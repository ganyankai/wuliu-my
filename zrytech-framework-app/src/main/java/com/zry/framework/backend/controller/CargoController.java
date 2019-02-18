package com.zry.framework.backend.controller;


import com.zry.framework.dto.CargoDto;
import com.zry.framework.service.CargoService;
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
public class CargoController {


    @Autowired
    private CargoService cargoService;

    /**
     * Desintion:货源分页列表信息
     *
     * @author:jiangxiaoxiang
     * @param:CargoDto货源dto
     * @return:ServerResponse
     */
    @PostMapping("/page")
    @ApiOperation(value = "货源分页列表信息")
    public ServerResponse cargoPage(@RequestBody RequestParams<CargoDto> requestParams) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return cargoService.cargoPage(requestParams.getParams(),requestParams.getPage());
    }

    /**
     * Desintion:货源详情
     *
     * @author:jiangxiaoxiang
     * @param:CargoDto货源dto
     * @return:ServerResponse
     */
    @PostMapping("/get")
    @ApiOperation(value = "货源详情")
    public ServerResponse get(@RequestBody RequestParams<CargoDto> requestParams) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return cargoService.get(requestParams.getParams());
    }

    /**
     * Desintion:货源审核
     *
     * @author:jiangxiaoxiang
     * @param:CargoDto货源dto
     * @return:ServerResponse
     */
    @PostMapping("/auditSource")
    @ApiOperation(value = "货源审核")
    public ServerResponse auditSource(@RequestBody RequestParams<CargoDto> requestParams) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return cargoService.auditSource(requestParams.getParams());
    }


    /**
     * Desintion:发布货源(前端)
     *
     * @author:jiangxiaoxiang
     * @param:CargoDto货源dto
     * @return:ServerResponse
     */
    @PostMapping("/pushResource")
    @ApiOperation(value = "发布货源")
    public ServerResponse pushResource(@RequestBody RequestParams<CargoDto> requestParams) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return cargoService.pushResource(requestParams.getParams());
    }


}
