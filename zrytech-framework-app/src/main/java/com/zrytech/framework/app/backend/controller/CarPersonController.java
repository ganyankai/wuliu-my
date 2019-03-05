package com.zrytech.framework.app.backend.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zrytech.framework.app.dto.CheckDto;
import com.zrytech.framework.app.dto.DetailsDto;
import com.zrytech.framework.app.dto.carperson.CarPersonPageDto;
import com.zrytech.framework.app.service.CarPersonService;
import com.zrytech.framework.base.annotation.CurrentUser;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.common.entity.User;

/**
 * 后台管理系统 - 司机与压货人
 * 
 * @author cat
 *
 */
@RestController
@RequestMapping("/admin/carPerson")
public class CarPersonController {

	@Autowired
	private CarPersonService carPersonService;

	/**
	 * 司机与压货人分页
	 * 
	 * @param requestParams
	 * @param result
	 * @param user
	 * @return
	 */
	@Valid
	@RequestMapping("/page")
	public ServerResponse page(@RequestBody @Valid RequestParams<CarPersonPageDto> requestParams, BindingResult result,
			@CurrentUser User user) {
		return carPersonService.page(requestParams.getParams(), requestParams.getPage().getPageNum(),
				requestParams.getPage().getPageSize());
	}
	
	
	/**
	 * 司机与压货人详情
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
		return carPersonService.details(requestParams.getParams().getId());
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
		return carPersonService.check(requestParams.getParams(), user);
	}
	
	
}
