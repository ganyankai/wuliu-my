package com.zrytech.framework.app.controller.api;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 企业收数清单表
 */
@Deprecated
@RestController
@RequestMapping("/idcard")
public class IdcardParserController {

    @RequestMapping("/export")
    public void idCardExport(HttpServletResponse response) throws Exception {

    }



}
