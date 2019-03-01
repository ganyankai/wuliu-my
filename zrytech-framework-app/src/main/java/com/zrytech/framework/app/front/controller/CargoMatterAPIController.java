package com.zrytech.framework.app.front.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zrytech.framework.app.dto.CargoMatterPageDto;
import com.zrytech.framework.app.dto.DetailsDto;
import com.zrytech.framework.app.dto.cargomatter.CargoMatterAddDto;
import com.zrytech.framework.app.dto.cargomatter.CargoMatterUpdateDto;
import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.app.service.CargoMatterService;
import com.zrytech.framework.base.annotation.CurrentCustomer;
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
			@CurrentCustomer Customer customer) {
		return cargoMatterService.page(requestParams.getParams(), requestParams.getPage().getPageNum(),
				requestParams.getPage().getPageSize(), customer);
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
			@CurrentCustomer Customer customer) {
		return cargoMatterService.details(requestParams.getParams(), customer);
	}
	
	
	/**
	 * 报价
	 * 
	 * @param requestParams
	 * @param result
	 * @param user
	 * @return
	 */
	@Valid
	@RequestMapping("/add")
	public ServerResponse add(@RequestBody @Valid RequestParams<CargoMatterAddDto> requestParams, BindingResult result,
			@CurrentCustomer Customer customer) {
		return cargoMatterService.add(requestParams.getParams(), customer);
	}
	
	
	/**
	 * 更新报价单
	 * 
	 * @param requestParams
	 * @param result
	 * @param user
	 * @return
	 */
	@Valid
	@RequestMapping("/update")
	public ServerResponse update(@RequestBody @Valid RequestParams<CargoMatterUpdateDto> requestParams, BindingResult result,
			@CurrentCustomer Customer customer) {
		return cargoMatterService.update(requestParams.getParams(), customer);
	}
}
