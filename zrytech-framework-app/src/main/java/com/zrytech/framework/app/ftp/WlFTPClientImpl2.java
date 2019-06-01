package com.zrytech.framework.app.ftp;

import com.zrytech.framework.base.constant.Constant;
import com.zrytech.framework.app.domain.File;
import com.zrytech.framework.base.enums.BaseResult;
import com.zrytech.framework.base.enums.ResultEnum;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.base.ftp.FTPClientFacotory;
import com.zrytech.framework.app.ftp.WlIFtp;
import com.zrytech.framework.base.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @Auther: xumeng
 * @Date: 2018/5/22/022 10:06
 * @Description:
 */
@Component
@Slf4j
public class WlFTPClientImpl2 implements WlIFtp {

    @Autowired
    private FTPClientFacotory facotory;

    public void storeUniqueFile(File describer, InputStream inputStream) {
        FTPClient client = facotory.getFTPClient();
        if (client == null) {
//            throw new BusinessException(new BaseResult(ResultEnum.FILE_UPLOAD_ERROR));
//            throw new BusinessException(new BaseResult(ResultEnum.BUSSESS_ERROR));
            throw new RuntimeException();
        }
        try {
            if (describer.getFilePath() != null) {
                createDir(client,describer.getFilePath());
            }
            boolean success = client.storeUniqueFile(describer.getFileRewriteName(), inputStream);
            if(!success){//文件上传 没有成功则抛出异常
//                throw new BusinessException(new BaseResult(ResultEnum.FILE_UPLOAD_ERROR));
                throw new RuntimeException();
            }
        } catch (Exception e) {
            log.debug("ftp上传{}文件失败", describer.getFileFullyQualifiedPath());
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(inputStream);
            facotory.destoryFTPClient(client);
        }
    }

    @Override
    public boolean deleteFile(File describer) {
        FTPClient client = facotory.getFTPClient();
        if (client == null) {
            return false;
        }
        try {
            if (describer.getFilePath() != null) {
                createDir(client,describer.getFilePath());
            }
            return client.deleteFile(describer.getFileRewriteName());
        } catch (Exception e) {
            log.error("删除ftp服务器文件失败:", e);
            return false;
        } finally {
            facotory.destoryFTPClient(client);
        }
    }

    @Override
    public OutputStream storeFileStream(File describer) {
        FTPClient client = facotory.getFTPClient();
        if (client == null) {
            return null;
        }
        try {
            if (describer.getFilePath() != null) {
                createDir(client,describer.getFilePath());
            }
            return client.storeFileStream(describer.getFileRewriteName());
        } catch (Exception e) {
            log.error("获取ftp服务器异常：", e);
        }
        return null;
    }

    @Override
    public boolean retrieveFile(File describer, OutputStream local) {
        FTPClient client = facotory.getFTPClient();
        try {
            if (describer.getFilePath() != null) {
                createDir(client,describer.getFilePath());
            }
            return client.retrieveFile(describer.getFileRewriteName(), local);
        } catch (Exception e) {
            log.error("ftp服务器异常：", e);
        }finally {
            facotory.destoryFTPClient(client);
        }
        return false;
    }

    @Override
    public InputStream retrieveFileStream(File describer) {
        FTPClient client = facotory.getFTPClient();
        try {
            if (describer.getFilePath() != null) {
                createDir(client,describer.getFilePath());
            }
            return client.retrieveFileStream(describer.getFileRewriteName());
        } catch (Exception e) {
            log.error("ftp服务器异常：", e);
        }finally {
            facotory.destoryFTPClient(client);
        }
        return null;
    }


    /**
     * 创建目录(有则切换目录，没有则创建目录)
     *
     * @param dir
     * @return
     */
    private void createDir(FTPClient client, String dir) throws IOException {
        //尝试切入目录
        if (client.changeWorkingDirectory(Constant.FTP_ROOT_DIR+dir)) {
            return ;
        }
        dir = StringUtil.trimStart(dir, Constant.FILE_SEPARATOR);
        dir = StringUtil.trimEnd(dir, Constant.FILE_SEPARATOR);
        String[] arr = dir.split(Constant.FILE_SEPARATOR);
        StringBuffer sbfDir = new StringBuffer();
        //定位到工作目录跟目录下
        sbfDir.append(Constant.FTP_ROOT_DIR);
        //循环生成子目录
        for (String s : arr) {
            sbfDir.append("/");//liunx 目录的分隔符
            sbfDir.append(s);
            dir = sbfDir.toString();
            //进入目录
            if (client.changeWorkingDirectory(dir)){
                //尝试一级一级进入 进入不了则创建目录
            }else {
                if (!client.makeDirectory(dir)) {
                    throw new RuntimeException("创建目录失败:" + sbfDir.toString());
                }
            }
        }
        //将目录切换至指定路径
        if(!client.changeWorkingDirectory(dir)){
            throw new RuntimeException(String.format("工作目录:%s,创建失败",dir));
        }
    }
}
