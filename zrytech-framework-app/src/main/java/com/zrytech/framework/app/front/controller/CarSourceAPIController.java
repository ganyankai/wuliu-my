package com.zrytech.framework.app.front.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zrytech.framework.app.dto.DetailsDto;
import com.zrytech.framework.app.dto.carrecordplace.CarRecordPlaceSaveDto;
import com.zrytech.framework.app.dto.carsource.CarSourceAddDto;
import com.zrytech.framework.app.dto.carsource.CarSourceCheckUpdateDto;
import com.zrytech.framework.app.dto.carsource.CarSourcePageDto;
import com.zrytech.framework.app.dto.carsourcecar.CarSourceCarSaveDto;
import com.zrytech.framework.app.entity.CarSource;
import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.app.service.CarSourceService;
import com.zrytech.framework.base.entity.PageData;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.util.RequestUtil;

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
	 * 车主及车主子账号 - 发布车源
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@PostMapping("/add")
	public ServerResponse add(@RequestBody @Valid RequestParams<CarSourceAddDto> requestParams, BindingResult result) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		return carSourceService.createCarSource(requestParams.getParams(), customer);
	}
	
	
	/**
	 * 车主及车主子账号 - 我的车源分页
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@PostMapping("/myCarSourcePage")
	public ServerResponse myCarSourcePage(@RequestBody @Valid RequestParams<CarSourcePageDto> requestParams,
			BindingResult result) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		Integer pageNum = requestParams.getPage().getPageNum();
		Integer pageSize = requestParams.getPage().getPageSize();
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		CarSourcePageDto dto = requestParams.getParams();
		dto.setCarOwnerId(customer.getCarOwner().getId());
		PageData<CarSource> pageData = carSourceService.carSourcePage(dto, pageNum, pageSize);
		return ServerResponse.successWithData(pageData);
	}

	
	/**
	 * 车主及车主子账号 - 车源详情
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@PostMapping("/details")
	public ServerResponse details(@RequestBody @Valid RequestParams<DetailsDto> requestParams, BindingResult result) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		return carSourceService.details(requestParams.getParams(), customer);
	}
	
	
	/**
	 * 车主及车主子账号 - 修改车源基本信息需要审核的字段
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@PostMapping("/updateNeedApprove")
	public ServerResponse updateNeedApprove(@RequestBody @Valid RequestParams<CarSourceCheckUpdateDto> requestParams,
			BindingResult result) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		return carSourceService.updateNeedApprove(requestParams.getParams(), customer);
	}
	
	
	/**
	 * 车主及车主子账号 - 修改起止地（包含起止地的更新和添加）
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@PostMapping("/saveLine")
	public ServerResponse saveLine(@RequestBody @Valid RequestParams<CarRecordPlaceSaveDto> requestParams,
			BindingResult result) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		return carSourceService.addOrUpdateCarRecordPlace(requestParams.getParams(), customer);
	}
	
	
	/**
	 * 车主及车主子账号 - 修改车辆（包含车辆的更新和添加）
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@PostMapping("/saveCarSourceCar")
	public ServerResponse saveCarSourceCar(@RequestBody @Valid RequestParams<CarSourceCarSaveDto> requestParams,
			BindingResult result) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		return carSourceService.saveCarSourceCar(requestParams.getParams(), customer);
	}
	
}
