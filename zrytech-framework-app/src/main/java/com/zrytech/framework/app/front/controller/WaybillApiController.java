package com.zrytech.framework.app.front.controller;

import com.zrytech.framework.app.dto.DeleteDto;
import com.zrytech.framework.app.dto.DetailsDto;
import com.zrytech.framework.app.dto.WaybillDto;
import com.zrytech.framework.app.dto.waybill.CarOwnerWaybillPageDto;
import com.zrytech.framework.app.dto.waybilldetail.WaybillDetailAddDto;
import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.app.service.WaybillService;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.base.util.RequestUtil;
import com.zrytech.framework.common.entity.SysCustomer;
import com.zrytech.framework.common.enums.CommonResult;
import com.zrytech.framework.common.enums.ResultEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 运单
 *
 * @author jxx
 */
@Api(description = "移动端运单相关api")
@RestController
@RequestMapping("/font/waybill")
public class WaybillApiController {

    @Autowired
    private WaybillService waybillService;

    /**
     * @return
     * @Desinition:运单分页列表展示
     * @param:requestParams
     * @param:WaybillDto运单dto
     */
    @PostMapping("/indentPage")
    @ApiOperation(value = "运单分页列表展示")
    public ServerResponse indentPage(@RequestBody RequestParams<WaybillDto> requestParams) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        SysCustomer sysCustomer = RequestUtil.getCurrentUser(SysCustomer.class);
        requestParams.getParams().setCargoOwnnerId(sysCustomer.getId());
        return waybillService.indentPage(requestParams.getParams(), requestParams.getPage());
    }

    /**
     * @return
     * @Desinition:运单统计
     * @param:requestParams
     * @param:WaybillDto运单dto
     */
    @PostMapping("/coundIndent")
    @ApiOperation(value = "运单统计")
    public ServerResponse coundIndent(@RequestBody RequestParams<WaybillDto> requestParams) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        SysCustomer sysCustomer = RequestUtil.getCurrentUser(SysCustomer.class);
        requestParams.getParams().setCargoOwnnerId(sysCustomer.getId());
        return waybillService.coundIndent(requestParams.getParams());
    }

    /**
     * @return
     * @Desinition:运单详情
     * @param:requestParams
     * @param:WaybillDto运单dto
     */
    @PostMapping("/get")
    @ApiOperation(value = "运单详情")
    public ServerResponse get(@RequestBody RequestParams<WaybillDto> requestParams) {
        if (requestParams.getParams() == null
                || requestParams.getParams().getId() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return waybillService.get(requestParams.getParams());
    }


    /**
     * @return
     * @Desinition:创建运单
     * @param:requestParams
     * @param:WaybillDto运单dto
     */
    @PostMapping("/createIndent")
    @ApiOperation(value = "创建运单")
    public ServerResponse createIndent(@RequestBody RequestParams<WaybillDto> requestParams) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        SysCustomer sysCustomer = RequestUtil.getCurrentUser(SysCustomer.class);
        requestParams.getParams().setCargoOwnnerId(sysCustomer.getId());
        return waybillService.createIndent(requestParams.getParams());
    }

    /**
     * @return
     * @Desinition:待确认运单
     * @param:requestParams
     * @param:WaybillDto运单dto
     */
    @PostMapping("/confirmIndent")
    @ApiOperation(value = "待确认运单")
    public ServerResponse confirmIndent(@RequestBody RequestParams<WaybillDto> requestParams) {
        if (requestParams.getParams() == null
                || requestParams.getParams().getId() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return waybillService.confirmIndent(requestParams.getParams());
    }

    /**
     * @return
     * @Desinition:更改运单
     * @param:requestParams
     * @param:WaybillDto运单dto
     */
    @PostMapping("/changeIndent")
    @ApiOperation(value = "更改运单")
    public ServerResponse changeIndent(@RequestBody RequestParams<WaybillDto> requestParams) {
        if (requestParams.getParams() == null
                || requestParams.getParams().getId() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return waybillService.changeIndent(requestParams.getParams());
    }

    /**
     * @return
     * @Desinition:删除运单
     * @param:requestParams
     * @param:WaybillDto运单dto
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除运单")
    public ServerResponse delete(@RequestBody RequestParams<WaybillDto> requestParams) {
        if (requestParams.getParams() == null
                || requestParams.getParams().getId() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return waybillService.delete(requestParams.getParams());
    }

    /**
     * @return
     * @Desinition:取消运单
     * @param:requestParams
     * @param:WaybillDto运单dto
     */
    @PostMapping("/cancelIndent")
    @ApiOperation(value = "取消运单")
    public ServerResponse cancelIndent(@RequestBody RequestParams<WaybillDto> requestParams) {
        if (requestParams.getParams() == null
                || requestParams.getParams().getId() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return waybillService.cancelIndent(requestParams.getParams());
    }
    
    
    /**
     * 新增运单项
     * @author cat
     * 
     * @param requestParams
     * @param result
     * @return
     */
    @Valid
    @PostMapping("/addWaybillDetail")
    public ServerResponse addWaybillDetail(@RequestBody @Valid RequestParams<WaybillDetailAddDto> requestParams, BindingResult result) {
        Customer customer = RequestUtil.getCurrentUser(Customer.class);
        return waybillService.addWaybillDetail(requestParams.getParams(), customer);
    }
    
    
    /**
     * 删除运单项
     * @author cat
     * 
     * @param requestParams
     * @param result
     * @return
     */
    @Valid
    @PostMapping("/deleteWaybillDetail")
    public ServerResponse deleteWaybillDetail(@RequestBody @Valid RequestParams<DeleteDto> requestParams, BindingResult result) {
        Customer customer = RequestUtil.getCurrentUser(Customer.class);
        return waybillService.deleteWaybillDetail(requestParams.getParams(), customer);
    }
    
    
    /**
     * 删除运单装卸地
     * @author cat
     * 
     * @param requestParams
     * @param result
     * @return
     */
    @Valid
    @PostMapping("/deleteBillLocation")
    public ServerResponse deleteBillLocation(@RequestBody @Valid RequestParams<DeleteDto> requestParams, BindingResult result) {
        Customer customer = RequestUtil.getCurrentUser(Customer.class);
        return waybillService.deleteBillLocation(requestParams.getParams(), customer);
    }
    
    
    /**
     * 车主及车主子账号 - 运单分页
     * @author cat
     * 
     * @param requestParams
     * @param result
     * @return
     */
    @Valid
    @PostMapping("/page")
    public ServerResponse page(@RequestBody @Valid RequestParams<CarOwnerWaybillPageDto> requestParams, BindingResult result) {
        Customer customer = RequestUtil.getCurrentUser(Customer.class);
		return waybillService.page(requestParams.getParams(), requestParams.getPage().getPageNum(),
				requestParams.getPage().getPageSize(), customer);
    }
    
    
    /**
     * 车主及车主子账号 - 运单详情
     * @author cat
     * 
     * @param requestParams
     * @param result
     * @return
     */
    @Valid
    @PostMapping("/details")
    public ServerResponse details(@RequestBody @Valid RequestParams<DetailsDto> requestParams, BindingResult result) {
        Customer customer = RequestUtil.getCurrentUser(Customer.class);
		return waybillService.details(requestParams.getParams(), customer);
    }
}
