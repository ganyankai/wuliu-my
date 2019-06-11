package com.zrytech.framework.app.front.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zrytech.framework.app.ano.NeedCertified;
import com.zrytech.framework.app.dto.CommonDto;
import com.zrytech.framework.app.dto.focus.MyFocusLineAddDto;
import com.zrytech.framework.app.dto.focus.MyFocusLineUpdateDto;
import com.zrytech.framework.app.service.MyFocusLineService;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;

/**
 * 前台 - 车主货主关注的路线
 * 
 * @author cat
 *
 */
@RestController
@RequestMapping("/api/myFocusLine")
public class MyFocusLineAPIController {

	@Autowired
	private MyFocusLineService service;

	@Valid
	@RequestMapping("/save")
	public ServerResponse save(@RequestBody @Valid RequestParams<MyFocusLineAddDto> requestParams,
			BindingResult result) {
		return service.save(requestParams.getParams());
	}

	@Valid
	@RequestMapping("/update")
	public ServerResponse update(@RequestBody @Valid RequestParams<MyFocusLineUpdateDto> requestParams,
			BindingResult result) {
		return service.update(requestParams.getParams());
	}

	@Valid
	@RequestMapping("/delete")
	public ServerResponse delete(@RequestBody @Valid RequestParams<CommonDto> requestParams, BindingResult result) {
		return service.delete(requestParams.getParams());
	}

	@Valid
	@RequestMapping("/details")
	public ServerResponse details(@RequestBody @Valid RequestParams<CommonDto> requestParams, BindingResult result) {
		return service.details(requestParams.getParams());
	}

	@Valid
	@RequestMapping("/list")
	public ServerResponse list() {
		return service.list();
	}

}
