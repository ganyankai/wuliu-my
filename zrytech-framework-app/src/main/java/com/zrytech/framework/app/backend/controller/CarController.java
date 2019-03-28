package com.zrytech.framework.app.backend.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zrytech.framework.app.ano.AdminRole;
import com.zrytech.framework.app.constants.ApproveConstants;
import com.zrytech.framework.app.dto.DetailsDto;
import com.zrytech.framework.app.dto.approve.ApproveDto;
import com.zrytech.framework.app.dto.car.CarPageDto;
import com.zrytech.framework.app.entity.Car;
import com.zrytech.framework.app.service.CarService;
import com.zrytech.framework.base.entity.PageData;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;
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
	 * 管理员 - 车辆分页
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@AdminRole
	@Valid
	@PostMapping("/page")
	public ServerResponse page(@RequestBody @Valid RequestParams<CarPageDto> requestParams, BindingResult result) {
		Integer pageNum = requestParams.getPage().getPageNum();
		Integer pageSize = requestParams.getPage().getPageSize();
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		PageData<Car> carPage = carService.carPage(requestParams.getParams(), pageNum, pageSize);
		return ServerResponse.successWithData(carPage);
	}
	
	
	/**
	 * 管理员 - 某一个车主的车辆分页
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@AdminRole
	@Valid
	@PostMapping("/oneCarOwnerCarPage")
	public ServerResponse oneCarOwnerCarPage(@RequestBody @Valid RequestParams<CarPageDto> requestParams, BindingResult result) {
		Integer pageNum = requestParams.getPage().getPageNum();
		Integer pageSize = requestParams.getPage().getPageSize();
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		CarPageDto dto = requestParams.getParams();
		Integer carOwnerId = dto.getCarOwnerId();
		if(carOwnerId == null) {
			throw new BusinessException(112, "车主不能为空");
		}
		PageData<Car> carPage = carService.carPage(dto, pageNum, pageSize);
		return ServerResponse.successWithData(carPage);
	}
	
	
	/**
	 * 管理员 - 待审批的车辆分页
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@AdminRole
	@Valid
	@PostMapping("/approvePendingCarPage")
	public ServerResponse approvePendingCarPage(@RequestBody @Valid RequestParams<CarPageDto> requestParams, BindingResult result) {
		Integer pageNum = requestParams.getPage().getPageNum();
		Integer pageSize = requestParams.getPage().getPageSize();
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		CarPageDto dto = requestParams.getParams();
		dto.setApproveStatus(ApproveConstants.STATUS_APPROVAL_PENDING);
		dto.setIsDelete(false);
		PageData<Car> carPage = carService.carPage(dto, pageNum, pageSize);
		return ServerResponse.successWithData(carPage);
	}
	
	
	/**
	 * 管理员 - 车辆详情
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@AdminRole
	@Valid
	@PostMapping("/details")
	public ServerResponse adminDetails(@RequestBody @Valid RequestParams<DetailsDto> requestParams, BindingResult result) {
		return carService.adminDetails(requestParams.getParams());
	}

	
	/**
	 * 管理员 - 车辆审批
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @param user
	 * @return
	 */
	@AdminRole
	@Valid
	@PostMapping("/approve")
	public ServerResponse approve(@RequestBody @Valid RequestParams<ApproveDto> requestParams, BindingResult result) {
		User user = RequestUtil.getCurrentUser(User.class);
		return carService.adminApprove(requestParams.getParams(), user);
	}
	
	
}
