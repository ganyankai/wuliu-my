package com.zrytech.framework.app.backend.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zrytech.framework.app.dto.CheckDto;
import com.zrytech.framework.app.dto.DetailsDto;
import com.zrytech.framework.app.dto.car.CarPageDto;
import com.zrytech.framework.app.service.CarService;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.util.RequestUtil;
import com.zrytech.framework.common.entity.User;

/**
 * 后台管理系统 - 车辆
 * 
 * @author cat
 *
 */
@RestController
@RequestMapping("/admin/car")
public class CarController {

	@Autowired
	private CarService carService;
	

	/**
	 * 车辆分页
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@RequestMapping("/page")
	public ServerResponse page(@RequestBody @Valid RequestParams<CarPageDto> requestParams, BindingResult result) {
		Integer pageNum = requestParams.getPage().getPageNum();
		Integer pageSize = requestParams.getPage().getPageSize();
		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		return carService.page(requestParams.getParams(), pageNum, pageSize);
	}
	
	
	/**
	 * 车辆详情
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@RequestMapping("/details")
	public ServerResponse details(@RequestBody @Valid RequestParams<DetailsDto> requestParams, BindingResult result) {
		return carService.details(requestParams.getParams());
	}

	
	/**
	 * 车辆审核
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @param user
	 * @return
	 */
	@Valid
	@RequestMapping("/check")
	public ServerResponse check(@RequestBody @Valid RequestParams<CheckDto> requestParams, BindingResult result) {
		User user = RequestUtil.getCurrentUser(User.class);
		return carService.check(requestParams.getParams(), user);
	}
	
	
}
