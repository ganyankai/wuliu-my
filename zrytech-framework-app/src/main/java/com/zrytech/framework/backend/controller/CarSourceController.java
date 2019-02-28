package com.zrytech.framework.backend.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zrytech.framework.dto.CarSourcePageDto;
import com.zrytech.framework.dto.CheckDto;
import com.zrytech.framework.dto.DetailsDto;
import com.zrytech.framework.service.CarSourceService;
import com.zrytech.framework.base.annotation.CurrentUser;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.common.entity.User;

/**
 * 后台管理系统 - 车源
 * 
 * @author cat
 *
 */
@RestController
@RequestMapping("/admin/carSource")
public class CarSourceController {

	@Autowired
	private CarSourceService carSourceService;

	/**
	 * 车源分页
	 * 
	 * @param requestParams
	 * @param result
	 * @param user
	 * @return
	 */
	@Valid
	@RequestMapping("/page")
	public ServerResponse page(@RequestBody @Valid RequestParams<CarSourcePageDto> requestParams, BindingResult result,
			@CurrentUser User user) {
		return carSourceService.page(requestParams.getParams(), requestParams.getPage().getPageNum(),
				requestParams.getPage().getPageSize());
	}
	
	
	/**
	 * 车源详情
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
		return carSourceService.details(requestParams.getParams().getId());
	}

	
	/**
	 * 车源审核
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
		return carSourceService.check(requestParams.getParams(), user);
	}
	
	
}
