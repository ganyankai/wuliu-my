package com.zrytech.framework.app.front.controller;

import com.zrytech.framework.app.dto.familiarcar.FamiliarCarDto;
import com.zrytech.framework.app.service.FamiliarCarService;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;
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

@Api(description = "familiarCar API")
@RestController
@RequestMapping("/familiarCar")
public class FamiliarCarController {

    @Autowired
    private FamiliarCarService familiarCarService;
    /**
     * Desintion:关注人分页列表信息
     *
     * @author:dante
     * @param:OfenLocationDto常用地址dto
     * @return:ServerResponse
     */
    @PostMapping("/page")
    @ApiOperation(value = "关注人分页列表信息")
    public ServerResponse familiarCarPage(@RequestBody RequestParams<FamiliarCarDto> requestParams, BindingResult result) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return familiarCarService.familiarCarPage(requestParams.getParams(),requestParams.getPage());
    }

    /**
     * Desintion:常用地址添加
     *
     * @author:dante
     * @param:familiarCar dto
     * @return:ServerResponse
     */
    @PostMapping("/add")
    @ApiOperation(value = "familiarCar添加")
    public ServerResponse add(@RequestBody @Valid RequestParams<FamiliarCarDto> requestParams,BindingResult result) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return familiarCarService.add(requestParams.getParams());
    }

    /**
     * Desintion:关注人删除
     *
     * @author:dante
     * @param : familiarCar dto
     * @return:ServerResponse
     */
    @PostMapping("/delete")
    @ApiOperation(value = "关注人删除")
    public ServerResponse delete(@RequestBody @Valid RequestParams<FamiliarCarDto> requestParams,BindingResult result) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return familiarCarService.delete(requestParams.getParams());
    }
}
