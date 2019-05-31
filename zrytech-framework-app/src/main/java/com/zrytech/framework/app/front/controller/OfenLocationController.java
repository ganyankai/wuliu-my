package com.zrytech.framework.app.front.controller;

import com.zrytech.framework.app.ano.NeedCertified;
import com.zrytech.framework.app.dto.ofenlocation.OfenLocationAddDto;
import com.zrytech.framework.app.dto.ofenlocation.OfenLocationCommonDto;
import com.zrytech.framework.app.dto.ofenlocation.OfenLocationDto;
import com.zrytech.framework.app.dto.ofenlocation.OfenLocationUpdateDto;
import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.app.entity.OfenLocation;
import com.zrytech.framework.app.service.OfenLocationService;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.base.util.RequestUtil;
import com.zrytech.framework.common.enums.CommonResult;
import com.zrytech.framework.common.enums.ResultEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(description = "货主常用地址api")
@RestController
@RequestMapping("/ofenLocation")
public class OfenLocationController {

    @Autowired
    private OfenLocationService ofenLocationService;
    /**
     * Desintion:常用地址分页列表信息
     *
     * @author:dante
     * @param:OfenLocationDto常用地址dto
     * @return:ServerResponse
     */
    @NeedCertified
    @Valid
    @PostMapping("/page")
    @ApiOperation(value = "常用地址分页列表信息")
    public ServerResponse addressPage(@RequestBody RequestParams<OfenLocationDto> requestParams,BindingResult result) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return ofenLocationService.addressPage(requestParams.getParams(),requestParams.getPage());
    }

    /**
     * Desintion:常用地址添加
     *
     * @author:dante
     * @param:OfenLocationDto常用地址dto
     * @return:ServerResponse
     */
    @NeedCertified
    @PostMapping("/add")
    @ApiOperation(value = "常用地址添加")
    public ServerResponse add(@RequestBody @Valid RequestParams<OfenLocationAddDto> requestParams,BindingResult result) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return ofenLocationService.add(requestParams.getParams());
    }

    /**
     * Desintion:常用地址详情
     *
     * @author:dante
     * @param:OfenLocationDto常用地址dto
     * @return:ServerResponse
     */
    @NeedCertified
    @Valid
    @PostMapping("/get")
    @ApiOperation(value = "常用地址详情")
    public ServerResponse get(@RequestBody @Valid RequestParams<OfenLocationCommonDto> requestParams,BindingResult result) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        //判断当前用户身份
        if(!ofenLocationService.checkCustomer(requestParams.getParams().getId())){
            throw new BusinessException(new CommonResult(ResultEnum.CUSTOMER_NOT_EXIST));
        }
        return ofenLocationService.get(requestParams.getParams());
    }

    /**
     * Desintion:常用地址修改
     *
     * @author:dante
     * @param:OfenLocationDto常用地址dto
     * @return:ServerResponse
     */
    @NeedCertified
    @PostMapping("/update")
    @ApiOperation(value = "常用地址修改")
    public ServerResponse update(@RequestBody @Valid RequestParams<OfenLocationUpdateDto> requestParams,BindingResult result) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        //判断当前用户身份
        if(!ofenLocationService.checkCustomer(requestParams.getParams().getId())){
            throw new BusinessException(new CommonResult(ResultEnum.CUSTOMER_NOT_EXIST));
        }
        return ofenLocationService.update(requestParams.getParams());
    }

    /**
     * Desintion:常用地址删除
     *
     * @author:dante
     * @param:OfenLocationDto常用地址dto
     * @return:ServerResponse
     */
    @NeedCertified
    @Valid
    @PostMapping("/delete")
    @ApiOperation(value = "常用地址删除")
    public ServerResponse delete(@RequestBody @Valid RequestParams<OfenLocationCommonDto> requestParams,BindingResult result) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        //判断当前用户身份
        if(!ofenLocationService.checkCustomer(requestParams.getParams().getId())){
            throw new BusinessException(new CommonResult(ResultEnum.CUSTOMER_NOT_EXIST));
        }
        return ofenLocationService.delete(requestParams.getParams());
    }
}
