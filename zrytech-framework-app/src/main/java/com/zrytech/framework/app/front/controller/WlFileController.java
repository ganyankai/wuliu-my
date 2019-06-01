package com.zrytech.framework.app.front.controller;

import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.app.service.WlFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @auther: xumeng
 * @date: 2018/5/3 11:05
 * @description: 附件Controller
 */
@Controller
@Slf4j
@RequestMapping("/api")
public class WlFileController {

    @Autowired
    private WlFileService fileService;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @RequestMapping("/upload")
    @ResponseBody
    public ServerResponse upload(MultipartFile file){
        return fileService.upload(file);
    }


    /**
     * 文件上传
     * @param file
     * @return
     */
    @RequestMapping("/uploadAliYun")
    @ResponseBody
    public ServerResponse uploadAliyun(MultipartFile file){

        return fileService.uploadAliyun(file);
    }


    /**
     * 图片预览
     * @param fileId
     * @param response
     */
    @RequestMapping("/view/{fileId}")
    public void view(@PathVariable("fileId") String fileId, HttpServletResponse response){
          Integer id=0;
        try{
           id=Integer.parseInt(fileId);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        fileService.view(id,response);
    }

    /**
     * 图片预览
     * @param fileId
     * @param response
     */
    @RequestMapping("/view/thumb/{fileId}")
    public void viewThumb(@PathVariable("fileId") Integer fileId, HttpServletResponse response){
        fileService.viewThumb(fileId,response);
    }


    /**
     * 文件下载
     * @param fileId
     * @param response
     */
    @RequestMapping("/download/{fileId}")
    public void download(@PathVariable("fileId") Integer fileId, HttpServletResponse response){
        fileService.download(fileId,response);
    }

    /**
     * 文件压缩下载
     * @param fileId
     * @param response
     */
    @RequestMapping("/zipDowload")
    public void zipDowload(HttpServletResponse response){
        fileService.zipDowload(response);
    }

}
