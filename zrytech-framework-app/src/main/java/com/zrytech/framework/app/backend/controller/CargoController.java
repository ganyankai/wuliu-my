package com.zrytech.framework.app.backend.controller;

import com.zrytech.framework.app.ano.AdminRole;
import com.zrytech.framework.app.dto.CommonDto;
import com.zrytech.framework.app.dto.approve.ApproveDto;
import com.zrytech.framework.app.dto.cargosource.CargoSourceSearchDto;
import com.zrytech.framework.app.service.CargoService;
import com.zrytech.framework.base.entity.Page;
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
 * 货源
 */
@RestController
@RequestMapping("/goodsSource")
public class CargoController {

	@Autowired
	private CargoService service;

	@AdminRole
	@Valid
	@PostMapping("/adminCheckCargoSource")
	public ServerResponse adminCheckCargoSource(@RequestBody @Valid RequestParams<ApproveDto> requestParams,
			BindingResult result) {
		return service.adminCheckCargoSource(requestParams.getParams());
	}

	@AdminRole
	@Valid
	@PostMapping("/page")
	public ServerResponse cargoPage(@RequestBody @Valid RequestParams<CargoSourceSearchDto> requestParams,
			BindingResult result) {
		Page page = requestParams.getPage();
		if (page == null) {
			page = new Page(1, 10);
		}
		Integer pageNum = page.getPageNum();
		Integer pageSize = page.getPageSize();
		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		return service.adminPage(pageNum, pageSize, requestParams.getParams());
	}

	@AdminRole
	@Valid
	@PostMapping("/get")
	public ServerResponse get(@RequestBody @Valid RequestParams<CommonDto> requestParams, BindingResult result) {
		return service.adminDetails(requestParams.getParams());
	}

}
