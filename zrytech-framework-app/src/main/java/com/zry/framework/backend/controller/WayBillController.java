package com.zry.framework.backend.controller;


import com.zry.framework.service.WaybillService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "运单相关api")
@RestController
@RequestMapping("/indent")
public class WayBillController {

    @Autowired
    private WaybillService waybillService;
}
