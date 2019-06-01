package com.zrytech.framework.app.service;

import com.zrytech.framework.base.entity.ServerResponse;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;


public interface WlFileService {

    /**
     * 文件上传
     * @param file 需要上传的附件对象
     * @return
     */
    ServerResponse upload(MultipartFile file);

    /**
     *  图片预览
     * @Long fileId  文件id
     * @param response 文件输出写入响应流
     */
    void view(Integer fileId, HttpServletResponse response);

    /**
     * 文件下载
     * @param fileId
     * @param response
     */
    void download(Integer fileId, HttpServletResponse response);

    /**
     *  图片预览 缩略图
     * @Long fileId  文件id
     * @param response 文件输出写入响应流
     */
    void viewThumb(Integer fileId, HttpServletResponse response);

    /**
     * 文件上传
     * @param file
     * @return
     */
    ServerResponse uploadAliyun(MultipartFile file);

    void zipDowload(HttpServletResponse response);

}
