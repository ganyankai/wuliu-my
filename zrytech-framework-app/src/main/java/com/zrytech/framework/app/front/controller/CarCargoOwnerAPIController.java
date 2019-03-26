package com.zrytech.framework.app.front.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zrytech.framework.app.constants.CustomerConstants;
import com.zrytech.framework.app.dto.customer.CustomerRegisterDto;
import com.zrytech.framework.app.service.CarCargoOwnerService;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;


/**
 * 前台 - 车辆
 * 
 * @author cat
 *
 */
@RestController
@RequestMapping("/api/carCargoOwner")
public class CarCargoOwnerAPIController {

	@Autowired
	private CarCargoOwnerService carCargoOwnerService;

	
	
	/**
	 * 车主注册
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@RequestMapping("/carOwnerRegister")
	public ServerResponse carOwnerRegister(@RequestBody @Valid RequestParams<CustomerRegisterDto> requestParams,
			BindingResult result) {
		CustomerRegisterDto params = requestParams.getParams();
		params.setCustomerType(CustomerConstants.TYPE_CAR_OWNER);
		return carCargoOwnerService.register(params);
	}
	
	
	/**
	 * 货主注册
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@RequestMapping("/cargoOwnerRegister")
	public ServerResponse cargoOwnerRegister(@RequestBody @Valid RequestParams<CustomerRegisterDto> requestParams,
			BindingResult result) {
		CustomerRegisterDto params = requestParams.getParams();
		params.setCustomerType(CustomerConstants.TYPE_CARGO_OWNER);
		return carCargoOwnerService.register(params);
	}
	
	
}
