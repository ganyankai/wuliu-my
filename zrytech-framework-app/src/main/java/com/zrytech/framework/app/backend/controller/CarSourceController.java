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
import com.zrytech.framework.app.dto.CommonDto;
import com.zrytech.framework.app.dto.approve.ApproveDto;
import com.zrytech.framework.app.dto.carsource.CarSourcePageDto;
import com.zrytech.framework.app.entity.CarSource;
import com.zrytech.framework.app.service.CarSourceService;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.PageData;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.base.util.RequestUtil;
import com.zrytech.framework.common.entity.User;

/**
 * 后台管理系统 - 车源
 * 
 * @author cat
 *
 */
@RestController
@RequestMapping("/admin/carSource")
public class CarSourceController {

	@Autowired
	private CarSourceService carSourceService;

	
	
	/**
	 * 管理员 - 车源分页
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@AdminRole
	@Valid
	@PostMapping("/page")
	public ServerResponse carSourcePage(@RequestBody @Valid RequestParams<CarSourcePageDto> requestParams,
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
		PageData<CarSource> pageData = carSourceService.carSourcePage(requestParams.getParams(), pageNum, pageSize);
		return ServerResponse.successWithData(pageData);
	}
	
	
	/**
	 * 管理员 - 某一个车主的车源分页
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@AdminRole
	@Valid
	@PostMapping("/oneCarOwnerCarSourcePage")
	public ServerResponse oneCarOwnerCarSourcePage(@RequestBody @Valid RequestParams<CarSourcePageDto> requestParams,
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
		CarSourcePageDto dto = requestParams.getParams();
		if (dto.getCarOwnerId() == null) {
			throw new BusinessException(112, "车主不能为空");
		}
		PageData<CarSource> pageData = carSourceService.carSourcePage(dto, pageNum, pageSize);
		return ServerResponse.successWithData(pageData);
	}
	
	
	/**
	 * 管理员 - 待审批的车源分页
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@AdminRole
	@Valid
	@PostMapping("/approvePendingCarSourcePage")
	public ServerResponse approvePendingCarSourcePage(@RequestBody @Valid RequestParams<CarSourcePageDto> requestParams,
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
		CarSourcePageDto dto = requestParams.getParams();
		dto.setApproveStatus(ApproveConstants.STATUS_APPROVAL_PENDING);
		PageData<CarSource> pageData = carSourceService.carSourcePage(dto, pageNum, pageSize);
		return ServerResponse.successWithData(pageData);
	}
	
	
	/**
	 * 管理员 - 车源详情
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@AdminRole
	@Valid
	@PostMapping("/details")
	public ServerResponse details(@RequestBody @Valid RequestParams<CommonDto> requestParams, BindingResult result) {
		return carSourceService.adminDetails(requestParams.getParams());
	}
	
	
	/**
	 * 管理员 - 车源审批
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@AdminRole
	@Valid
	@PostMapping("/approve")
	public ServerResponse approve(@RequestBody @Valid RequestParams<ApproveDto> requestParams, BindingResult result) {
		User user = RequestUtil.getCurrentUser(User.class);
		return carSourceService.adminApprove(requestParams.getParams(), user);
	}
	
}
