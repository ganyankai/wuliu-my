package com.zry.framework.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zry.framework.repository.ApproveLogRepository;
import com.zry.framework.repository.CarCargoOwnnerRepository;
import com.zry.framework.repository.CarPersonRepository;
import com.zry.framework.repository.LogisticsCustomerRepository;
import com.github.pagehelper.PageHelper;
import com.zry.framework.constants.ApproveLogConstants;
import com.zry.framework.constants.CarPersonConstants;
import com.zry.framework.constants.CustomerConstants;
import com.zry.framework.dto.CarPersonPageDto;
import com.zry.framework.dto.CheckDto;
import com.zry.framework.dto.CommonDto;
import com.zry.framework.dto.DeleteDto;
import com.zry.framework.dto.DetailsDto;
import com.zry.framework.dto.carperson.CarOwnerCarPersonPageDto;
import com.zry.framework.dto.carperson.CarPersonAddDto;
import com.zry.framework.dto.carperson.CarPersonCheckUpdateDto;
import com.zry.framework.dto.carperson.CarPersonEnabledDto;
import com.zry.framework.dto.carperson.CarPersonNoCheckUpdateDto;
import com.zry.framework.entity.ApproveLog;
import com.zry.framework.entity.CarCargoOwnner;
import com.zry.framework.entity.CarPerson;
import com.zry.framework.entity.Customer;
import com.zry.framework.mapper.CarPersonMapper;
import com.zry.framework.service.CarPersonService;
import com.zrytech.framework.base.entity.PageData;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.common.entity.User;

/**
 * 司机与押货人
 * @author cat
 *
 */
@Service
@Transactional
public class CarPersonServiceImpl implements CarPersonService {
	
	@Autowired private CarPersonRepository carPersonRepository;

	@Autowired private CarPersonMapper carPersonMapper;
	
	@Autowired private CarCargoOwnnerRepository carCargoOwnnerRepository;
	
	@Autowired private ApproveLogRepository approveLogRepository;
	
	@Autowired private LogisticsCustomerRepository customerRepository;
	
	
	
	
	/**
	 * 司机与压货人分页
	 * @author cat
	 * 
	 * @param dto
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Override
	public ServerResponse page(CarPersonPageDto dto, Integer pageNum, Integer pageSize){
		com.github.pagehelper.Page<Object> result = PageHelper.startPage(pageNum, pageSize);
		List<CarPerson> list = carPersonMapper.selectSelective(dto);
		for (CarPerson carPerson : list) {
			carPerson.setCarOwnerName(carCargoOwnnerRepository.findNameById(carPerson.getCreateBy()));
		}
		PageData<CarPerson> pageData = new PageData<CarPerson>(result.getPageSize(), result.getPageNum(), result.getTotal(), list);
		return ServerResponse.successWithData(pageData);
	}
	
	
	/**
	 * 司机或者押货人详情
	 * @author cat
	 * 
	 * @param id	司机或者押货人Id
	 * @return
	 */
	@Override
	public ServerResponse details(Integer id) {
		CarPerson carPerson = carPersonRepository.findOne(id);
		carPerson = this.bindingCarOwner(carPerson);
		return ServerResponse.successWithData(carPerson);
	}
	
	
	/**
	 * 为司机或者押货人绑定 车主信息
	 * @author cat
	 * 
	 * @param carPerson
	 * @return
	 */
	public CarPerson bindingCarOwner(CarPerson carPerson) {
		if(carPerson.getCreateBy() != null) {
			CarCargoOwnner carOwner = carCargoOwnnerRepository.findOne(carPerson.getCreateBy());
			carPerson.setCarOwnerName(carOwner.getName());
			carPerson.setCarOwner(carOwner);
		}
		return carPerson;
	}
	
	
	
	
	
	/**
	 * 司机或押货人审核
	 * @author cat
	 * 
	 * @param checkDto
	 */
	@Override
	public ServerResponse check(CheckDto checkDto, User user) {
		Integer businessId = checkDto.getBusinessId();
		
		CarPerson carPerson = assertCarPersonExist(businessId);
		
		if(!CarPersonConstants.PERSON_STATUS_WAIT_CHECK.equalsIgnoreCase(carPerson.getStatus())) {
			throw new BusinessException(112, "审核失败：司机或押货人的状态不是待审核");
		}
		if(carPerson.getIsDelete()) {
			throw new BusinessException(112, "审核失败：司机或押货人已被删除");
		}
		
		// 修改车辆状态
		String result = checkDto.getResult();
		if(ApproveLogConstants.APPROVE_RESULT_PASS.equals(result)) {
			carPerson.setStatus(CarPersonConstants.PERSON_STATUS_UP);
		}else {
			carPerson.setStatus(CarPersonConstants.PERSON_STATUS_DOWN);
		}
		carPersonRepository.save(carPerson);
		
		// 添加审核记录
		ApproveLog entity = new ApproveLog();
		entity.setApproveBy(user.getId());
		entity.setApproveContent(checkDto.getContent());
		entity.setApproveResult(checkDto.getResult());
		entity.setApproveTime(new Date());
		entity.setApproveType(ApproveLogConstants.APPROVE_TYPE_CAR_PERSON);
		entity.setBusinessId(businessId);
		approveLogRepository.save(entity);
		
		return ServerResponse.successWithData("审核成功");
	}
	
	
	/**
	 * 断言司机或押货人存在
	 * @author cat
	 * 
	 * @param carPersonId	司机或押货人Id
	 * @return
	 */
	public CarPerson assertCarPersonExist(Integer carPersonId) {
		CarPerson carPerson = carPersonRepository.findOne(carPersonId);
		if(carPerson == null){
			throw new BusinessException(112, "司机或压货人不存在");
		}
		return carPerson;
	}
	
	
	/**
	 * 断言司机或押货人未被删除
	 * @author cat
	 * 
	 * @param carPerson 司机或押货人
	 * @return
	 */
	public void assertCarPersonNotDelete(CarPerson carPerson) {
		if(carPerson.getIsDelete()){
			throw new BusinessException(112, "司机或压货人不存在");
		}
	}
	
	
	/*以下为车主及车主子账号接口*/
	////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	/**
	 * 司机与压货人分页（车主及车主子账号）
	 * @author cat
	 * 
	 * @param dto
	 * @param pageNum
	 * @param pageSize
	 * @param customer	当前登录人
	 * @return
	 */
	@Override
	public ServerResponse page(CarOwnerCarPersonPageDto dto, Integer pageNum, Integer pageSize, Customer customer){
		// TODO 搜索条件，展示结果待定
		com.github.pagehelper.Page<Object> result = PageHelper.startPage(pageNum, pageSize);
		List<CarPerson> list = carPersonMapper.carOwnerCarPersonPage(dto);
		for (CarPerson carPerson : list) {
			carPerson.setCarOwnerName(carCargoOwnnerRepository.findNameById(carPerson.getCreateBy()));
		}
		PageData<CarPerson> pageData = new PageData<CarPerson>(result.getPageSize(), result.getPageNum(), result.getTotal(), list);
		return ServerResponse.successWithData(pageData);
	}
	
	
	/**
	 * 司机或者押货人详情（车主及车主子账号）
	 * @author cat
	 * 
	 * @param id	司机或者押货人Id
	 * @param customer	当前登录人
	 * @return
	 */
	@Override
	public ServerResponse details(DetailsDto dto, Customer customer) {
		// TODO 判断当前用户是否有权限查看数据
		// TODO 返回结果待定
		CarPerson carPerson = this.assertCarPersonExist(dto.getId());
		carPerson = this.bindingCarOwner(carPerson);
		return ServerResponse.successWithData(carPerson);
	}
	
	
	/**
	 * 车主及车主子账号 - 添加司机或压货人
	 * <p>添加司机与压货人时需要同时创建登录账号（目前压货人不创建账号）</p>
	 * @author cat
	 * 
	 * @param dto	司机压货人的个人信息和账号信息
	 * @param customer	当前登录人
	 * @return
	 */
	@Override
	public ServerResponse add(CarPersonAddDto dto, Customer customer) {
		// TODO 鉴权
		CarCargoOwnner carCargoOwner = customer.getCarOwner();
		Integer carOwnerId = carCargoOwner.getId();
		Boolean avoidAudit = carCargoOwner.getAvoidAudit(); // 是否免审核
		// 仅司机创建登录账号
		Integer newCustomerId = null;
		if(CarPersonConstants.PERSON_TYPE_DRIVER.equalsIgnoreCase(dto.getPersonType())) {
			newCustomerId = this.addDriverCustomer(dto, avoidAudit);
		}
		// 添加车主或压货人信息
		CarPerson carPerson = new CarPerson();
		BeanUtils.copyProperties(dto, carPerson);
		carPerson.setId(null);
		carPerson.setCarOwnerId(carOwnerId);
		carPerson.setCreateBy(customer.getId());
		carPerson.setCustomerId(newCustomerId);
		carPerson.setCreateDate(new Date());
		carPerson.setIsDelete(false);
		if(avoidAudit) {
			carPerson.setStatus(CarPersonConstants.PERSON_STATUS_UP);
		}else {
			carPerson.setStatus(CarPersonConstants.PERSON_STATUS_WAIT_CHECK); // 默认待审核
		}
		carPersonRepository.save(carPerson);
		return ServerResponse.successWithData("添加成功");
	}
	
	
	/**   
	 * 创建司机账号
	 * @author cat
	 * 
	 * @param dto	司机账号信息
	 * @return	司机账号Id
	 */
	private Integer addDriverCustomer(CarPersonAddDto dto, Boolean avoidAudit) {
		// 参数校验
		if (StringUtils.isBlank(dto.getUserAccount())) {
			throw new BusinessException(112, "账号不能为空");
		}
		if (StringUtils.isBlank(dto.getPassword())) {
			throw new BusinessException(112, "密码不能为空");
		}
		if (StringUtils.isBlank(dto.getUserTel())) {
			throw new BusinessException(112, "账号手机号不能为空");
		}
		// 判断账号是否已存在，当前仅判断用户名唯一
		Customer account = customerRepository.findByUserAccount(dto.getUserAccount());
		if (account != null) {
			throw new BusinessException(112, "账号已存在");
		}
		// 新建账号
		Customer entity = new Customer();
		entity.setUserAccount(dto.getUserAccount());
		entity.setPassword(dto.getPassword());
		entity.setTel(dto.getUserTel());
		entity.setCustomerType(CustomerConstants.TYPE_DRIVER);
		if(avoidAudit) {
			entity.setIsActive(true);
		}else {
			entity.setIsActive(false); // 默认禁用
		}
		customerRepository.save(entity);
		return entity.getId();
	}
	
	
	/**
	 * 车主及车主子账号 - 删除司机或压货人
	 * @author cat
	 * 
	 * @param dto	待删除的司机或压货人Id
	 * @param customer	当前登录人
	 * @return
	 */
	@Override
	public ServerResponse delete(DeleteDto dto, Customer customer) {
		CarPerson carPerson = this.assertCarPersonExist(dto.getId());
		this.assertCarPersonNotDelete(carPerson);
		
		CarCargoOwnner carOwner = customer.getCarOwner();
		if(carOwner.getId() != carPerson.getCarOwnerId()) {
			throw new BusinessException(112, "非法访问");
		}
		
		// TODO 鉴权
		// TODO 判断当前司机或压货人是否可以删除（暂未确认哪些条件下可以删除）
		// TODO 删除日志（暂未确认是否需要日志）
		// TODO 禁用司机账号
		carPersonRepository.deleteCarById(dto.getId());
		return ServerResponse.successWithData("删除成功");
	}
	
	
	/**
	 * 车主及车主子账号 - 司机与压货人的禁用与启用
	 * @author cat
	 * 
	 * @param dto
	 * @param customer
	 * @return
	 */
	@Override
	public ServerResponse enabled(CarPersonEnabledDto dto, Customer customer) {
		CarPerson carPerson = this.assertCarPersonExist(dto.getId());
		this.assertCarPersonNotDelete(carPerson);
		
		if(!CarPersonConstants.PERSON_TYPE_DRIVER.equalsIgnoreCase(carPerson.getPersonType())) {
			throw new BusinessException(112, "参数有误");
		}
		
		CarCargoOwnner carOwner = customer.getCarOwner();
		if(carOwner.getId() != carPerson.getCarOwnerId()) {
			throw new BusinessException(112, "非法访问");
		}
		
		// TODO 鉴权
		
		customerRepository.updateIsActiveById(carPerson.getCustomerId(), dto.getEnabled());
		if(!dto.getEnabled()) { // 禁用
			// TODO 删除已登录的TOKEN
		}
		
		return ServerResponse.successWithData("修改成功");
	} 
	
	
	/**
	 * 修改司机压货人不需要审核字段
	 * @author cat
	 * 
	 * @param dto	待修改字段
	 * @param customer	当前登录人
	 * @return
	 */
	@Override
	public ServerResponse updateNoCheck(CarPersonNoCheckUpdateDto dto, Customer customer) {
		// TODO 鉴权
		CarPerson carPerson = this.assertCarPersonExist(dto.getId());
		this.assertCarPersonNotDelete(carPerson);
		
		CarCargoOwnner carOwner = customer.getCarOwner();
		if(carOwner.getId() != carPerson.getCarOwnerId()) {
			throw new BusinessException(112, "非法访问");
		}
		
		BeanUtils.copyProperties(dto, carPerson);
		carPersonRepository.save(carPerson);
		return ServerResponse.successWithData("修改成功");
	}
	
	
	/**
	 * 修改司机压货人需要审核字段，修改后将状态改为待审核
	 * @author cat
	 * 
	 * @param dto	待修改字段
	 * @param customer	当前登录人
	 * @return
	 */
	@Override
	public ServerResponse updateCheck(CarPersonCheckUpdateDto dto, Customer customer) {
		// TODO 鉴权
		CarPerson carPerson = this.assertCarPersonExist(dto.getId());
		this.assertCarPersonNotDelete(carPerson);
		
		CarCargoOwnner carOwner = customer.getCarOwner();
		if(carOwner.getId() != carPerson.getCarOwnerId()) {
			throw new BusinessException(112, "非法访问");
		}
		
		BeanUtils.copyProperties(dto, carPerson);
		carPerson.setStatus(CarPersonConstants.PERSON_STATUS_WAIT_CHECK);
		carPersonRepository.save(carPerson);
		return ServerResponse.successWithData("修改成功");
	}
	
	
	/**
	 * 提交审核
	 * @author cat
	 * 
	 * @param dto	待提交审核的司机压货人Id
	 * @param customer	当前登录人
	 * @return
	 */
	@Override
	public ServerResponse submitAudit(CommonDto dto, Customer customer) {
		CarPerson carPerson = this.assertCarPersonExist(dto.getId());
		this.assertCarPersonNotDelete(carPerson);
		
		CarCargoOwnner carOwner = customer.getCarOwner();
		if(carOwner.getId() != carPerson.getCarOwnerId()) {
			throw new BusinessException(112, "非法访问");
		}
		
		// TODO 验证当前用户是否有权限修改数据
		if(!CarPersonConstants.PERSON_STATUS_DOWN.equalsIgnoreCase(carPerson.getStatus())) {
			throw new BusinessException(112, "提交审核失败：仅下架状态的司机或压货人可以提交审核");
		}
		carPersonRepository.updateStatusById(dto.getId(), CarPersonConstants.PERSON_STATUS_WAIT_CHECK);
		return ServerResponse.successWithData("提交审核成功");
	}
	
}
