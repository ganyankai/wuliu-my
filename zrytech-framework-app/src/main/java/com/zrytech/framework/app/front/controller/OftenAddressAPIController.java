package com.zrytech.framework.app.front.controller;

import com.zrytech.framework.app.ano.NeedCertified;
import com.zrytech.framework.app.dto.CommonDto;
import com.zrytech.framework.app.dto.oftenaddress.OftenAddressAddDto;
import com.zrytech.framework.app.dto.oftenaddress.OftenAddressUpdateDto;
import com.zrytech.framework.app.service.OftenAddressService;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 常用路线
 * 
 * @author cat
 *
 */
@RestController
@RequestMapping("/api/usedAddress")
public class OftenAddressAPIController {

	@Autowired
	private OftenAddressService service;

	@PostMapping("/list")
	public ServerResponse list() {
		return service.list();
	}

	@NeedCertified
	@Valid
	@PostMapping("/add")
	public ServerResponse add(@RequestBody @Valid RequestParams<OftenAddressAddDto> requestParams,
			BindingResult result) {
		return service.save(requestParams.getParams());
	}

	@NeedCertified
	@Valid
	@PostMapping("/get")
	public ServerResponse details(@RequestBody @Valid RequestParams<CommonDto> requestParams, BindingResult result) {
		return service.details(requestParams.getParams());
	}

	@NeedCertified
	@Valid
	@PostMapping("/update")
	public ServerResponse update(@RequestBody @Valid RequestParams<OftenAddressUpdateDto> requestParams,
			BindingResult result) {
		return service.update(requestParams.getParams());
	}

	@NeedCertified
	@Valid
	@PostMapping("/delete")
	public ServerResponse delete(@RequestBody @Valid RequestParams<CommonDto> requestParams, BindingResult result) {
		return service.delete(requestParams.getParams());
	}
}
