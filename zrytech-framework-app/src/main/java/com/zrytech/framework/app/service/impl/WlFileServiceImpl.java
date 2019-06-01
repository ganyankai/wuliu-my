package com.zrytech.framework.app.service.impl;

import com.zrytech.framework.app.constants.WlConstant;
import com.zrytech.framework.app.ftp.WlIFtp;
import com.zrytech.framework.app.utils.WlFileUtil;
import com.zrytech.framework.app.utils.WlOssServerUtils;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.enums.FileTypeEnum;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.base.util.PictureCompressUtil;
import com.zrytech.framework.app.dao.WlFileDao;
import com.zrytech.framework.app.entity.WlFile;
import com.zrytech.framework.app.entity.WlFileRelation;
import com.zrytech.framework.common.enums.CommonResult;
import com.zrytech.framework.common.enums.ResultEnum;
import com.zrytech.framework.app.service.WlFileService;

import com.zrytech.framework.common.util.VideoTool;
import com.zrytech.framework.app.utils.WlZipUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class WlFileServiceImpl implements WlFileService {

    @Autowired
    private WlFileDao wlFileDao;

    @Autowired
    private WlIFtp wlIFtp;

    @Value("${file.tmp.dir}")
    private String tmpDir;


    /**
     * 本地字符编码
     */
    private static String LOCAL_CHARSET = "GBK";

    // FTP协议里面，规定文件名编码为iso-8859-1
    private static String SERVER_CHARSET = "ISO-8859-1";

    @Override
    public ServerResponse upload(MultipartFile file) {
        String fileName = file.getOriginalFilename();//获取文件名称
        String[] preFile = fileName.split("\\.");
        fileName = UUID.randomUUID().toString().replace("-", "") + "." + preFile[preFile.length - 1];
        String localTempFile = createLocalTempFile(file);//创建本地临时文件
        System.out.println("文件路径:" + localTempFile);
        InputStream inputStream = null;
        OutputStream compress_outputStream = null;
        try {
            String fullyQualifiedPath = WlFileUtil.getfullyQualifiedPath(fileName);//获取文件上传到目标服务器全路径
            WlFile uploadWlFile = (WlFile) WlFileUtil.getFile(fullyQualifiedPath, WlFile.class);//获取要最终存储到数据库文件对象
            uploadWlFile.setFileSize(file.getSize()); //设置文件大小
            inputStream = new FileInputStream(localTempFile);//获取文件输入流
            wlIFtp.storeUniqueFile(uploadWlFile, inputStream);//执行上传
            uploadWlFile = wlFileDao.save(uploadWlFile);//进行数据库保存
            FileTypeEnum fileType = WlFileUtil.getFileType(fileName);//获取文件类型
            //如果是图片类型的文件 要进行压缩再额外上传一份
            if (fileType == FileTypeEnum.picture) {
                String compress_FileName = WlFileUtil.getCompressFileName(fileName);//获取压缩后的文件的文件名称
                String compress_fullyQualifiedPath = WlFileUtil.getfullyQualifiedPath(compress_FileName);//获取文件上传到目标服务器全路径
                WlFile compress_uploadWlFile = (WlFile) WlFileUtil.getFile(compress_fullyQualifiedPath, WlFile.class);//获取文件对象
                compress_outputStream = wlIFtp.storeFileStream(compress_uploadWlFile); //写文件服务器输出流
                PictureCompressUtil.compress(localTempFile, compress_outputStream);
                WlFile compress_Wl_File = wlFileDao.save(compress_uploadWlFile);
                wlFileDao.relationOriginalFileAndCompressFile(uploadWlFile, compress_Wl_File); //建立原图与缩略图之间的联系
            }
            return ServerResponse.successWithData(uploadWlFile);
        } catch (Exception e) {
            log.error("文件上传失败:", e);
            throw new BusinessException(new CommonResult(ResultEnum.FILE_UPLOAD_FAILURE));
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (compress_outputStream != null) {
                try {
                    compress_outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            deleteLocalTempFile(localTempFile);//删除本地零时文件
        }
    }

    /**
     * 阿里云文件上传(视频,图片)
     *
     * @return
     * @param:file
     */
    public ServerResponse uploadAliyun(MultipartFile file) {
        String fileName = file.getOriginalFilename();//获取文件名称
        String[] preFile = fileName.split("\\.");
        fileName = UUID.randomUUID().toString().replace("-", "") + "." + preFile[preFile.length - 1];
        String localTempFile = createLocalTempFile(file);//创建本地临时文件:视频封面截取路径
        String sourceUrl = null;//上传到阿里云视频的url地址
        if (localTempFile != null) {
            sourceUrl = WlOssServerUtils.uploadVideo(localTempFile);
            log.warn("上传阿里云:" + sourceUrl);
        }
        //String ffmpepPath= DictionUtil.getValue(Constant.UNIX_FFMPEG, Constant.UNIX_FFMPEG_KEY);
        String videoLocalTempFile = VideoTool.processImg(localTempFile, WlConstant.FFMPEG_PATH);
        if (videoLocalTempFile != null) {
            localTempFile = videoLocalTempFile;
        }
        InputStream inputStream = null;
        OutputStream compress_outputStream = null;
        try {
            String fullyQualifiedPath = WlFileUtil.getfullyQualifiedPath(fileName);//获取文件上传到目标服务器全路径
            WlFile uploadWlFile = (WlFile) WlFileUtil.getFile(fullyQualifiedPath, WlFile.class);//获取要最终存储到数据库文件对象
            uploadWlFile.setFileSize(file.getSize()); //设置文件大小
            inputStream = new FileInputStream(localTempFile);//获取文件输入流
            wlIFtp.storeUniqueFile(uploadWlFile, inputStream);//执行上传
            uploadWlFile = wlFileDao.save(uploadWlFile);//进行数据库保存
            FileTypeEnum fileType = WlFileUtil.getFileType(fileName);//获取文件类型
            //如果是图片类型的文件 要进行压缩再额外上传一份
            if (fileType == FileTypeEnum.picture) {
                String compress_FileName = WlFileUtil.getCompressFileName(fileName);//获取压缩后的文件的文件名称
                String compress_fullyQualifiedPath = WlFileUtil.getfullyQualifiedPath(compress_FileName);//获取文件上传到目标服务器全路径
                WlFile compress_uploadWlFile = (WlFile) WlFileUtil.getFile(compress_fullyQualifiedPath, WlFile.class);//获取文件对象
                compress_outputStream = wlIFtp.storeFileStream(compress_uploadWlFile); //写文件服务器输出流
                PictureCompressUtil.compress(localTempFile, compress_outputStream);
                WlFile compress_Wl_File = wlFileDao.save(compress_uploadWlFile);
                wlFileDao.relationOriginalFileAndCompressFile(uploadWlFile, compress_Wl_File); //建立原图与缩略图之间的联系
            }
            uploadWlFile.setSourceUrl(sourceUrl);
            return ServerResponse.successWithData(uploadWlFile);

        } catch (Exception e) {
            log.error("文件上传失败:", e);
            throw new BusinessException(new CommonResult(ResultEnum.FILE_UPLOAD_FAILURE));
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (compress_outputStream != null) {
                try {
                    compress_outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            deleteLocalTempFile(localTempFile);//删除本地零时文件
        }
    }

    public void zipDowload(HttpServletResponse response) {
        try {
            WlZipUtils.connnectDowload(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void view(Integer fileId, HttpServletResponse response) {
        if (fileId == null) {
            fileId = 0;
        }
        WlFile wlFile = wlFileDao.findOne(fileId); //查询数据获取文件entity
        if (wlFile != null) {
            response.setContentType("text/html; charset=UTF-8"); //设置响应头
            response.setContentType("image/jpeg");
            internalWrite(wlFile, response);
        }
    }

    @Override
    public void download(Integer fileId, HttpServletResponse response) {
        WlFile wlFile = wlFileDao.findOne(fileId);
        String fileOriginName = wlFile.getFileOriginName();//文件名称
        try {
            fileOriginName = java.net.URLEncoder.encode(fileOriginName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("编码失败", e);
        }
        response.setContentType("application/force-download"); //设置响应头
        response.setHeader("Content-Disposition", "attachment;filename=" + fileOriginName);
        internalWrite(wlFile, response);
    }

    @Override
    public void viewThumb(Integer fileId, HttpServletResponse response) {
        WlFileRelation wlFileRelation = wlFileDao.findFileRelationByOriginalId(fileId);
        Integer fileRelationCompressId = wlFileRelation.getFileRelationCompressId();
        view(fileRelationCompressId, response);
    }


    private void internalWrite(WlFile wlFile, HttpServletResponse response) {
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            wlIFtp.retrieveFile(wlFile, out);
            out.flush();
        } catch (IOException e) {
            log.error("文件读取失败：fileId={}", wlFile.getId(), e);
            throw new BusinessException(new CommonResult(ResultEnum.FILE_DOWNLOAD_FAILURE));
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 生成本地临时文件
     *
     * @param multipartFile
     * @return
     */
    private String createLocalTempFile(MultipartFile multipartFile) {
        java.io.File file = new java.io.File(tmpDir);
        if (!file.exists()) {
            file.mkdir();
        }
        String originalFilename = multipartFile.getOriginalFilename();
        String tmpFileName = System.currentTimeMillis() + WlFileUtil.getFileSuffixes(originalFilename);//生成零时文件名称
        String localfilePath = tmpDir + java.io.File.separator + tmpFileName;
        java.io.File tmpfile = new java.io.File(localfilePath); //生成本地零时文件
        try {
            multipartFile.transferTo(tmpfile);
        } catch (IOException e) {
            log.error("生成本地零时文件出错：", e);
            throw new BusinessException(new CommonResult(ResultEnum.FILE_UPLOAD_FAILURE));
        }
        //生成本地临时文件
        log.warn("本地临时文件已经生成:"+localfilePath);
        return localfilePath;
    }

    private void deleteLocalTempFile(String localTempFile) {
        log.warn("删除本地临时文件:"+localTempFile);
        java.io.File file = new java.io.File(localTempFile);
        if (file.exists()) {
            file.delete();
        }
    }


}


