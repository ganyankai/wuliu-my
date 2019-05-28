package com.zrytech.framework.app.front.controller;

import com.zrytech.framework.app.ano.CargoOwnerRole;
import com.zrytech.framework.app.dto.CargoDto;
import com.zrytech.framework.app.dto.CommonDto;
import com.zrytech.framework.app.dto.cargolocation.CargoLocationUpdateDto;
import com.zrytech.framework.app.dto.cargosource.CargoSourceAddDto;
import com.zrytech.framework.app.dto.cargosource.CargoSourceCheckUpdateDto;
import com.zrytech.framework.app.dto.cargosource.CargoSourceNoCheckUpdateDto;
import com.zrytech.framework.app.dto.cargosource.CargoSourceSearchDto;
import com.zrytech.framework.app.service.CargoService;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.RequestParams;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.base.util.RequestUtil;
import com.zrytech.framework.common.entity.SysCustomer;
import com.zrytech.framework.common.enums.CommonResult;
import com.zrytech.framework.common.enums.ResultEnum;

import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 货源（前端）
 * 
 * @author cat
 *
 */
@RestController
@RequestMapping("/api/cargoSource")
public class CargoSourceController {

	@Autowired
	private CargoService service;
	

	@CargoOwnerRole
	@Valid
	@PostMapping("/saveCargoSource")
	public ServerResponse saveCargoSource(@RequestBody @Valid RequestParams<CargoSourceAddDto> requestParams,
			BindingResult result) {
		return service.saveCargoSource(requestParams.getParams());
	}

	@CargoOwnerRole
	@Valid
	@PostMapping("/updateCargoLocations")
	public ServerResponse updateCargoLocations(@RequestBody @Valid RequestParams<CargoLocationUpdateDto> requestParams,
			BindingResult result) {
		return service.updateCargoLocations(requestParams.getParams());
	}

	@CargoOwnerRole
	@Valid
	@PostMapping("/cancel")
	public ServerResponse cancel(@RequestBody @Valid RequestParams<CommonDto> requestParams, BindingResult result) {
		return service.cancel(requestParams.getParams());
	}

	@CargoOwnerRole
	@Valid
	@PostMapping("/submitChcek")
	public ServerResponse submitChcek(@RequestBody @Valid RequestParams<CommonDto> requestParams,
			BindingResult result) {
		return service.submitChcek(requestParams.getParams());
	}

	@CargoOwnerRole
	@Valid
	@PostMapping("/down")
	public ServerResponse down(@RequestBody @Valid RequestParams<CommonDto> requestParams, BindingResult result) {
		return service.down(requestParams.getParams());
	}

	@CargoOwnerRole
	@Valid
	@PostMapping("/updateNoCheck")
	public ServerResponse updateNoCheck(@RequestBody @Valid RequestParams<CargoSourceNoCheckUpdateDto> requestParams,
			BindingResult result) {
		return service.updateNoCheck(requestParams.getParams());
	}

	@CargoOwnerRole
	@Valid
	@PostMapping("/updateCheck")
	public ServerResponse updateCheck(@RequestBody @Valid RequestParams<CargoSourceCheckUpdateDto> requestParams,
			BindingResult result) {
		return service.updateCheck(requestParams.getParams());
	}

	/**
	 * 货主的货源分页，仅展示当前登录货主的货源
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@CargoOwnerRole
	@Valid
	@PostMapping("/myCargoSourcePage")
	public ServerResponse myCargoSourcePage(@RequestBody @Valid RequestParams<CargoSourceSearchDto> requestParams,
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
		return service.myCargoSourcePage(pageNum, pageSize, requestParams.getParams());
	}

	@CargoOwnerRole
	@Valid
	@PostMapping("/myCargoSourceDetails")
	public ServerResponse myCargoSourceDetails(@RequestBody @Valid RequestParams<CommonDto> requestParams,
			BindingResult result) {
		return service.details(requestParams.getParams());
	}

	
	/**
	 * 大厅货源分页，仅展示发布中货源
	 * @author cat
	 * 
	 * @param requestParams
	 * @param result
	 * @return
	 */
	@Valid
	@PostMapping("/openPage")
	public ServerResponse openPage(@RequestBody @Valid RequestParams<CargoSourceSearchDto> requestParams,
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
		return service.openPage(pageNum, pageSize, requestParams.getParams());
	}
	
	@Valid
	@PostMapping("/openDetails")
	public ServerResponse openDetails(@RequestBody @Valid RequestParams<CommonDto> requestParams,
			BindingResult result) {
		return service.openDetails(requestParams.getParams());
	}
	
	
	/**
	 * Desintion:我的货源分页列表信息
	 *
	 * @author:jiangxiaoxiang
	 * @param:CargoDto货源dto
	 * @return:ServerResponse
	 */
	@Deprecated
	@PostMapping("/mySourcePage")
	@ApiOperation(value = "我的货源")
	public ServerResponse mySourcePage(@RequestBody RequestParams<CargoDto> requestParams) {
		CargoDto cargoDto = requestParams.getParams();
		if (requestParams.getParams() == null) {
			cargoDto = new CargoDto();
		}
		SysCustomer sysCustomer = RequestUtil.getCurrentUser(SysCustomer.class);
		cargoDto.setCreateBy(sysCustomer.getId());
		return service.mySourcePage(requestParams.getParams(), requestParams.getPage());
	}
	
	
	/**
	 * Desintion:货源大厅分页列表信息
	 *
	 * @author:jiangxiaoxiang
	 * @param:CargoDto货源dto
	 * @return:ServerResponse
	 */
	@Deprecated
	@PostMapping("/hallPage")
	@ApiOperation(value = "货源大厅分页列表信息")
	public ServerResponse hallPage(@RequestBody RequestParams<CargoDto> requestParams) {
		CargoDto cargoDto = requestParams.getParams();
		if (requestParams.getParams() == null) {
			cargoDto = new CargoDto();
		}
		return service.mySourcePage(cargoDto, requestParams.getPage());
	}
	
	/**
	 * Desintion:邀请报价(前端)
	 *
	 * @author:jiangxiaoxiang
	 * @param:CargoDto货源dto
	 * @return:ServerResponse
	 */
	@PostMapping("/invitationOffer")
	@ApiOperation(value = "邀请报价")
	public ServerResponse invitationOffer(@RequestBody RequestParams<CargoDto> requestParams) {
		if (requestParams.getParams() == null) {
			throw new BusinessException(new CommonResult(ResultEnum.OBJECT_ERROR));
		}
		return service.invitationOffer(requestParams.getParams());
	}
}
