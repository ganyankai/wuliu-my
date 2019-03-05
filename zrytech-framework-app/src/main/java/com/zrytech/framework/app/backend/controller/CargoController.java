package com.zrytech.framework.app.backend.controller;


import com.zrytech.framework.app.dto.CargoDto;
import com.zrytech.framework.app.service.CargoService;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.base.util.RequestUtil;
import com.zrytech.framework.common.entity.SysCustomer;
import com.zrytech.framework.common.entity.User;
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
        CargoDto cargoDto = requestParams.getParams();
        if (cargoDto == null) {
            cargoDto = new CargoDto();
        }
        return cargoService.cargoPage(cargoDto, requestParams.getPage());
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
        if (requestParams.getParams() == null
                || requestParams.getParams().getId() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        User user = RequestUtil.getCurrentUser(User.class);
        return cargoService.auditSource(requestParams.getParams(), user);
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
        SysCustomer sysCustomer = RequestUtil.getCurrentUser(SysCustomer.class);
        requestParams.getParams().setCreateBy(sysCustomer.getId());
        return cargoService.pushResource(requestParams.getParams());
    }

    /**
     * Desintion:取消发布货源(前端)
     *
     * @author:jiangxiaoxiang
     * @param:CargoDto货源dto
     * @return:ServerResponse
     */
    @PostMapping("/cancelResource")
    @ApiOperation(value = "取消发布货源")
    public ServerResponse cancelResource(@RequestBody RequestParams<CargoDto> requestParams) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return cargoService.cancelResource(requestParams.getParams());
    }

    /**
     * Desintion:修改货源(前端)
     *
     * @author:jiangxiaoxiang
     * @param:CargoDto货源dto
     * @return:ServerResponse
     */
    @PostMapping("/updateSource")
    @ApiOperation(value = "修改货源")
    public ServerResponse updateSource(@RequestBody RequestParams<CargoDto> requestParams) {
        if (requestParams.getParams() == null
                || requestParams.getParams().getId() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return cargoService.updateSource(requestParams.getParams());
    }

    /**
     * Desintion:删除货源(前端)
     *
     * @author:jiangxiaoxiang
     * @param:CargoDto货源dto
     * @return:ServerResponse
     */
    @PostMapping("/deleteSource")
    @ApiOperation(value = "删除货源")
    public ServerResponse deleteSource(@RequestBody RequestParams<CargoDto> requestParams) {
        if (requestParams.getParams() == null
                || requestParams.getParams().getId() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return cargoService.deleteSource(requestParams.getParams());
    }

    /**
     * Desintion:邀请报价(前端)
     *
     * @author:jiangxiaoxiang
     * @param:CargoDto货源dto
     * @return:ServerResponse
     */
    @PostMapping("/invitationOffer")
    @ApiOperation(value = "邀请报价")
    public ServerResponse invitationOffer(@RequestBody RequestParams<CargoDto> requestParams) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return cargoService.invitationOffer(requestParams.getParams());
    }
}
