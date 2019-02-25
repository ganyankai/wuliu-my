package com.zry.framework.front.controller;

import com.zry.framework.dto.WaybillDto;
import com.zry.framework.service.WaybillService;
import com.zrytech.framework.base.annotation.CurrentCustomer;
import com.zrytech.framework.base.entity.Customer;
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

/**
 * 运单
 * @author jxx
 *
 */
@Api(description = "移动端运单相关api")
@RestController
@RequestMapping("/font/waybill")
public class WaybillApiController {

    @Autowired
    private WaybillService waybillService;


    /**
     * @Desinition:运单分页列表展示
     * @param:requestParams
     * @param:WaybillDto运单dto
     * @return
     */
    @PostMapping("/indentPage")
    @ApiOperation(value = "运单分页列表展示")
    public ServerResponse indentPage(@RequestBody RequestParams<WaybillDto> requestParams) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        //TODO:设置货主登录ID
        //  requestParams.getParams().setCargoOwnnerId();
        return waybillService.indentPage(requestParams.getParams(),requestParams.getPage());
    }

    /**
     * @Desinition:运单统计
     * @param:requestParams
     * @param:WaybillDto运单dto
     * @return
     */
    @PostMapping("/coundIndent")
    @ApiOperation(value = "运单统计")
    public ServerResponse coundIndent(@RequestBody RequestParams<WaybillDto> requestParams) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        //TODO:设置货主登录ID
        //  requestParams.getParams().setCargoOwnnerId();
        return waybillService.coundIndent(requestParams.getParams());
    }

    /**
     * @Desinition:运单详情
     * @param:requestParams
     * @param:WaybillDto运单dto
     * @return
     */
    @PostMapping("/get")
    @ApiOperation(value = "运单详情")
    public ServerResponse get(@RequestBody RequestParams<WaybillDto> requestParams) {
        if (requestParams.getParams() == null
                || requestParams.getParams().getId()==null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return waybillService.get(requestParams.getParams());
    }


    /**
     * @Desinition:创建运单
     * @param:requestParams
     * @param:WaybillDto运单dto
     * @return
     */
    @PostMapping("/createIndent")
    @ApiOperation(value = "创建运单")
    public ServerResponse createIndent(@RequestBody RequestParams<WaybillDto> requestParams, @CurrentCustomer Customer customer) {
        if (requestParams.getParams() == null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        requestParams.getParams().setCargoOwnnerId(customer.getId());
        return waybillService.createIndent(requestParams.getParams());
    }

    /**
     * @Desinition:待确认运单
     * @param:requestParams
     * @param:WaybillDto运单dto
     * @return
     */
    @PostMapping("/confirmIndent")
    @ApiOperation(value = "待确认运单")
    public ServerResponse confirmIndent(@RequestBody RequestParams<WaybillDto> requestParams) {
        if (requestParams.getParams() == null
                || requestParams.getParams().getId()==null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return waybillService.confirmIndent(requestParams.getParams());
    }

    /**
     * @Desinition:更改运单
     * @param:requestParams
     * @param:WaybillDto运单dto
     * @return
     */
    @PostMapping("/changeIndent")
    @ApiOperation(value = "更改运单")
    public ServerResponse changeIndent(@RequestBody RequestParams<WaybillDto> requestParams) {
        if (requestParams.getParams() == null
                || requestParams.getParams().getId()==null) {
            throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
        }
        return waybillService.changeIndent(requestParams.getParams());
    }

}
