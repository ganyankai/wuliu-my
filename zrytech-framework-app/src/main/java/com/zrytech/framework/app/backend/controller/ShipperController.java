package com.zrytech.framework.app.backend.controller;


import com.zrytech.framework.app.dto.CargoCustomerDto;
import com.zrytech.framework.app.dto.CertificationDto;
import com.zrytech.framework.app.service.ShipperService;
import com.zrytech.framework.base.annotation.CurrentUser;
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

@Api(description = "货主认证资料api")
@RestController
@RequestMapping("/shipper")
public class ShipperController {

    @Autowired
    private ShipperService shipperService;


    /**
     * Desintion:认证资料分页列表信息
     *
     * @author:jiangxiaoxiang
     * @param:CertificationDto认证资料对象
     * @return:ServerResponse
     */
    @PostMapping("/certificationPage")
    @ApiOperation(value = "认证资料分页列表信息")
    public ServerResponse certificationPage(@RequestBody RequestParams<CertificationDto> requestParams) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return shipperService.certificationPage(requestParams.getParams(), requestParams.getPage());
    }

    /**
     * Desintion:如果客户跳过个人/企业资料认证,后续再填写认证资料
     *
     * @author:jiangxiaoxiang
     * @param:CertificationDto认证资料对象
     * @return:ServerResponse
     */
    @PostMapping("/perSonOrOrganizeCertification")
    @ApiOperation(value = "填写认证材料")
    public ServerResponse perSonOrOrganizeCertification(@RequestBody RequestParams<CertificationDto> requestParams) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        SysCustomer sysCustomer = RequestUtil.getCurrentUser(SysCustomer.class);
        return shipperService.perSonOrOrganizeCertification(requestParams.getParams(), sysCustomer);
    }

    /**
     * Desintion:认证资料详情
     *
     * @author:jiangxiaoxiang
     * @param:CertificationDto认证资料对象
     * @return:ServerResponse
     */
    @PostMapping("/get")
    @ApiOperation(value = "认证资料详情")
    public ServerResponse get(@RequestBody RequestParams<CertificationDto> requestParams) {
        if (requestParams.getParams() == null ||
                requestParams.getParams().getId() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return shipperService.get(requestParams.getParams());
    }

    /**
     * Desintion:认证资料详情
     *
     * @author:jiangxiaoxiang
     * @param:CertificationDto认证资料对象
     * @return:ServerResponse
     */
    @PostMapping("/detail")
    @ApiOperation(value = "认证资料详情")
    public ServerResponse detail(@RequestBody RequestParams<CertificationDto> requestParams) {
        if (requestParams.getParams() == null ||
                requestParams.getParams().getId() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return shipperService.detail(requestParams.getParams());
    }


    /**
     * Desintion:认证资料审核
     *
     * @author:jiangxiaoxiang
     * @param:CertificationDto认证资料对象
     * @return:ServerResponse
     */
    @PostMapping("/certificationAudit")
    @ApiOperation(value = "认证资料审核")
    public ServerResponse certificationAudit(@RequestBody RequestParams<CertificationDto> requestParams, @CurrentUser User user) {
        if (requestParams.getParams() == null ||
                requestParams.getParams().getId() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return shipperService.certificationAudit(requestParams.getParams(), user);
    }

    /**
     * Desintion:设置免审核(认证资料)
     *
     * @author:jiangxiaoxiang
     * @param:CertificationDto认证资料对象
     * @return:ServerResponse
     */
    @PostMapping("/withOutAudit")
    @ApiOperation(value = "认证资料免审核")
    public ServerResponse withOutAudit(@RequestBody RequestParams<CertificationDto> requestParams) {
        if (requestParams.getParams() == null ||
                requestParams.getParams().getId() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return shipperService.withOutAudit(requestParams.getParams());
    }

    /**
     * Desintion:客户基本信息分页列表
     *
     * @author:jiangxiaoxiang
     * @param:CargoCustomerDto客户基本信息对象
     * @return:ServerResponse
     */
    @PostMapping("/page")
    @ApiOperation(value = "客户基本信息分页列表")
    public ServerResponse selectCargoPage(@RequestBody RequestParams<CargoCustomerDto> requestParams) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return shipperService.selectCargoPage(requestParams.getParams(), requestParams.getPage());
    }

    /**
     * Desintion:客户基本信息详情
     *
     * @author:jiangxiaoxiang
     * @param:CargoCustomerDto客户基本信息对象
     * @return:ServerResponse
     */
    @PostMapping("/id")
    @ApiOperation(value = "客户基本信息详情")
    public ServerResponse id(@RequestBody RequestParams<CargoCustomerDto> requestParams) {
        if (requestParams.getParams() == null ||
                requestParams.getParams().getId() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return shipperService.id(requestParams.getParams());
    }

    /**
     * Desintion:客户修改头像接口
     *
     * @author:jiangxiaoxiang
     * @param:CargoCustomerDto客户基本信息对象
     * @return:ServerResponse
     */
    @PostMapping("/update")
    @ApiOperation(value = "客户修改头像")
    public ServerResponse update(@RequestBody RequestParams<CargoCustomerDto> requestParams) {
        if (requestParams.getParams() == null ||
                requestParams.getParams().getId() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return shipperService.update(requestParams.getParams());
    }

    /**
     * Desintion:客户启用禁用设置
     *
     * @author:jiangxiaoxiang
     * @param:CargoCustomerDto客户dto
     * @return:ServerResponse
     */
    @PostMapping("/enableOrUnable")
    @ApiOperation(value = "客户启用禁用")
    public ServerResponse enableOrUnable(@RequestBody RequestParams<CargoCustomerDto> requestParams) {
        if (requestParams.getParams() == null ||
                requestParams.getParams().getId() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return shipperService.enableOrUnable(requestParams.getParams());
    }
}
