package com.zry.framework.backend.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zry.framework.dto.CarCargoOwnnerPageDto;
import com.zry.framework.dto.CheckDto;
import com.zry.framework.dto.DetailsDto;
import com.zry.framework.service.CarCargoOwnerService;
import com.zrytech.framework.base.annotation.CurrentUser;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.common.entity.User;

/**
 * 后台管理系统 - 车主货主
 * 
 * @author cat
 *
 */
@RestController
@RequestMapping("/admin/carCargoOwner")
public class CarCargoOwnerController {

	@Autowired
	private CarCargoOwnerService carCargoOwnerService;

	
	/**
	 * 车主货主分页
	 * 
	 * @param requestParams
	 * @param result
	 * @param user
	 * @return
	 */
	@Valid
	@RequestMapping("/page")
	public ServerResponse page(@RequestBody @Valid RequestParams<CarCargoOwnnerPageDto> requestParams, BindingResult result,
			@CurrentUser User user) {
		return carCargoOwnerService.page(requestParams.getParams(), requestParams.getPage().getPageNum(),
				requestParams.getPage().getPageSize());
	}
	
	
	/**
	 * 车主货主详情
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
		return carCargoOwnerService.details(requestParams.getParams().getId());
	}

	
	/**
	 * 车辆审核
	 * 
	 * @param requestParams
	 * @param result
	 * @param user
	 * @return
	 */
	@Valid
	@RequestMapping("/check")
	public ServerResponse check(@RequestBody @Valid RequestParams<CheckDto> requestParams, BindingResult result,
			@CurrentUser User user) {
		return carCargoOwnerService.check(requestParams.getParams(), user);
	}
	
	
}
