package com.zry.framework.backend.controller;

import com.zry.framework.dto.DetailsDto;
import com.zry.framework.dto.WaybillPageDto;
import com.zry.framework.service.WaybillService;
import com.zrytech.framework.base.annotation.CurrentUser;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.common.entity.User;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 运单
 * @author cat
 *
 */
@RestController
@RequestMapping("admin/waybill")
public class WayBillController {

    @Autowired
    private WaybillService waybillService;
    

	/**
	 * 运单分页
	 * 
	 * @param requestParams
	 * @param result
	 * @param user
	 * @return
	 */
	@Valid
	@RequestMapping("/page")
	public ServerResponse page(@RequestBody @Valid RequestParams<WaybillPageDto> requestParams, BindingResult result,
			@CurrentUser User user) {
		return waybillService.page(requestParams.getParams(), requestParams.getPage().getPageNum(),
				requestParams.getPage().getPageSize());
	}
	
	
	/**
	 * 运单详情
	 * 
	 * @param requestParams
	 * @param result
	 * @param user
	 * @return
	 */
	@Valid
	@RequestMapping("/details")
	public ServerResponse details(@RequestBody @Valid RequestParams<DetailsDto> requestParams, BindingResult result,
			@CurrentUser User user) {
		return waybillService.details(requestParams.getParams().getId());
	}
}
