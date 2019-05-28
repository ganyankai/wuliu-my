package com.zrytech.framework.app.backend.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zrytech.framework.app.ano.AdminRole;
import com.zrytech.framework.app.dto.CommonDto;
import com.zrytech.framework.app.dto.approve.ApproveDto;
import com.zrytech.framework.app.dto.carperson.AdminDriverPageDto;
import com.zrytech.framework.app.dto.carperson.AdminSupercargoPageDto;
import com.zrytech.framework.app.service.CarPersonService;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.util.RequestUtil;
import com.zrytech.framework.common.entity.User;

/**
 * 后台管理系统 - 司机与压货人
 * 
 * @author cat
 *
 */
@RestController
@RequestMapping("/admin/carPerson")
public class CarPersonController {

	@Autowired
	private CarPersonService carPersonService;

	
	/**
	 * 管理员 - 司机分页
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@AdminRole
	@Valid
	@PostMapping("/driverPage")
	public ServerResponse driverPage(@RequestBody @Valid RequestParams<AdminDriverPageDto> requestParams,
			BindingResult result) {
		Integer pageNum = requestParams.getPage().getPageNum();
		Integer pageSize = requestParams.getPage().getPageSize();
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		return carPersonService.adminDriverPage(requestParams.getParams(), pageNum, pageSize);
	}

	
	/**
	 * 管理员 - 压货人分页
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@AdminRole
	@Valid
	@PostMapping("/supercargoPage")
	public ServerResponse supercargoPage(@RequestBody @Valid RequestParams<AdminSupercargoPageDto> requestParams,
			BindingResult result) {
		Integer pageNum = requestParams.getPage().getPageNum();
		Integer pageSize = requestParams.getPage().getPageSize();
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		return carPersonService.adminSupercargoPage(requestParams.getParams(), pageNum, pageSize);
	}
	
	
	/**
	 * 管理员 - 某一个车主的司机分页
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@AdminRole
	@Valid
	@PostMapping("/oneCarOwnerDriverPage")
	public ServerResponse oneCarOwnerDriverPage(@RequestBody @Valid RequestParams<AdminDriverPageDto> requestParams,
			BindingResult result) {
		Integer pageNum = requestParams.getPage().getPageNum();
		Integer pageSize = requestParams.getPage().getPageSize();
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		return carPersonService.adminOneCarOwnerDriverPage(requestParams.getParams(), pageNum, pageSize);
	}
	
	
	/**
	 * 管理员 - 某一个车主的压货人分页
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@AdminRole
	@Valid
	@PostMapping("/oneCarOwnerSupercargoPage")
	public ServerResponse oneCarOwnerSupercargoPage(
			@RequestBody @Valid RequestParams<AdminSupercargoPageDto> requestParams, BindingResult result) {
		Integer pageNum = requestParams.getPage().getPageNum();
		Integer pageSize = requestParams.getPage().getPageSize();
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		return carPersonService.adminOneCarOwnerSupercargoPage(requestParams.getParams(), pageNum, pageSize);
	}
	
	
	/**
	 * 管理员 - 待认证的司机分页
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@AdminRole
	@Valid
	@PostMapping("/approvePendingDriverPage")
	public ServerResponse approvePendingDriverPage(@RequestBody @Valid RequestParams<AdminDriverPageDto> requestParams,
			BindingResult result) {
		Integer pageNum = requestParams.getPage().getPageNum();
		Integer pageSize = requestParams.getPage().getPageSize();
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		return carPersonService.adminApprovePendingDriverPage(requestParams.getParams(), pageNum, pageSize);
	}
	
	
	/**
	 * 管理员 - 待认证的压货人分页
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@AdminRole
	@Valid
	@PostMapping("/approvePendingSupercargoPage")
	public ServerResponse approvePendingSupercargoPage(
			@RequestBody @Valid RequestParams<AdminSupercargoPageDto> requestParams, BindingResult result) {
		Integer pageNum = requestParams.getPage().getPageNum();
		Integer pageSize = requestParams.getPage().getPageSize();
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		return carPersonService.adminApprovePendingSupercargoPage(requestParams.getParams(), pageNum, pageSize);
	}
	
	
	/**
	 * 管理员 - 司机详情
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@AdminRole
	@Valid
	@PostMapping("/driverDetails")
	public ServerResponse driverDetails(@RequestBody @Valid RequestParams<CommonDto> requestParams,
			BindingResult result) {
		return carPersonService.adminDriverDetails(requestParams.getParams());
	}
	
	
	/**
	 * 管理员 - 压货人详情
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@AdminRole
	@Valid
	@PostMapping("/supercargoDetails")
	public ServerResponse supercargoDetails(@RequestBody @Valid RequestParams<CommonDto> requestParams,
			BindingResult result) {
		return carPersonService.adminSupercargoDetails(requestParams.getParams());
	}
	
	
	/**
	 * 管理员 - 司机审批
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@AdminRole
	@Valid
	@PostMapping("/driverApprove")
	public ServerResponse driverApprove(@RequestBody @Valid RequestParams<ApproveDto> requestParams,
			BindingResult result) {
		User user = RequestUtil.getCurrentUser(User.class);
		return carPersonService.adminDriverApprove(requestParams.getParams(), user);
	}
	

	/**
	 * 管理员 - 压货人审批
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@AdminRole
	@Valid
	@PostMapping("/supercargoApprove")
	public ServerResponse supercargoApprove(@RequestBody @Valid RequestParams<ApproveDto> requestParams,
			BindingResult result) {
		User user = RequestUtil.getCurrentUser(User.class);
		return carPersonService.adminSupercargoApprove(requestParams.getParams(), user);
	}
	
}
