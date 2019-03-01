package com.zrytech.framework.app.front.controller;

import com.zrytech.framework.app.dto.WaybillDto;
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
import org.springframework.beans.factory.annotation.Autowired;
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
}
