package com.zrytech.framework.app.front.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zrytech.framework.app.dto.CommonDto;
import com.zrytech.framework.app.dto.DeleteDto;
import com.zrytech.framework.app.dto.DetailsDto;
import com.zrytech.framework.app.dto.carperson.CarPersonAddDto;
import com.zrytech.framework.app.dto.carperson.CarPersonCheckUpdateDto;
import com.zrytech.framework.app.dto.carperson.CarPersonEnabledDto;
import com.zrytech.framework.app.dto.carperson.CarPersonNoCheckUpdateDto;
import com.zrytech.framework.app.dto.carperson.CarPersonPageDto;
import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.app.service.CarPersonService;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.util.RequestUtil;


/**
 * 前台 - 司机与压货人
 * @author cat
 *
 */
@RestController
@RequestMapping("/api/carPerson")
public class CarPersonAPIController {

	@Autowired
	private CarPersonService carPersonService;

	
	/**
	 * 车主及车主子账号 - 司机与压货人启用禁用
	 * 
	 * @param requestParams
	 * @param result
	 * @param customer	车主或者车主子账号
	 * @return
	 */
	@Valid
	@PostMapping("/enabled")
	public ServerResponse enabled(@RequestBody @Valid RequestParams<CarPersonEnabledDto> requestParams,
			BindingResult result) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		return carPersonService.enabled(requestParams.getParams(), customer);
	}
	
	
	/**
	 * 车主及车主子账号 - 删除司机与压货人
	 * 
	 * @param requestParams
	 * @param result
	 * @param customer	车主或者车主子账号
	 * @return
	 */
	@Valid
	@PostMapping("/delete")
	public ServerResponse delete(@RequestBody @Valid RequestParams<DeleteDto> requestParams, BindingResult result) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		return carPersonService.delete(requestParams.getParams(), customer);
	}
	
	
	/**
	 * 车主及车主子账号 - 取消审批
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@PostMapping("/cancelApprove")
	public ServerResponse cancel(@RequestBody @Valid RequestParams<CommonDto> requestParams, BindingResult result) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		return carPersonService.cancelApprove(requestParams.getParams(), customer);
	}
	
	
	/**
	 * 车主及车主子账号 - 提交审核
	 * 
	 * @param requestParams
	 * @param result
	 * @param customer
	 * @return
	 */
	@Deprecated
	@Valid
	@PostMapping("/submitAudit")
	public ServerResponse submitAudit(@RequestBody @Valid RequestParams<CommonDto> requestParams, BindingResult result) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		return carPersonService.submitAudit(requestParams.getParams(), customer);
	}
	
	
	/**
	 * 车主及车主子账号 - 提交审批（仅已取消状态的司机或压货人可以提交审批）
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@PostMapping("/submitApprove")
	public ServerResponse submitApprove(@RequestBody @Valid RequestParams<CommonDto> requestParams,
			BindingResult result) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		return carPersonService.submitApprove(requestParams.getParams(), customer);
	}
	
	/**
	 * 车主及车主子账号 - 修改司机或压货人不需要审核的内容
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @param customer
	 * @return
	 */
	@Valid
	@PostMapping("/updateNoCheck")
	public ServerResponse updateNoCheck(@RequestBody @Valid RequestParams<CarPersonNoCheckUpdateDto> requestParams,
			BindingResult result) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		return carPersonService.updateNoCheck(requestParams.getParams(), customer);
	}
	
	
	/**
	 * 修改司机与压货人需要审核的内容
	 * 
	 * @param requestParams
	 * @param result
	 * @param customer
	 * @return
	 */
	@Deprecated
	@Valid
	@PostMapping("/updateCheck")
	public ServerResponse updateCheck(@RequestBody @Valid RequestParams<CarPersonCheckUpdateDto> requestParams, BindingResult result) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		return carPersonService.updateCheck(requestParams.getParams(), customer);
	}
	
	/**
	 * 车主及车主子账号 - 修改司机压货人需要审批的字段
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@PostMapping("/updateNeedApprove")
	public ServerResponse updateNeedApprove(@RequestBody @Valid RequestParams<CarPersonCheckUpdateDto> requestParams,
			BindingResult result) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		return carPersonService.updateNeedApprove(requestParams.getParams(), customer);
	}
	
	
	/**
	 * 添加司机与压货人
	 * 
	 * @param requestParams
	 * @param result
	 * @param customer	车主或者车主子账号
	 * @return
	 */
	@Deprecated
	@Valid
	@PostMapping("/add")
	public ServerResponse add(@RequestBody @Valid RequestParams<CarPersonAddDto> requestParams, BindingResult result) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		return carPersonService.add(requestParams.getParams(), customer);
	}
	
	
	/**
	 * 车主及车主子账号 - 新增司机或压货人
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@PostMapping("/create")
	public ServerResponse create(@RequestBody @Valid RequestParams<CarPersonAddDto> requestParams,
			BindingResult result) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		return carPersonService.create(requestParams.getParams(), customer);
	}
	
	/**
	 * 车主及车主子账号 - 司机与压货人详情
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
		return carPersonService.details(requestParams.getParams(), customer);
	}
	
	/**
	 * 车主及车主子账号 - 司机与压货人分页
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@PostMapping("/page")
	public ServerResponse page(@RequestBody @Valid RequestParams<CarPersonPageDto> requestParams,
			BindingResult result) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		Integer pageNum = requestParams.getPage().getPageNum();
		Integer pageSize = requestParams.getPage().getPageSize();
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		CarPersonPageDto dto = requestParams.getParams();
		dto.setIsDelete(false);
		dto.setCarOwnerId(customer.getCarOwner().getId());
		return carPersonService.page(dto, pageNum, pageSize);
	}
	
	
	/**
	 * 车主及车主子账号 - 我的司机
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@PostMapping("/myDriver")
	public ServerResponse myDriver(@RequestBody @Valid RequestParams<CarPersonPageDto> requestParams,
			BindingResult result) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		Integer pageNum = requestParams.getPage().getPageNum();
		Integer pageSize = requestParams.getPage().getPageSize();
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		CarPersonPageDto dto = requestParams.getParams();
		return carPersonService.myDriver(dto, pageNum, pageSize, customer);
	}
	
	/**
	 * 车主及车主子账号 - 我的压货人
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@PostMapping("/mySupercargo")
	public ServerResponse mySupercargo(@RequestBody @Valid RequestParams<CarPersonPageDto> requestParams,
			BindingResult result) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		Integer pageNum = requestParams.getPage().getPageNum();
		Integer pageSize = requestParams.getPage().getPageSize();
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
		CarPersonPageDto dto = requestParams.getParams();
		return carPersonService.mySupercargo(dto, pageNum, pageSize, customer);
	}
	
	
	
}
