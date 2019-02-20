package com.zry.framework.backend.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zry.framework.dto.CargoMatterPageDto;
import com.zry.framework.dto.DetailsDto;
import com.zry.framework.service.CargoMatterService;
import com.zrytech.framework.base.annotation.CurrentUser;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.common.entity.User;

/**
 * 后台管理系统 - 报价单
 * 
 * @author cat
 *
 */
@RestController
@RequestMapping("/admin/cargoMatter")
public class CargoMatterController {

	@Autowired
	private CargoMatterService cargoMatterService;

	/**
	 * 报价单分页
	 * 
	 * @param requestParams
	 * @param result
	 * @param user
	 * @return
	 */
	@Valid
	@RequestMapping("/page")
	public ServerResponse page(@RequestBody @Valid RequestParams<CargoMatterPageDto> requestParams, BindingResult result,
			@CurrentUser User user) {
		return cargoMatterService.page(requestParams.getParams(), requestParams.getPage().getPageNum(),
				requestParams.getPage().getPageSize());
	}
	
	
	/**
	 * 报价单详情
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
		return cargoMatterService.details(requestParams.getParams().getId());
	}
	
	
}
