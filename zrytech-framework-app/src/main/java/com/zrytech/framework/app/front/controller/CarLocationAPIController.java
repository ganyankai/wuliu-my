package com.zrytech.framework.app.front.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zrytech.framework.app.dto.CommonDto;
import com.zrytech.framework.app.dto.carlocation.CarLocationAddDto;
import com.zrytech.framework.app.dto.carlocation.CarLocationPageDto;
import com.zrytech.framework.app.service.CarLocationService;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;


/**
 * 前台 - 车辆位置
 * 
 * @author cat
 *
 */
@RestController
@RequestMapping("/api/carLocation")
public class CarLocationAPIController {

	@Autowired
	private CarLocationService carLocationService;

	
	// TODO 需要确认哪些人可以调用新增车辆位置的接口
	/**
	 * 添加车辆位置
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@RequestMapping("/add")
	public ServerResponse add(@RequestBody @Valid RequestParams<CarLocationAddDto> requestParams, BindingResult result) {
		return carLocationService.add(requestParams.getParams());
	}
	
	
	/**
	 * 车辆位置分页
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@RequestMapping("/page")
	public ServerResponse page(@RequestBody @Valid RequestParams<CarLocationPageDto> requestParams, BindingResult result) {
		return carLocationService.page(requestParams.getParams(), requestParams.getPage().getPageNum(),
				requestParams.getPage().getPageSize());
	}
	
	
	/**
	 * 车辆位置分页
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@RequestMapping("/pageByCarId")
	public ServerResponse pageByCarId(@RequestBody @Valid RequestParams<CommonDto> requestParams, BindingResult result) {
		return carLocationService.page(requestParams.getParams().getId(), requestParams.getPage().getPageNum(),
				requestParams.getPage().getPageSize());
	}
	
	
}
