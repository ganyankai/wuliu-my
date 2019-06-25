package com.zrytech.framework.app.front.controller;

import javax.validation.Valid;

import com.zrytech.framework.base.entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zrytech.framework.app.ano.CarOwnerRole;
import com.zrytech.framework.app.ano.NeedCertified;
import com.zrytech.framework.app.dto.CommonDto;
import com.zrytech.framework.app.dto.car.CarAddDto;
import com.zrytech.framework.app.dto.car.CarCheckUpdateDto;
import com.zrytech.framework.app.dto.car.CarNoCheckUpdateDto;
import com.zrytech.framework.app.dto.car.CarPageDto;
import com.zrytech.framework.app.entity.Car;
import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.app.service.CarService;
import com.zrytech.framework.base.entity.PageData;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.util.RequestUtil;


/**
 * 前台 - 车辆
 * 
 * @author cat
 *
 */
@RestController
@RequestMapping("/api/car")
public class CarAPIController {

	@Autowired
	private CarService carService;

	
	/**
	 * 删除车辆
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@NeedCertified
	@CarOwnerRole
	@Valid
	@RequestMapping("/delete")
	public ServerResponse delete(@RequestBody @Valid RequestParams<CommonDto> requestParams, BindingResult result) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		return carService.delete(requestParams.getParams(), customer);
	}
	
	
	/**
	 * 修改车辆不需要审核的内容
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@NeedCertified
	@CarOwnerRole
	@Valid
	@RequestMapping("/updateNoCheck")
	public ServerResponse updateNoCheck(@RequestBody @Valid RequestParams<CarNoCheckUpdateDto> requestParams,
			BindingResult result) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		return carService.updateNoCheck(requestParams.getParams(), customer);
	}
	
	
	/**
	 * 修改车辆需要审核的内容
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@NeedCertified
	@CarOwnerRole
	@Valid
	@RequestMapping("/updateCheck")
	public ServerResponse updateCheck(@RequestBody @Valid RequestParams<CarCheckUpdateDto> requestParams,
			BindingResult result) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		return carService.updateCheck(requestParams.getParams(), customer);
	}
	
	
	/**
	 * 添加车辆
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @param
	 * @return
	 */
	@NeedCertified
	@CarOwnerRole
	@Valid
	@RequestMapping("/add")
	public ServerResponse add(@RequestBody @Valid RequestParams<CarAddDto> requestParams, BindingResult result) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		return carService.add(requestParams.getParams(), customer);
	}
	
	
	/**
	 * 车主及车主子账号 - 车辆详情
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@CarOwnerRole
	@Valid
	@RequestMapping("/details")
	public ServerResponse details(@RequestBody @Valid RequestParams<CommonDto> requestParams, BindingResult result) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		return carService.details(requestParams.getParams(), customer);
	}
	
	
	/**
	 * 车主及车主子账号 - 我的车辆分页
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@CarOwnerRole
	@Valid
	@RequestMapping("/myCarPage")
	public ServerResponse myCarPage(@RequestBody @Valid RequestParams<CarPageDto> requestParams, BindingResult result) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		Page page = requestParams.getPage();
		if (page == null) {
			page = new Page(1, 10);
		}
		Integer pageNum = page.getPageNum();
		Integer pageSize = page.getPageSize();
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		CarPageDto dto = requestParams.getParams();
		dto.setCarOwnerId(customer.getCarOwner().getId());
		dto.setIsDelete(false);
		PageData<Car> carPage = carService.carPage(dto, pageNum, pageSize);
		return ServerResponse.successWithData(carPage);
	}
	
	
	
}
