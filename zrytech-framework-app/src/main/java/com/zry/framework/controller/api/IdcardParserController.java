package com.zry.framework.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import com.zrytech.framework.base.entity.ServerResponse;

/**
 * 企业收数清单表
 */
@RestController
@RequestMapping("/idcard")
public class IdcardParserController {

    @RequestMapping("/export")
    public void idCardExport(HttpServletResponse response) throws Exception {

    }



}
