package com.zrytech.framework.app.front.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zrytech.framework.app.ano.CarOwnerRole;
import com.zrytech.framework.app.ano.CargoOwnerRole;
import com.zrytech.framework.app.ano.NeedCertified;
import com.zrytech.framework.app.dto.evaluate.EvaluateAddDto;
import com.zrytech.framework.app.dto.evaluate.EvaluateSearchDto;
import com.zrytech.framework.app.service.EvaluateService;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;

/**
 * 前台 - 评价
 * 
 * @author cat
 *
 */
@RestController
@RequestMapping("/api/evaluate")
public class EvaluateAPIController {

	@Autowired
	private EvaluateService service;

	@NeedCertified
	@CarOwnerRole
	@Valid
	@PostMapping("/carEvaluateCargo")
	public ServerResponse carEvaluateCargo(@RequestBody @Valid RequestParams<EvaluateAddDto> requestParams,
			BindingResult result) {
		return service.carEvaluateCargo(requestParams.getParams());
	}

	@NeedCertified
	@CargoOwnerRole
	@Valid
	@PostMapping("/cargoEvaluateCar")
	public ServerResponse cargoEvaluateCar(@RequestBody @Valid RequestParams<EvaluateAddDto> requestParams,
			BindingResult result) {
		return service.cargoEvaluateCar(requestParams.getParams());
	}

	@NeedCertified
	@CarOwnerRole
	@Valid
	@PostMapping("/fromCargo")
	public ServerResponse fromCargo(@RequestBody @Valid RequestParams<EvaluateSearchDto> requestParams,
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
		return service.fromCargo(requestParams.getParams(), pageNum, pageSize);
	}

	@NeedCertified
	@CarOwnerRole
	@Valid
	@PostMapping("/toCargo")
	public ServerResponse toCargo(@RequestBody @Valid RequestParams<EvaluateSearchDto> requestParams,
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
		return service.toCargo(requestParams.getParams(), pageNum, pageSize);
	}

	@NeedCertified
	@CargoOwnerRole
	@Valid
	@PostMapping("/fromCar")
	public ServerResponse fromCar(@RequestBody @Valid RequestParams<EvaluateSearchDto> requestParams,
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
		return service.fromCar(requestParams.getParams(), pageNum, pageSize);
	}

	@NeedCertified
	@CargoOwnerRole
	@Valid
	@PostMapping("/toCar")
	public ServerResponse toCar(@RequestBody @Valid RequestParams<EvaluateSearchDto> requestParams,
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
		return service.toCar(requestParams.getParams(), pageNum, pageSize);
	}

	@Valid
	@PostMapping("/openPage")
	public ServerResponse openPage(@RequestBody @Valid RequestParams<EvaluateSearchDto> requestParams,
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
		return service.openPage(requestParams.getParams(), pageNum, pageSize);
	}

}
