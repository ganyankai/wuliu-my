package com.zrytech.framework.app.service.impl;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.app.constants.WlConstant;
import com.zrytech.framework.app.entity.WlArticle;
import com.zrytech.framework.app.entity.WlArticleComment;
import com.zrytech.framework.app.entity.WlFile;
import com.zrytech.framework.app.enums.WlArticleType;
import com.zrytech.framework.app.utils.WlFileUtil;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.enums.FileTypeEnum;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.base.ftp.IFtp;
import com.zrytech.framework.base.util.BeanUtil;
//import com.zrytech.framework.base.util.FileUtil;
import com.zrytech.framework.app.dao.*;
import com.zrytech.framework.app.dto.WlArticleDto;
import com.zrytech.framework.app.entity.WlArticleCategory;
import com.zrytech.framework.common.dao.UserDao;
import com.zrytech.framework.common.entity.User;
import com.zrytech.framework.common.enums.CommonResult;
import com.zrytech.framework.common.enums.ResultEnum;
import com.zrytech.framework.app.service.WlArticleService;
import com.zrytech.framework.common.util.DictionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author zhanhao
 * @date 2018/05/04 10:59
 **/
@Service
@Slf4j
public class WlArticleServiceImpl implements WlArticleService {

    @Autowired
    private WlArticleDao wlArticleDao;

    @Autowired
    private WlArticleCategoryDao wlArticleCategoryDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private WlArticleCommentDao wlArticleCommentDao;

    @Autowired
    private WlArticleLikeCollectDao wlArticleLikeCollectDao;


    public ServerResponse insertArticle(User user, WlArticleDto wlArticleDto) {
        if (wlArticleDto == null || user == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        WlArticle wlArticle = BeanUtil.copy(wlArticleDto, WlArticle.class);
        WlArticleCategory wlArticleCategory = wlArticleCategoryDao.selectArticleCategoryById(wlArticle.getArticleCategoryId());
        if (wlArticleCategory == null) {
            throw new BusinessException(new CommonResult(ResultEnum.REPOSITORY_NOT_EXIST));
        }
        wlArticle.setArticleCreateUserId(user.getId());
        if (WlArticleType.RECEPTIONROOM.getIndex() == wlArticleDto.getArticleCategoryId()) {
            checkFileId(wlArticle);
        }
        wlArticleDao.insertArticle(wlArticle);
        return ServerResponse.success();
    }

    @Autowired
    WlFileDao wlFileDao;

    @Autowired
    private IFtp ftp;


    public void checkFileId(WlArticle wlArticle) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        Process process = null;
        String localTempFile = createLocalTempFile();
        String ffmpepPath = DictionUtil.getValue(WlConstant.UNIX_FFMPEG, WlConstant.UNIX_FFMPEG_KEY);
        try {
            process = processBuilder.command(ffmpepPath, "-i",
                    wlArticle.getSourceUrl(), "-t", "0.01", localTempFile).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // boolean alive = process.isAlive();
        //if (alive) {

        WlFile wlFile = upload(localTempFile);
        if (wlFile != null) {
            wlArticle.setArticleCover(wlFile.getId());
            //  }
        }
    }

    public WlFile upload(String localTempFilePath) {
        System.out.println("文件路径:" + localTempFilePath);
        /*  InputStream inputStream = null;*/
       /* OutputStream compress_outputStream = null;*/
        java.io.File file = new java.io.File(localTempFilePath);
        try {
            System.out.println("文件名称:" + file.getName() + ";文件path路径:" + file.getPath() + "绝对路径:" + file.getCanonicalPath());
            String fullyQualifiedPath = WlFileUtil.getfullyQualifiedPath(file.getName());//获取文件上传到目标服务器全路径
            WlFile uploadWlFile = (WlFile) WlFileUtil.getFile(fullyQualifiedPath, WlFile.class);//获取要最终存储到数据库文件对象
            // uploadFile.setFileSize(file.getSize()); //设置文件大小
            /*  inputStream = new FileInputStream(file.getPath());//获取文件输入流:报错*/
            System.out.println("文件路径>>>>>>>>>>>>>:" + localTempFilePath);
            uploadWlFile.setFileRewriteFullyQualifiedPath(uploadWlFile.getFileFullyQualifiedPath());
            /*    ftp.storeUniqueFile(uploadFile, inputStream);//执行上传*/
            uploadWlFile = wlFileDao.save(uploadWlFile);//进行数据库保存
            FileTypeEnum fileType = WlFileUtil.getFileType(file.getName());//获取文件类型
            //如果是图片类型的文件 要进行压缩再额外上传一份
          /*  if (fileType == FileTypeEnum.picture) {
                String compress_FileName = FileUtil.getCompressFileName(file.getName());//获取压缩后的文件的文件名称
                String compress_fullyQualifiedPath = FileUtil.getfullyQualifiedPath(compress_FileName);//获取文件上传到目标服务器全路径
                File compress_uploadFile = (File) FileUtil.getFile(compress_fullyQualifiedPath, File.class);//获取文件对象
              *//*  compress_outputStream = ftp.storeFileStream(compress_uploadFile); //写文件服务器输出流
                PictureCompressUtil.compress(localTempFilePath, compress_outputStream);*//*
                File compress_File = fileDao.save(compress_uploadFile);
                fileDao.relationOriginalFileAndCompressFile(uploadFile, compress_File); //建立原图与缩略图之间的联系
            }*/
            return uploadWlFile;
        } catch (Exception e) {
            log.error("文件上传失败:", e);
            e.printStackTrace();
            throw new BusinessException(new CommonResult(ResultEnum.FILE_UPLOAD_FAILURE));
        } finally {
            deleteLocalTempFile(localTempFilePath);//删除本地零时文件
        }
    }

    private void deleteLocalTempFile(String localTempFile) {
        java.io.File file = new java.io.File(localTempFile);
        if (file.exists()) {
            file.delete();
        }
    }

    private String createLocalTempFile() {
        java.io.File file = new java.io.File(WlConstant.VIDEO_FILE_PATH);
        if (!file.exists()) {
            file.mkdir();
        }
        String localfilePath = WlConstant.VIDEO_FILE_PATH + java.io.File.separator + System.currentTimeMillis() + ".jpg";
        return localfilePath;
    }

    public ServerResponse deleteArticle(WlArticleDto wlArticleDto) {
        if (wlArticleDto == null || wlArticleDto.getId() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        WlArticle wlArticle = BeanUtil.copy(wlArticleDto, WlArticle.class);
        wlArticleDao.deleteArticle(wlArticle);
        WlArticleComment wlArticleComment = new WlArticleComment();
        wlArticleComment.setArticleId(wlArticle.getId());
        wlArticleCommentDao.deleteArticleComment(wlArticleComment);

        wlArticleLikeCollectDao.delete(wlArticle.getId());
        return ServerResponse.success();
    }

    public ServerResponse updateArticle(WlArticleDto wlArticleDto) {
        if (wlArticleDto == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        WlArticle wlArticle = BeanUtil.copy(wlArticleDto, WlArticle.class);
        WlArticleCategory wlArticleCategory = wlArticleCategoryDao.selectArticleCategoryById(wlArticle.getArticleCategoryId());
        if (wlArticleCategory == null) {
            throw new BusinessException(new CommonResult(ResultEnum.REPOSITORY_NOT_EXIST));
        }
        if (WlArticleType.RECEPTIONROOM.getIndex() == wlArticle.getArticleCategoryId()) {
            checkFileId(wlArticle);
        }
        wlArticleDao.updateArticle(wlArticle);
        return ServerResponse.success();
    }

    public ServerResponse selectArticleById(WlArticleDto articleDt) {
        if (articleDt == null || articleDt.getId() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return ServerResponse.successWithData(wlArticleDao.selectArticleById(articleDt.getId()));
    }

    public ServerResponse selectArticlePage(WlArticleDto wlArticleDto, Page page) {
        if (wlArticleDto == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        WlArticle wlArticle = BeanUtil.copy(wlArticleDto, WlArticle.class);
        PageInfo<WlArticle> pageInfo = wlArticleDao.selectArticlePage(wlArticle, page);
        checkContentRegex(pageInfo);
        return ServerResponse.successWithData(pageInfo);
    }

    public void checkContentRegex(PageInfo<WlArticle> pageInfo) {
        if (pageInfo != null) {
            List<WlArticle> list = pageInfo.getList();
            if (list != null && list.size() > 0) {
                for (int i=1;i<list.size();i++) {
                    if (list.get(i).getArticleContent() != null && list.get(i).getArticleContent().length() > 0) {
                        String content = list.get(i).getArticleContent().replaceAll("<(.|\\n)+?>", "");
                        list.get(i).setArticleContent(content);
                      /*  if (content != null && content.length() > 40) {
                            list.get(i).setArticleContent(content.substring(0, 35) + "...");
                        } else {
                            list.get(i).setArticleContent(content);
                        }*/
                    }
                }
            }
        }
    }

    public ServerResponse status(WlArticleDto wlArticleDto) {
        if (wlArticleDto == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        WlArticle wlArticle = new WlArticle();
        wlArticle.setId(wlArticleDto.getId());
        wlArticle.setArticleStatus(wlArticleDto.getArticleStatus());
        wlArticleDao.updateArticle(wlArticle);
        return ServerResponse.success();
    }
}
