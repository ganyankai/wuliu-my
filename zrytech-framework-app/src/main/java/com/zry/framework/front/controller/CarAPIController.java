package com.zry.framework.front.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zry.framework.dto.CarAddDto;
import com.zry.framework.dto.CarCheckUpdateDto;
import com.zry.framework.dto.CarNoCheckUpdateDto;
import com.zry.framework.dto.CommonDto;
import com.zry.framework.dto.DeleteDto;
import com.zry.framework.dto.DetailsDto;
import com.zry.framework.dto.car.CarOwnerCarPageDto;
import com.zry.framework.entity.Customer;
import com.zry.framework.service.CarService;
import com.zrytech.framework.base.annotation.CurrentCustomer;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;


/**
 * 前台 - 车辆
 * 
 * @author cat
 *
 */
@RestController
@RequestMapping("/api/car")
public class CarAPIController {

	@Autowired
	private CarService carService;

	
	/**
	 * 删除车辆
	 * 
	 * @param requestParams
	 * @param result
	 * @param customer	车主或者车主子账号
	 * @return
	 */
	@Valid
	@RequestMapping("/delete")
	public ServerResponse delete(@RequestBody @Valid RequestParams<DeleteDto> requestParams, BindingResult result,
			@CurrentCustomer Customer customer) {
		return carService.delete(requestParams.getParams(), customer);
	}
	
	
	/**
	 * 提交审核
	 * 
	 * @param requestParams
	 * @param result
	 * @param customer	车主或者车主子账号
	 * @return
	 */
	@Valid
	@RequestMapping("/submitAudit")
	public ServerResponse submitAudit(@RequestBody @Valid RequestParams<CommonDto> requestParams, BindingResult result,
			@CurrentCustomer Customer customer) {
		return carService.submitAudit(requestParams.getParams(), customer);
	}

	
	/**
	 * 修改车辆不需要审核的内容
	 * 
	 * @param requestParams
	 * @param result
	 * @param customer	车主或者车主子账号
	 * @return
	 */
	@Valid
	@RequestMapping("/updateNoCheck")
	public ServerResponse updateNoCheck(@RequestBody @Valid RequestParams<CarNoCheckUpdateDto> requestParams, BindingResult result,
			@CurrentCustomer Customer customer) {
		return carService.updateNoCheck(requestParams.getParams(), customer);
	}
	
	
	/**
	 * 修改车辆需要审核的内容
	 * 
	 * @param requestParams
	 * @param result
	 * @param customer	车主或者车主子账号
	 * @return
	 */
	@Valid
	@RequestMapping("/updateCheck")
	public ServerResponse updateCheck(@RequestBody @Valid RequestParams<CarCheckUpdateDto> requestParams, BindingResult result,
			@CurrentCustomer Customer customer) {
		return carService.updateCheck(requestParams.getParams(), customer);
	}
	
	
	/**
	 * 添加车辆
	 * 
	 * @param requestParams
	 * @param result
	 * @param customer	车主或者车主子账号
	 * @return
	 */
	@Valid
	@RequestMapping("/add")
	public ServerResponse add(@RequestBody @Valid RequestParams<CarAddDto> requestParams, BindingResult result,
			@CurrentCustomer Customer customer) {
		return carService.add(requestParams.getParams(), customer);
	}
	
	
	/**
	 * 车辆详情
	 * 
	 * @param requestParams
	 * @param result
	 * @param customer	车主或者车主子账号
	 * @return
	 */
	@Valid
	@RequestMapping("/details")
	public ServerResponse details(@RequestBody @Valid RequestParams<DetailsDto> requestParams, BindingResult result,
			@CurrentCustomer Customer customer) {
		return carService.details(requestParams.getParams(), customer);
	}
	
	
	/**
	 * 车辆分页
	 * 
	 * @param requestParams
	 * @param result
	 * @param customer	车主或者车主子账号
	 * @return
	 */
	@Valid
	@RequestMapping("/page")
	public ServerResponse page(@RequestBody @Valid RequestParams<CarOwnerCarPageDto> requestParams, BindingResult result,
			@CurrentCustomer Customer customer) {
		return carService.page(requestParams.getParams(), requestParams.getPage().getPageNum(),
				requestParams.getPage().getPageSize(), customer);
	}
	
	
}
