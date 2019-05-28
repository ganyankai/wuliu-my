package com.zrytech.framework.app.backend.controller;

import com.zrytech.framework.app.dto.OftenAddressDto;
import com.zrytech.framework.app.service.OftenAddressService;
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

@Api(description = "常用地址相关api")
@RestController
@RequestMapping("/usedAddress")
public class OftenAddressController {

    @Autowired
    private OftenAddressService oftenAddressService;

    /**
     * Desintion:常用地址分页列表信息
     *
     */
    @PostMapping("/page")
    @ApiOperation(value = "常用地址分页列表信息")
    public ServerResponse certificationPage(@RequestBody RequestParams<OftenAddressDto> requestParams) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return oftenAddressService.addressPage(requestParams.getParams(),requestParams.getPage());
    }

    /**
     * Desintion:常用地址添加
     *
     * @author:jiangxiaoxiang
     * @param:OftenAddressDto常用地址dto
     * @return:ServerResponse
     */
    @PostMapping("/add")
    @ApiOperation(value = "常用地址添加")
    public ServerResponse add(@RequestBody RequestParams<OftenAddressDto> requestParams) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return oftenAddressService.add(requestParams.getParams());
    }

    /**
     * Desintion:常用地址详情
     *
     * @author:jiangxiaoxiang
     * @param:OftenAddressDto常用地址dto
     * @return:ServerResponse
     */
    @PostMapping("/get")
    @ApiOperation(value = "常用地址详情")
    public ServerResponse get(@RequestBody RequestParams<OftenAddressDto> requestParams) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return oftenAddressService.get(requestParams.getParams());
    }

    /**
     * Desintion:常用地址修改
     *
     * @author:jiangxiaoxiang
     * @param:OftenAddressDto常用地址dto
     * @return:ServerResponse
     */
    @PostMapping("/update")
    @ApiOperation(value = "常用地址修改")
    public ServerResponse update(@RequestBody RequestParams<OftenAddressDto> requestParams) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return oftenAddressService.update(requestParams.getParams());
    }

    /**
     * Desintion:常用地址删除
     *
     * @author:jiangxiaoxiang
     * @param:OftenAddressDto常用地址dto
     * @return:ServerResponse
     */
    @PostMapping("/delete")
    @ApiOperation(value = "常用地址删除")
    public ServerResponse delete(@RequestBody RequestParams<OftenAddressDto> requestParams) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return oftenAddressService.delete(requestParams.getParams());
    }
}
