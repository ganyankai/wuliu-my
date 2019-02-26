package com.zry.framework.front.controller;

import com.zry.framework.entity.CargoCustomer;
import com.zry.framework.service.CustomerService;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.base.util.RequestUtil;
import com.zrytech.framework.common.enums.CommonResult;
import com.zrytech.framework.common.enums.ResultEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "子账号相关api")
@RestController
@RequestMapping("/childAccount")
public class ChildAccountApiController {

    @Autowired
    private CustomerService customerService;


    /**
     * @Desinition:子账号类列表展示
     * @Author:Jxx
     * @param:
     * @return:
     * */
    @PostMapping("/page")
    @ApiOperation(value = "子账号类列表展示")
    public ServerResponse childAccountPage(@RequestBody RequestParams<CargoCustomer> requestParams) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        //TODO:设置货主登录ID
        //  requestParams.getParams().setCargoOwnnerId();
        return customerService.childAccountPage(requestParams.getParams());
    }
}
