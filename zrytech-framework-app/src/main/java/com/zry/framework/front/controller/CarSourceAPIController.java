package com.zry.framework.front.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zry.framework.dto.CarSourcePageDto;
import com.zry.framework.dto.CommonDto;
import com.zry.framework.dto.DetailsDto;
import com.zry.framework.dto.carrecordplace.CarRecordPlaceSaveDto;
import com.zry.framework.dto.carsource.CarSourceAddDto;
import com.zry.framework.dto.carsource.CarSourceCheckUpdateDto;
import com.zry.framework.dto.carsourcecar.CarSourceCarSaveDto;
import com.zry.framework.entity.Customer;
import com.zry.framework.service.CarSourceService;
import com.zrytech.framework.base.annotation.CurrentCustomer;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;

/**
 * 前台 - 车源
 * 
 * @author cat
 *
 */
@RestController
@RequestMapping("/api/carSource")
public class CarSourceAPIController {

	@Autowired
	private CarSourceService carSourceService;

	
	/**
	 * 车源分页
	 * 
	 * @param requestParams
	 * @param result
	 * @param customer
	 * @return
	 */
	@Valid
	@RequestMapping("/page")
	public ServerResponse page(@RequestBody @Valid RequestParams<CarSourcePageDto> requestParams, BindingResult result,
			@CurrentCustomer Customer customer) {
		return carSourceService.page(requestParams.getParams(), requestParams.getPage().getPageNum(),
				requestParams.getPage().getPageSize(), customer);
	}
	
	
	/**
	 * 车源详情
	 * 
	 * @param requestParams
	 * @param result
	 * @param customer
	 * @return
	 */
	@Valid
	@RequestMapping("/details")
	public ServerResponse details(@RequestBody @Valid RequestParams<DetailsDto> requestParams, BindingResult result,
			@CurrentCustomer Customer customer) {
		return carSourceService.details(requestParams.getParams(), customer);
	}

	
	/**
	 * 提交审核
	 * 
	 * @param requestParams
	 * @param result
	 * @param customer
	 * @return
	 */
	@Valid
	@RequestMapping("/submitAudit")
	public ServerResponse submitAudit(@RequestBody @Valid RequestParams<CommonDto> requestParams, BindingResult result,
			@CurrentCustomer Customer customer) {
		return carSourceService.submitAudit(requestParams.getParams(), customer);
	}
	
	
	/**
	 * 发布车源
	 * 
	 * @param requestParams
	 * @param result
	 * @param customer
	 * @return
	 */
	@Valid
	@RequestMapping("/add")
	public ServerResponse add(@RequestBody @Valid RequestParams<CarSourceAddDto> requestParams, BindingResult result,
			@CurrentCustomer Customer customer) {
		return carSourceService.add(requestParams.getParams(), customer);
	}
	
	
	/**
	 * 更新车源需审核字段
	 * 
	 * @param requestParams
	 * @param result
	 * @param customer
	 * @return
	 */
	@Valid
	@RequestMapping("/updateCheck")
	public ServerResponse updateCheck(@RequestBody @Valid RequestParams<CarSourceCheckUpdateDto> requestParams, BindingResult result,
			@CurrentCustomer Customer customer) {
		return carSourceService.updateCheck(requestParams.getParams(), customer);
	}
	
	
	
	/**
	 * 更新路线或新增路线
	 * 
	 * @param requestParams
	 * @param result
	 * @param customer
	 * @return
	 */
	@Valid
	@RequestMapping("/saveLine")
	public ServerResponse saveLine(@RequestBody @Valid RequestParams<CarRecordPlaceSaveDto> requestParams, BindingResult result,
			@CurrentCustomer Customer customer) {
		return carSourceService.saveLine(requestParams.getParams(), customer);
	}
	
	
	/**
	 * 车源之新增车辆或更新车辆
	 * 
	 * @param requestParams
	 * @param result
	 * @param customer
	 * @return
	 */
	@Valid
	@RequestMapping("/saveCarSourceCar")
	public ServerResponse saveCarSourceCar(@RequestBody @Valid RequestParams<CarSourceCarSaveDto> requestParams, BindingResult result,
			@CurrentCustomer Customer customer) {
		return carSourceService.saveCarSourceCar(requestParams.getParams(), customer);
	}
}
