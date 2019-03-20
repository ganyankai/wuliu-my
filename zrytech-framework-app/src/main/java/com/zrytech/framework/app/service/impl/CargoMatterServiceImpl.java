package com.zrytech.framework.app.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zrytech.framework.app.repository.CarCargoOwnnerRepository;
import com.zrytech.framework.app.repository.CargoMatterRepository;
import com.zrytech.framework.app.repository.CargoRepository;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.zrytech.framework.app.constants.ApproveConstants;
import com.zrytech.framework.app.constants.ApproveLogConstants;
import com.zrytech.framework.app.constants.CargoMatterConstants;
import com.zrytech.framework.app.dto.CargoMatterPageDto;
import com.zrytech.framework.app.dto.DetailsDto;
import com.zrytech.framework.app.dto.approve.ApproveDto;
import com.zrytech.framework.app.dto.cargomatter.CarOwnerCargoMatterPageDto;
import com.zrytech.framework.app.dto.cargomatter.CargoMatterAddDto;
import com.zrytech.framework.app.dto.cargomatter.CargoMatterNeedApproveUpdateDto;
import com.zrytech.framework.app.dto.cargomatter.CargoMatterUpdateDto;
import com.zrytech.framework.app.entity.CarCargoOwnner;
import com.zrytech.framework.app.entity.Cargo;
import com.zrytech.framework.app.entity.CargoMatter;
import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.app.mapper.CargoMatterMapper;
import com.zrytech.framework.app.service.ApproveLogService;
import com.zrytech.framework.app.service.CargoMatterService;
import com.zrytech.framework.app.service.CargoService;
import com.zrytech.framework.base.entity.PageData;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.entity.User;
import com.zrytech.framework.base.exception.BusinessException;


/**
 * 报价单
 * @author cat
 *
 */
@Service
public class CargoMatterServiceImpl implements CargoMatterService {

	@Autowired
	private CargoMatterRepository cargoMatterRepository;

	@Autowired
	private CargoMatterMapper cargoMatterMapper;

	@Autowired
	private CarCargoOwnnerRepository carCargoOwnnerRepository;

	@Autowired
	private CargoRepository cargoRepository;

	@Autowired
	private CargoService cargoService;
	
	@Autowired
	private ApproveLogService approveLogService;
	
	
	
	
	@Override
	public PageData<CargoMatter> cargoMatterPage(CargoMatterPageDto dto, Integer pageNum, Integer pageSize) {
		com.github.pagehelper.Page<Object> result = PageHelper.startPage(pageNum, pageSize);
		List<CargoMatter> list = cargoMatterMapper.selectSelective(dto);
		for (CargoMatter cargoMatter : list) {
			cargoMatter = this.bindingCarCargoOwnerName(cargoMatter);
		}
		return new PageData<CargoMatter>(result.getPageSize(), result.getPageNum(), result.getTotal(), list);
	}
	
	
	@Override
	public ServerResponse adminDetails(DetailsDto dto) {
		CargoMatter cargoMatter = this.assertCargoMatterExist(dto.getId());
		cargoMatter = this.bindingCarCargoOwnerName(cargoMatter);
		cargoMatter = this.bindingCargo(cargoMatter);
		CargoMatterNeedApproveUpdateDto temp = JSON.parseObject(cargoMatter.getApproveContent(),
				CargoMatterNeedApproveUpdateDto.class);
		cargoMatter.setApproveContentCN(temp);
		return ServerResponse.successWithData(cargoMatter);
	}
	
	@Override
	public ServerResponse adminApprove(ApproveDto dto, User user) {
		CargoMatter cargoMatter = this.assertCargoMatterExist(dto.getBusinessId());
		if(!ApproveConstants.STATUS_APPROVAL_PENDING.equalsIgnoreCase(cargoMatter.getApproveStatus())) {
			throw new BusinessException(112, "审批失败：报价单的状态不是待审批");
		}
		this.approve(cargoMatter, ApproveConstants.RESULT_AGREE.equals(dto.getResult()));
		approveLogService.addApproveLog(dto, user.getId(), ApproveLogConstants.APPROVE_TYPE_CARGO_MATTER);
		return ServerResponse.successWithData("审批成功");
	}
	
	
	/**
	 * 报价单的审批
	 * @author cat
	 * 
	 * @param cargoMatter
	 * @param result
	 */
	private void approve(CargoMatter cargoMatter, Boolean result) {
		Integer id = cargoMatter.getId();
		if (result) {
			CargoMatterNeedApproveUpdateDto temp = JSON.parseObject(cargoMatter.getApproveContent(),
					CargoMatterNeedApproveUpdateDto.class);
			BeanUtils.copyProperties(temp, cargoMatter);
			cargoMatter.setApproveStatus(ApproveConstants.STATUS_BE_APPROVED);
			cargoMatter.setId(id);
			if (cargoMatter.getStatus().equalsIgnoreCase(CargoMatterConstants.STATUS_DRAFT)) {
				cargoMatter.setStatus(CargoMatterConstants.STATUS_PROCESS);
			}
		} else {
			cargoMatter.setApproveStatus(ApproveConstants.STATUS_NOT_APPROVED);
		}
		cargoMatterRepository.save(cargoMatter);
	}
	
	
	/**
	 * 为报价单设置车主、货主企业名称
	 * @author cat
	 * 
	 * @param cargoMatter
	 * @return
	 */
	CargoMatter bindingCarCargoOwnerName(CargoMatter cargoMatter) {
		Integer carOwnerId = cargoMatter.getCarOwnnerId();
		if(carOwnerId != null ) {
			cargoMatter.setCarOwnerName(carCargoOwnnerRepository.findNameById(carOwnerId));
		}
		Integer cargoOwnerId = cargoMatter.getCargoOwnerId();
		if(cargoOwnerId != null ) {
			cargoMatter.setCargoOwnerName(carCargoOwnnerRepository.findNameById(cargoOwnerId));
		}
		return cargoMatter;
	}
	
	
	/**
	 * 为报价单设置货源
	 * @author cat
	 * 
	 * @param cargoMatter
	 * @return
	 */
	CargoMatter bindingCargo(CargoMatter cargoMatter) {
		Integer cargoId = cargoMatter.getCargoId();
		if(cargoId != null ) {
			Cargo cargo = cargoRepository.findOne(cargoId);
			cargoMatter.setCargo(cargo);
		}
		return cargoMatter;
	}
	
	
	
	
	
	
	
	////////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * 车主及车主子账号 - 报价单分页
	 * @author cat
	 * 
	 * @param dto
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Override
	public ServerResponse page(CarOwnerCargoMatterPageDto dto, Integer pageNum, Integer pageSize, Customer customer){
		CarCargoOwnner carOwner = customer.getCarOwner();
		// TODO 鉴权，搜索条件，展示结果待定
		com.github.pagehelper.Page<Object> result = PageHelper.startPage(pageNum, pageSize);
		List<CargoMatter> list = cargoMatterMapper.carOwnerSelectSelective(dto, carOwner.getId());
		for (CargoMatter cargoMatter : list) {
			//cargoMatter = bindingCargoOwnerName(cargoMatter);
		}
		PageData<CargoMatter> pageData = new PageData<CargoMatter>(result.getPageSize(), result.getPageNum(), result.getTotal(), list);
		return ServerResponse.successWithData(pageData);
	}
	
	
	/**
	 * 车主及车主子账号 - 报价单详情
	 * @author cat
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ServerResponse details(DetailsDto dto, Customer customer) {
		CarCargoOwnner carOwner = customer.getCarOwner();
		CargoMatter cargoMatter = this.assertCargoMatterExist(dto.getId());
		this.assertCargoMatterBelongToCurrentUser(cargoMatter, carOwner.getId());
		// TODO 鉴权，展示结果待定
		//cargoMatter = bindingCargoOwnerName(cargoMatter);
		cargoMatter = bindingCargo(cargoMatter);
		return ServerResponse.successWithData(cargoMatter);
	}
	
	
	/**
	 * 车主及车主子账号 - 报价（新增报价单）
	 * @author cat
	 * 
	 * @param dto	货源与报价
	 * @param customer	当前登录人
	 * @return
	 */
	@Override
	public ServerResponse add(CargoMatterAddDto dto, Customer customer){
		CarCargoOwnner carOwner = customer.getCarOwner();
		Integer carOwnerId = carOwner.getId();
		// TODO 鉴权
		cargoService.assertCargoAvailable(dto.getCargoId());
		
		// 一个车主针对一个货源只能有一个报价单
		this.assertNotOffer(dto.getCargoId(), carOwnerId);
		
		// 新建报价单
		CargoMatter cargoMatter = new CargoMatter();
		BeanUtils.copyProperties(dto, cargoMatter);
		cargoMatter.setCarOwnnerId(carOwnerId);
		cargoMatter.setStatus(CargoMatterConstants.STATUS_DRAFT);
		cargoMatter.setCreateBy(customer.getId());
		cargoMatter.setCreateDate(new Date());
		cargoMatter.setId(null);
		cargoMatterRepository.save(cargoMatter);
		
		return ServerResponse.successWithData("报价成功");
	}
	
	
	/**
	 * 断言还未报价（一个车主针对一个货源只能有一个报价单）
	 * @author cat
	 * 
	 * @param cargoId	货源Id
	 * @param carOwnerId	车主Id
	 */
	private void assertNotOffer(Integer cargoId, Integer carOwnerId) {
		List<CargoMatter> list = cargoMatterRepository.findByCargoIdAndCarOwnnerId(cargoId, carOwnerId);
		if(list != null && list.size() > 0) {
			throw new BusinessException(112, "报价失败：请勿重复报价");
		}
	}
	
	
	/**
	 * 断言报价单存在
	 * @author cat
	 * 
	 * @param id	报价单Id
	 * @return
	 */
	private CargoMatter assertCargoMatterExist(Integer id) {
		CargoMatter cargoMatter = cargoMatterRepository.findOne(id);
		if(cargoMatter == null) {
			throw new BusinessException(112, "报价单不存在");
		}
		return cargoMatter;
	}
	
	
	/**
	 * 断言报价单属于当前登录车主
	 * 
	 * @param cargoMatter	报价单
	 * @param carOwnerId	车主Id
	 */
	private void assertCargoMatterBelongToCurrentUser(CargoMatter cargoMatter, Integer carOwnerId) {
		if(!cargoMatter.getCarOwnnerId().equals(carOwnerId)) {
			throw new BusinessException(112, "参数有误");
		}
	}
	
	
	
	/**
	 * 车主及车主子账号 - 修改报价单
	 * 
	 * @param dto
	 * @param customer	当前登录人
	 * @return
	 */
	@Override
	public ServerResponse update(CargoMatterUpdateDto dto, Customer customer){
		CarCargoOwnner carOwner = customer.getCarOwner();
		// TODO 鉴权
		CargoMatter cargoMatter = this.assertCargoMatterExist(dto.getId());
		this.assertCargoMatterBelongToCurrentUser(cargoMatter, carOwner.getId());
		BeanUtils.copyProperties(dto, cargoMatter);
		cargoMatterRepository.save(cargoMatter);
		return ServerResponse.successWithData("报价单修改成功");
	}
	
	
}
