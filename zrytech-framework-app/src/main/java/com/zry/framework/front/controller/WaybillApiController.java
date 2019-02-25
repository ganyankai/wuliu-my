package com.zry.framework.front.controller;

import com.zry.framework.dto.WaybillDto;
import com.zry.framework.service.WaybillService;
import com.zrytech.framework.base.annotation.CurrentCustomer;
import com.zrytech.framework.base.entity.Customer;
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
        requestParams.getParams().setCargoId(customer.getId());
        return waybillService.createIndent(requestParams.getParams());
    }

}
