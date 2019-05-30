package com.zrytech.framework.app.front.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zrytech.framework.app.ano.CarOwnerRole;
import com.zrytech.framework.app.ano.CargoOwnerRole;
import com.zrytech.framework.app.dto.focus.MyFocusPersonAddDto;
import com.zrytech.framework.app.service.MyFocusPersonService;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;

/**
 * 前台 - 车主货主关注
 * 
 * @author cat
 *
 */
@RestController
@RequestMapping("/api/myFocusPerson")
public class MyFocusPersonAPIController {

	@Autowired
	private MyFocusPersonService service;

	@CargoOwnerRole
	@Valid
	@RequestMapping("/addOrDelFoucsCar")
	public ServerResponse addOrDelFoucsCar(@RequestBody @Valid RequestParams<MyFocusPersonAddDto> requestParams,
			BindingResult result) {
		return service.addOrDelFoucsCar(requestParams.getParams());
	}

	@CargoOwnerRole
	@Valid
	@RequestMapping("/foucsCarList")
	public ServerResponse foucsCarList() {
		return service.foucsCarList();
	}

	@CarOwnerRole
	@Valid
	@RequestMapping("/addOrDelFoucsCargo")
	public ServerResponse addOrDelFoucsCargo(@RequestBody @Valid RequestParams<MyFocusPersonAddDto> requestParams,
			BindingResult result) {
		return service.addOrDelFoucsCargo(requestParams.getParams());
	}

	@CarOwnerRole
	@Valid
	@RequestMapping("/foucsCargoList")
	public ServerResponse foucsCargoList() {
		return service.foucsCargoList();
	}

}
