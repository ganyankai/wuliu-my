package com.zrytech.framework.app.front.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zrytech.framework.app.ano.CarOwnerRole;
import com.zrytech.framework.app.ano.CargoOwnerRole;
import com.zrytech.framework.app.dto.CargoMatterPageDto;
import com.zrytech.framework.app.dto.CommonDto;
import com.zrytech.framework.app.dto.cargomatter.CargoMatterAddDto;
import com.zrytech.framework.app.dto.cargomatter.CargoMatterUpdateDto;
import com.zrytech.framework.app.service.CargoMatterService;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;

/**
 * 前台 - 报价单
 * 
 * @author cat
 *
 */
@RestController
@RequestMapping("/api/cargoMatter")
public class CargoMatterAPIController {

	@Autowired
	private CargoMatterService service;

	@CarOwnerRole
	@Valid
	@RequestMapping("/add")
	public ServerResponse add(@RequestBody @Valid RequestParams<CargoMatterAddDto> requestParams,
			BindingResult result) {
		return service.add(requestParams.getParams());
	}

	@CarOwnerRole
	@Valid
	@RequestMapping("/update")
	public ServerResponse update(@RequestBody @Valid RequestParams<CargoMatterUpdateDto> requestParams,
			BindingResult result) {
		return service.update(requestParams.getParams());
	}

	@CarOwnerRole
	@Valid
	@RequestMapping("/delete")
	public ServerResponse delete(@RequestBody @Valid RequestParams<CommonDto> requestParams, BindingResult result) {
		return service.delete(requestParams.getParams());
	}

	@CarOwnerRole
	@Valid
	@RequestMapping("/carOwnerCargoMatterPage")
	public ServerResponse carOwnerCargoMatterPage(@RequestBody @Valid RequestParams<CargoMatterPageDto> requestParams,
			BindingResult result) {
		Page page = requestParams.getPage();
		if (page == null) {
			page = new Page(1, 10);
		}
		Integer pageNum = page.getPageNum();
		Integer pageSize = page.getPageSize();
		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		return service.carOwnerCargoMatterPage(requestParams.getParams(), pageNum, pageSize);
	}

	@CarOwnerRole
	@Valid
	@RequestMapping("/carOwnerCargoMatterDetails")
	public ServerResponse carOwnerCargoMatterDetails(@RequestBody @Valid RequestParams<CommonDto> requestParams,
			BindingResult result) {
		return service.carOwnerCargoMatterDetails(requestParams.getParams());
	}
	
	@CarOwnerRole
	@Valid
	@RequestMapping("/bid")
	public ServerResponse bid(@RequestBody @Valid RequestParams<CommonDto> requestParams, BindingResult result) {
		return service.bid(requestParams.getParams());
	}
	
	
	
	
	
	// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	// 货主前台接口
	
	
	@CargoOwnerRole
	@Valid
	@RequestMapping("/cargoOwnerGetCargoMatterByCargoId")
	public ServerResponse cargoOwnerGetCargoMatterByCargoId(
			@RequestBody @Valid RequestParams<CargoMatterPageDto> requestParams, BindingResult result) {
		return service.cargoOwnerGetCargoMatterByCargoId(requestParams.getParams());
	}

	@CargoOwnerRole
	@Valid
	@RequestMapping("/tender")
	public ServerResponse tender(@RequestBody @Valid RequestParams<CommonDto> requestParams, BindingResult result) {
		return service.tender(requestParams.getParams());
	}
	
	
}
