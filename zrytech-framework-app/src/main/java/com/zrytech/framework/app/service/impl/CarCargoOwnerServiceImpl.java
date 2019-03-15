package com.zrytech.framework.app.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.zrytech.framework.app.constants.ApproveConstants;
import com.zrytech.framework.app.constants.ApproveLogConstants;
import com.zrytech.framework.app.constants.CarCargoOwnerConstants;
import com.zrytech.framework.app.dto.CarCargoOwnnerPageDto;
import com.zrytech.framework.app.dto.CheckDto;
import com.zrytech.framework.app.dto.CommonDto;
import com.zrytech.framework.app.dto.DetailsDto;
import com.zrytech.framework.app.dto.carcargoowner.CarCargoOwnerNeedApproveDto;
import com.zrytech.framework.app.dto.carcargoowner.CarCargoOwnerUpdateAvoidAuditDto;
import com.zrytech.framework.app.entity.ApproveLog;
import com.zrytech.framework.app.entity.CarCargoOwnner;
import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.app.mapper.CarCargoOwnerMapper;
import com.zrytech.framework.app.repository.ApproveLogRepository;
import com.zrytech.framework.app.repository.CarCargoOwnnerRepository;
import com.zrytech.framework.app.repository.LogisticsCustomerRepository;
import com.zrytech.framework.app.service.CarCargoOwnerService;
import com.zrytech.framework.base.entity.PageData;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.common.entity.User;

@Service
public class CarCargoOwnerServiceImpl implements CarCargoOwnerService {


	@Autowired private CarCargoOwnnerRepository carCargoOwnnerRepository;
	
	@Autowired private CarCargoOwnerMapper carCargoOwnerMapper;
	
	@Autowired private LogisticsCustomerRepository customerRepository;
	
	@Autowired private ApproveLogRepository approveLogRepository;
	
	
	/**
	 * 车主货主分页
	 * @author cat
	 * 
	 * @param dto
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Override
	public ServerResponse page(CarCargoOwnnerPageDto dto, Integer pageNum, Integer pageSize){
		com.github.pagehelper.Page<Object> result = PageHelper.startPage(pageNum, pageSize);
		List<CarCargoOwnner> list = carCargoOwnerMapper.selectSelective(dto);
		for (CarCargoOwnner carCargoOwnner : list) {
			carCargoOwnner = this.bindingCustomer(carCargoOwnner);
		}
		PageData<CarCargoOwnner> pageData = new PageData<CarCargoOwnner>(result.getPageSize(), result.getPageNum(), result.getTotal(), list);
		return ServerResponse.successWithData(pageData);
	}
	
	
	/**
	 * 管理员 - 车主分页
	 * @author cat
	 * 
	 * @param dto
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Override
	public ServerResponse carOwnerPage(CarCargoOwnnerPageDto dto, Integer pageNum, Integer pageSize) {
		dto.setType(CarCargoOwnerConstants.TYPE_CAR_OWNER);
		return page(dto, pageNum, pageSize);
	}
	
	
	/**
	 * 管理员 - 货主分页
	 * @author cat
	 * 
	 * @param dto
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Override
	public ServerResponse cargoOwnerPage(CarCargoOwnnerPageDto dto, Integer pageNum, Integer pageSize) {
		dto.setType(CarCargoOwnerConstants.TYPE_CARGO_OWNER);
		return page(dto, pageNum, pageSize);
	}
	
	
	
	/**
	 * 车主货主详情
	 * @author cat
	 * 
	 * @param id
	 * @return
	 */
	@Deprecated
	@Override
	public ServerResponse details(Integer id) {
		CarCargoOwnner carCargoOwnner = carCargoOwnnerRepository.findOne(id);
		carCargoOwnner = bindingCustomer(carCargoOwnner);
		return ServerResponse.successWithData(carCargoOwnner);
	}
	
	
	/**
	 * 管理员 - 车主详情
	 * @author cat
	 * 
	 * @param dto	车主Id
	 * @return
	 */
	@Override
	public ServerResponse carOwnerDetails(DetailsDto dto) {
		CarCargoOwnner carOwnner = this.assertCarOwnerExist(dto.getId());
		carOwnner = this.bindingCustomer(carOwnner);
		return ServerResponse.successWithData(carOwnner);
	}
	
	
	/**
	 * 管理员 - 货主详情
	 * @author cat
	 * 
	 * @param dto	货主Id
	 * @return
	 */
	@Override
	public ServerResponse cargoOwnerDetails(DetailsDto dto) {
		CarCargoOwnner cargoOwnner = this.assertCargoOwnerExist(dto.getId());
		cargoOwnner = this.bindingCustomer(cargoOwnner);
		return ServerResponse.successWithData(cargoOwnner);
	}
	
	
	/**
	 * 为车主货主绑定账号信息
	 * @author cat
	 * 
	 * @param carCargoOwnner
	 * @return
	 */
	private CarCargoOwnner bindingCustomer(CarCargoOwnner carCargoOwnner) {
		Customer customer = customerRepository.findOne(carCargoOwnner.getCustomerId());
		customer.setPassword(null);
		carCargoOwnner.setCustomer(customer);
		return carCargoOwnner;
	}
	
	
	/**
	 * 车主货主审核
	 * @author cat
	 * 
	 * @param checkDto
	 * @param user
	 * @return
	 */
	@Deprecated
	@Override
	public ServerResponse check(CheckDto checkDto, User user) {
		Integer businessId = checkDto.getBusinessId();
		// 添加审核记录
		ApproveLog entity = new ApproveLog();
		entity.setApproveBy(user.getId());
		entity.setApproveContent(checkDto.getContent());
		entity.setApproveResult(checkDto.getResult());
		entity.setApproveTime(new Date());
		entity.setApproveType(ApproveLogConstants.APPROVE_TYPE_CAR_OWNER);
		entity.setBusinessId(businessId);
		approveLogRepository.save(entity);
		return ServerResponse.successWithData("审核成功");
	}
	
	
	
	/**
	 * 断言车主存在
	 * @author cat
	 * 
	 * @param carOwnerId	车主Id
	 * @return
	 */
	private CarCargoOwnner assertCarOwnerExist(Integer carOwnerId) {
		CarCargoOwnner carOwner = carCargoOwnnerRepository.findOne(carOwnerId);
		if(carOwner == null) {
			throw new BusinessException(112, "车主不存在");
		}
		if(!carOwner.getType().equalsIgnoreCase(CarCargoOwnerConstants.TYPE_CAR_OWNER)) {
			throw new BusinessException(112, "车主不存在");
		}
		return carOwner;
	}
	
	
	/**
	 * 断言货主存在
	 * @author cat
	 * 
	 * @param cargoOwnerId	货主Id
	 * @return
	 */
	private CarCargoOwnner assertCargoOwnerExist(Integer cargoOwnerId) {
		CarCargoOwnner cargoOwner = carCargoOwnnerRepository.findOne(cargoOwnerId);
		if(cargoOwner == null) {
			throw new BusinessException(112, "货主不存在");
		}
		if(!cargoOwner.getType().equalsIgnoreCase(CarCargoOwnerConstants.TYPE_CARGO_OWNER)) {
			throw new BusinessException(112, "货主不存在");
		}
		return cargoOwner;
	}
	

	/**
	 * 管理员 - 车主审核
	 * @author cat
	 * 
	 * @param checkDto	审核结果
	 * @param user	管理员
	 * @return
	 */
	@Transactional
	@Override
	public ServerResponse approveCarOwner(CheckDto dto, User user) {
		CarCargoOwnner carOwner = this.assertCarOwnerExist(dto.getBusinessId());
		if(!carOwner.getApproveStatus().equalsIgnoreCase(ApproveConstants.STATUS_APPROVAL_PENDING)) {
			throw new BusinessException(112, "审批失败：车主状态不是待审核");
		}
		this.approveCarCargoOwner(carOwner, ApproveLogConstants.APPROVE_RESULT_PASS.equalsIgnoreCase(dto.getResult()));
		this.addApproveLog(dto, user.getId(), ApproveLogConstants.APPROVE_TYPE_CAR_OWNER);
		return ServerResponse.successWithData("审批成功");
	}
	
	
	/**
	 * 管理员 - 货主审核
	 * @author cat
	 * 
	 * @param checkDto	审核结果
	 * @param user	管理员
	 * @return
	 */
	@Transactional
	@Override
	public ServerResponse approveCargoOwner(CheckDto dto, User user) {
		CarCargoOwnner cargoOwner = this.assertCargoOwnerExist(dto.getBusinessId());
		if(!cargoOwner.getApproveStatus().equalsIgnoreCase(ApproveConstants.STATUS_APPROVAL_PENDING)) {
			throw new BusinessException(112, "审批失败：货主状态不是待审核");
		}
		this.approveCarCargoOwner(cargoOwner, ApproveLogConstants.APPROVE_RESULT_PASS.equalsIgnoreCase(dto.getResult()));
		this.addApproveLog(dto, user.getId(), ApproveLogConstants.APPROVE_TYPE_CAR_OWNER);
		return ServerResponse.successWithData("审批成功");
	}
	
	
	/**
	 * 添加审核记录
	 * @author cat
	 * 
	 * @param dto	审核结果
	 * @param approveBy	审核人，管理员
	 * @param approveType	审核类型
	 */
	private void addApproveLog(CheckDto dto, Integer approveBy, String approveType) {
		ApproveLog entity = new ApproveLog();
		entity.setApproveBy(approveBy);
		entity.setApproveType(approveType);
		entity.setApproveContent(dto.getContent());
		entity.setApproveResult(dto.getResult());
		entity.setBusinessId(dto.getBusinessId());
		entity.setApproveTime(new Date());
		approveLogRepository.save(entity);
	}
	
	
	/**
	 * 车主货主审核（修改车主货主状态和待审核的内容）
	 * @author cat
	 * 
	 * @param carOwner	待审核的车主货主
	 * @param result	审核结果
	 * @return
	 */
	private void approveCarCargoOwner(CarCargoOwnner carCargoOwner, boolean result) {
		Integer id = carCargoOwner.getId();
		if (result) {
			String apprvoeContent = carCargoOwner.getApproveContent();
			CarCargoOwnerNeedApproveDto temp = JSON.parseObject(apprvoeContent, CarCargoOwnerNeedApproveDto.class);
			BeanUtils.copyProperties(temp, carCargoOwner);
			carCargoOwner.setId(id);
			carCargoOwner.setApproveStatus(ApproveConstants.STATUS_BE_APPROVED);
			if (carCargoOwner.getStatus().equalsIgnoreCase(CarCargoOwnerConstants.STATUS_UNCERTIFIED)) {
				carCargoOwner.setStatus(CarCargoOwnerConstants.STATUS_CERTIFIED);
			}
		} else {
			carCargoOwner.setApproveStatus(ApproveConstants.STATUS_NOT_APPROVED);
		}
		carCargoOwnnerRepository.save(carCargoOwner);
	}
	
	
	
	@Transactional
	public ServerResponse carOwnerUpdateNeedApprove(CarCargoOwnerNeedApproveDto dto, Customer customer) {
		CarCargoOwnner carOwner = customer.getCarOwner();
		carOwner = this.assertCarOwnerExist(carOwner.getId());
		
		if(carOwner.getApproveStatus().contentEquals(ApproveConstants.STATUS_APPROVAL_PENDING)) {
			throw new BusinessException(112, "修改失败：存在待审批的内容");
		}
		
		if(carOwner.getAvoidAudit()) { // 免审核
			BeanUtils.copyProperties(dto, carOwner);
			carOwner.setApproveStatus(ApproveConstants.STATUS_BE_APPROVED);
			if(carOwner.getStatus().equalsIgnoreCase(CarCargoOwnerConstants.STATUS_UNCERTIFIED)) {
				carOwner.setStatus(CarCargoOwnerConstants.STATUS_CERTIFIED);
			}
		}else {
			carOwner.setApproveStatus(ApproveConstants.STATUS_APPROVAL_PENDING);
		}
		carOwner.setApproveContent(JSON.toJSONString(dto));
		carCargoOwnnerRepository.save(carOwner);
		
		return ServerResponse.successWithData("修改成功");
	}
	
	
	@Transactional
	public ServerResponse cargoOwnerUpdateNeedApprove(CarCargoOwnerNeedApproveDto dto, Customer customer) {
		CarCargoOwnner cargoOwner = customer.getCargoOwner();
		cargoOwner = this.assertCargoOwnerExist(cargoOwner.getId());
		
		if(cargoOwner.getApproveStatus().contentEquals(ApproveConstants.STATUS_APPROVAL_PENDING)) {
			throw new BusinessException(112, "修改失败：存在待审批的内容");
		}
		
		if(cargoOwner.getAvoidAudit()) { // 免审核
			BeanUtils.copyProperties(dto, cargoOwner);
			cargoOwner.setApproveStatus(ApproveConstants.STATUS_BE_APPROVED);
			if(cargoOwner.getStatus().equalsIgnoreCase(CarCargoOwnerConstants.STATUS_UNCERTIFIED)) {
				cargoOwner.setStatus(CarCargoOwnerConstants.STATUS_CERTIFIED);
			}
		}else {
			cargoOwner.setApproveStatus(ApproveConstants.STATUS_APPROVAL_PENDING);
		}
		cargoOwner.setApproveContent(JSON.toJSONString(dto));
		carCargoOwnnerRepository.save(cargoOwner);
		
		return ServerResponse.successWithData("修改成功");
	}
	
	
	@Transactional
	@Override
	public ServerResponse carOwnerUpdateAvoidAudit(CarCargoOwnerUpdateAvoidAuditDto dto) {
		this.assertCarOwnerExist(dto.getId());
		carCargoOwnnerRepository.updateAvoidAuditById(dto.getAvoidAudit(), dto.getId());
		return ServerResponse.successWithData("修改成功");
	}
	
	
	@Transactional
	@Override
	public ServerResponse cargoOwnerUpdateAvoidAudit(CarCargoOwnerUpdateAvoidAuditDto dto) {
		this.assertCargoOwnerExist(dto.getId());
		carCargoOwnnerRepository.updateAvoidAuditById(dto.getAvoidAudit(), dto.getId());
		return ServerResponse.successWithData("修改成功");
	}
	
	
	
	
	
	
	
}
