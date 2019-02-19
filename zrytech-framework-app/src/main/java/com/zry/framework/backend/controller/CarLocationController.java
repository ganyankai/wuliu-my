package com.zry.framework.backend.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zry.framework.dto.CarLocationPageDto;
import com.zry.framework.service.CarLocationService;
import com.zrytech.framework.base.annotation.CurrentUser;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.common.entity.User;

/**
 * 后台管理系统 - 车辆位置
 * 
 * @author cat
 *
 */
@RestController
@RequestMapping("/admin/carLocation")
public class CarLocationController {

	@Autowired
	private CarLocationService carLocationService;

	/**
	 * 车辆位置分页
	 * 
	 * @param requestParams
	 * @param result
	 * @param user
	 * @return
	 */
	@Valid
	@RequestMapping("/page")
	public ServerResponse page(@RequestBody @Valid RequestParams<CarLocationPageDto> requestParams, BindingResult result,
			@CurrentUser User user) {
		return carLocationService.page(requestParams.getParams(), requestParams.getPage().getPageNum(),
				requestParams.getPage().getPageSize());
	}
	
	
	
}
