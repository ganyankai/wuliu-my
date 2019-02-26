package com.zry.framework.front.controller;

import com.zry.framework.dto.CargoDto;
import com.zry.framework.service.CargoService;
import com.zrytech.framework.base.annotation.CurrentCustomer;
import com.zrytech.framework.base.entity.Customer;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.common.enums.CommonResult;
import com.zrytech.framework.common.enums.ResultEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "货源相关api")
@RestController
@RequestMapping("/goodsSource")
public class CargoSourceController {

    @Autowired
    private CargoService cargoService;

    /**
     * Desintion:我的货源分页列表信息
     *
     * @author:jiangxiaoxiang
     * @param:CargoDto货源dto
     * @return:ServerResponse
     */
    @PostMapping("/mySourcePage")
    @ApiOperation(value = "我的货源")
    public ServerResponse mySourcePage(@RequestBody RequestParams<CargoDto> requestParams, @CurrentCustomer Customer customer) {
        CargoDto cargoDto = requestParams.getParams();
        if (requestParams.getParams() == null) {
            cargoDto = new CargoDto();
        }
        cargoDto.setCreateBy(customer.getId());
        return cargoService.mySourcePage(requestParams.getParams(), requestParams.getPage());
    }
}