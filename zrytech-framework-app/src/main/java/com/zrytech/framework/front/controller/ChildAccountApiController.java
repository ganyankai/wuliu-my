package com.zrytech.framework.front.controller;

import com.zrytech.framework.dto.CargoCustomerDto;
import com.zrytech.framework.service.CustomerService;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.base.util.RequestUtil;
import com.zrytech.framework.common.entity.SysCustomer;
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
     * @param:CargoCustomerDto客户dto
     * @return:ServerResponse
     * */
    @PostMapping("/page")
    @ApiOperation(value = "子账号类列表展示")
    public ServerResponse childAccountPage(@RequestBody RequestParams<CargoCustomerDto> requestParams) {
        CargoCustomerDto cargoCustomerDto=requestParams.getParams();
        if (cargoCustomerDto== null) {
            cargoCustomerDto=new CargoCustomerDto();
        }
        SysCustomer sysCustomer = RequestUtil.getCurrentUser(SysCustomer.class);
        //TODO:设置货主登录ID
        cargoCustomerDto.setId(sysCustomer.getId());
        return customerService.childAccountPage(cargoCustomerDto,requestParams.getPage());
    }

    /**
     * @Desinition:子账号添加
     * @Author:Jxx
     * @param:CargoCustomerDto客户dto
     * @return:ServerResponse
     * */
    @PostMapping("/addAccount")
    @ApiOperation(value = "子账号添加")
    public ServerResponse addAccount(@RequestBody RequestParams<CargoCustomerDto> requestParams) {
        CargoCustomerDto cargoCustomerDto=requestParams.getParams();
        if (cargoCustomerDto== null) {
            cargoCustomerDto=new CargoCustomerDto();
        }
        SysCustomer sysCustomer = RequestUtil.getCurrentUser(SysCustomer.class);
        //TODO:设置货主登录ID
        cargoCustomerDto.setCreateBy(sysCustomer.getId());
        return customerService.addAccount(cargoCustomerDto);
    }

    /**
     * @Desinition:子账号详情
     * @Author:Jxx
     * @param:CargoCustomerDto客户dto
     * @return:ServerResponse
     * */
    @PostMapping("/detail")
    @ApiOperation(value = "子账号详情")
    public ServerResponse detail(@RequestBody RequestParams<CargoCustomerDto> requestParams) {
        CargoCustomerDto cargoCustomerDto=requestParams.getParams();
        if (cargoCustomerDto== null
                || cargoCustomerDto.getId()==null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return customerService.detail(cargoCustomerDto);
    }

    /**
     * @Desinition:子账号修改
     * @Author:Jxx
     * @param:CargoCustomerDto客户dto
     * @return:ServerResponse
     * */
    @PostMapping("/updateAccount")
    @ApiOperation(value = "子账号修改")
    public ServerResponse updateAccount(@RequestBody RequestParams<CargoCustomerDto> requestParams) {
        CargoCustomerDto cargoCustomerDto=requestParams.getParams();
        if (cargoCustomerDto== null
                || cargoCustomerDto.getId()==null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return customerService.updateAccount(cargoCustomerDto);
    }

    /**
     * @Desinition:子账号删除
     * @Author:Jxx
     * @param:CargoCustomerDto客户dto
     * @return:ServerResponse
     * */
    @PostMapping("/deleteAccount")
    @ApiOperation(value = "子账号删除")
    public ServerResponse deleteAccount(@RequestBody RequestParams<CargoCustomerDto> requestParams) {
        CargoCustomerDto cargoCustomerDto=requestParams.getParams();
        if (cargoCustomerDto== null
                || cargoCustomerDto.getId()==null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return customerService.deleteAccount(cargoCustomerDto);
    }
}
