package com.zry.framework.controller;

import com.zry.framework.service.IdcardService;
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


    @Autowired
    private IdcardService idcardService;

    @RequestMapping("/export")
    public void idCardExport(HttpServletResponse response) throws Exception {
        idcardService.excelExport(response);
    }

    @RequestMapping("/import")
    public ServerResponse idCardImport(@RequestParam("file") MultipartFile excelFile) {
        return idcardService.excelImport( excelFile);
    }

    @RequestMapping("/check")
    public ServerResponse idCardCheck() {
        return idcardService.checkIdCard();
    }

}
