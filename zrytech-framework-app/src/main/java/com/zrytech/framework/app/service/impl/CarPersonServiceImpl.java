package com.zrytech.framework.app.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zrytech.framework.app.repository.ApproveLogRepository;
import com.zrytech.framework.app.repository.CarCargoOwnnerRepository;
import com.zrytech.framework.app.repository.CarPersonRepository;
import com.zrytech.framework.app.repository.LogisticsCustomerRepository;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.zrytech.framework.app.constants.ApproveLogConstants;
import com.zrytech.framework.app.constants.CarPersonConstants;
import com.zrytech.framework.app.constants.CustomerConstants;
import com.zrytech.framework.app.dto.CheckDto;
import com.zrytech.framework.app.dto.CommonDto;
import com.zrytech.framework.app.dto.DeleteDto;
import com.zrytech.framework.app.dto.DetailsDto;
import com.zrytech.framework.app.dto.carperson.CarOwnerCarPersonPageDto;
import com.zrytech.framework.app.dto.carperson.CarPersonAddDto;
import com.zrytech.framework.app.dto.carperson.CarPersonCheckUpdateDto;
import com.zrytech.framework.app.dto.carperson.CarPersonEnabledDto;
import com.zrytech.framework.app.dto.carperson.CarPersonNoCheckUpdateDto;
import com.zrytech.framework.app.dto.carperson.CarPersonPageDto;
import com.zrytech.framework.app.entity.ApproveLog;
import com.zrytech.framework.app.entity.CarCargoOwnner;
import com.zrytech.framework.app.entity.CarPerson;
import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.app.mapper.CarPersonMapper;
import com.zrytech.framework.app.service.CarPersonService;
import com.zrytech.framework.app.utils.StringCheckUtils;
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
	
	
	
	
	@Override
	public ServerResponse page(CarPersonPageDto dto, Integer pageNum, Integer pageSize){
		com.github.pagehelper.Page<Object> result = PageHelper.startPage(pageNum, pageSize);
		List<CarPerson> list = carPersonMapper.selectSelective(dto);
		for (CarPerson carPerson : list) {
			carPerson.setCarOwnerName(carCargoOwnnerRepository.findNameById(carPerson.getCarOwnerId()));
		}
		PageData<CarPerson> pageData = new PageData<CarPerson>(result.getPageSize(), result.getPageNum(), result.getTotal(), list);
		return ServerResponse.successWithData(pageData);
	}
	
	
	@Override
	public ServerResponse details(DetailsDto dto) {
		CarPerson carPerson = this.assertCarPersonExist(dto.getId());
		carPerson = this.bindingCarOwner(carPerson);
		carPerson = this.bindingDriverCustomerStatus(carPerson);
		return ServerResponse.successWithData(carPerson);
	}
	
	
	@Transactional
	@Override
	public ServerResponse check(CheckDto checkDto, User user) {
		Integer businessId = checkDto.getBusinessId();
		CarPerson carPerson = this.assertCarPersonAvailable(businessId);
		if(!CarPersonConstants.PERSON_STATUS_WAIT_CHECK.equalsIgnoreCase(carPerson.getStatus())) {
			throw new BusinessException(112, "审核失败：司机或押货人的状态不是待审核");
		}
		// 修改司机或押货人状态
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
	
	
	@Transactional
	@Override
	public ServerResponse approve(CheckDto checkDto, User user) {
		Integer businessId = checkDto.getBusinessId();
		CarPerson carPerson = this.assertCarPersonAvailable(businessId);
		// 仅待审批状态的司机或压货人可以审批
		if(!CarPersonConstants.APPROVE_STATUS_APPROVAL_PENDING.equalsIgnoreCase(carPerson.getApproveStatus())) {
			throw new BusinessException(112, "审批失败：司机或押货人的状态不是待审批");
		}
		// 修改司机或押货人的审批状态和需要审核的字段
		String result = checkDto.getResult();
		this.approve(ApproveLogConstants.APPROVE_RESULT_PASS.equals(result), carPerson);
		// 添加审核记录
		ApproveLog entity = new ApproveLog();
		entity.setApproveBy(user.getId());
		entity.setApproveContent(checkDto.getContent());
		entity.setApproveResult(result);
		entity.setApproveTime(new Date());
		entity.setApproveType(ApproveLogConstants.APPROVE_TYPE_CAR_PERSON);
		entity.setBusinessId(businessId);
		approveLogRepository.save(entity);
		return ServerResponse.successWithData("审核成功");
	}
	
	
	/**
	 * 为司机或者押货人绑定 车主信息
	 * @author cat
	 * 
	 * @param carPerson
	 * @return
	 */
	private CarPerson bindingCarOwner(CarPerson carPerson) {
		if(carPerson.getCarOwnerId() != null) {
			CarCargoOwnner carOwner = carCargoOwnnerRepository.findOne(carPerson.getCarOwnerId());
			carPerson.setCarOwnerName(carOwner.getName());
			carPerson.setCarOwner(carOwner);
		}
		return carPerson;
	}
	
	
	/**
	 * 给司机绑定登录账号的状态
	 * @author cat
	 * 
	 * @param carPerson
	 * @return
	 */
	private CarPerson bindingDriverCustomerStatus(CarPerson carPerson) {
		if(carPerson.getPersonType().equalsIgnoreCase(CarPersonConstants.PERSON_TYPE_DRIVER)) {
			Boolean isActive = customerRepository.findIsActiveById(carPerson.getCustomerId());
			carPerson.setIsActive(isActive);
		}
		return carPerson;
	}
	

	
	
	
	/**
	 * 断言司机或押货人存在
	 * @author cat
	 * 
	 * @param carPersonId	司机或押货人Id
	 * @return
	 */
	private CarPerson assertCarPersonExist(Integer carPersonId) {
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
	private void assertCarPersonNotDelete(CarPerson carPerson) {
		if(carPerson.getIsDelete()){
			throw new BusinessException(112, "司机或压货人不存在");
		}
	}
	
	
	@Override
	public CarPerson assertCarPersonAvailable(Integer carPersonId) {
		CarPerson carPerson = this.assertCarPersonExist(carPersonId);
		this.assertCarPersonNotDelete(carPerson);
		return carPerson;
	}
	
	
	/**
	 * 断言司机或压货人存在，且属于当前登录的车主
	 * @author cat
	 * 
	 * @param carPersonId	司机或压货人Id
	 * @param carOwnerId	车主Id
	 * @return	司机或压货人
	 */
	private CarPerson assertCarPersonBelongToCurrentUser(Integer carPersonId, Integer carOwnerId) {
		CarPerson carPerson = carPersonRepository.findByIdAndIsDeleteAndCarOwnerId(carPersonId, false, carOwnerId);
		if(carPerson == null) {
			throw new BusinessException(112, "司机或压货人不存在");
		}
		return carPerson;
	}
	
	
	@Override
	public CarPerson assertDriverBelongToCurrentUser(Integer driverId, Integer carOwnerId) {
		CarPerson carPerson = carPersonRepository.findByIdAndIsDeleteAndCarOwnerId(driverId, false, carOwnerId);
		if(carPerson == null || !carPerson.getPersonType().equalsIgnoreCase(CarPersonConstants.PERSON_TYPE_DRIVER)) {
			throw new BusinessException(112, "司机不存在");
		}
		return carPerson;
	}
	
	
	@Override
	public CarPerson assertSupercargoBelongToCurrentUser(Integer supercargoId, Integer carOwnerId) {
		CarPerson carPerson = carPersonRepository.findByIdAndIsDeleteAndCarOwnerId(supercargoId, false, carOwnerId);
		if(carPerson == null || !carPerson.getPersonType().equalsIgnoreCase(CarPersonConstants.PERSON_TYPE_SUPERCARGO)) {
			throw new BusinessException(112, "压货人不存在");
		}
		return carPerson;
	}
	
	
	
	
	
	
	/*以下为车主及车主子账号接口*/
	
	
	
	
	
	
	/**   
	 * 创建司机账号
	 * @author cat
	 * 
	 * @param dto	司机账号信息
	 * @return	司机账号Id
	 */
	private Integer createDriverCustomer(CarPersonAddDto dto, Boolean avoidAudit, Integer createBy) {
		// 参数校验
		if (StringUtils.isBlank(dto.getUserAccount())) {
			throw new BusinessException(112, "账号不能为空");
		}
		if (StringUtils.isBlank(dto.getPassword())) {
			throw new BusinessException(112, "密码不能为空");
		}
		if(StringCheckUtils.rexCheckPassword(dto.getPassword())) {
			throw new BusinessException(112, "密码格式有误：包含数字、字母、半角标点符号中至少两种，长度(6-20)");
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
		entity.setPassword(DigestUtils.md5Hex(dto.getPassword().getBytes()));
		entity.setTel(dto.getUserTel());
		entity.setCreateDate(new Date());
		entity.setCreateBy(createBy);
		entity.setCustomerType(CustomerConstants.TYPE_DRIVER);
		if(avoidAudit) {
			entity.setIsActive(true);
		}else {
			entity.setIsActive(false); // 默认禁用
		}
		customerRepository.save(entity);
		return entity.getId();
	}
	
	
	@Transactional
	@Override
	public ServerResponse add(CarPersonAddDto dto, Customer customer) {
		CarCargoOwnner carOwner = customer.getCarOwner();
		Boolean avoidAudit = carOwner.getAvoidAudit(); // 是否免审核
		// 仅司机创建登录账号
		Integer newCustomerId = null;
		if(CarPersonConstants.PERSON_TYPE_DRIVER.equalsIgnoreCase(dto.getPersonType())) {
			newCustomerId = this.createDriverCustomer(dto, avoidAudit, customer.getId());
		}
		// 添加车主或压货人信息
		CarPerson carPerson = new CarPerson();
		BeanUtils.copyProperties(dto, carPerson);
		carPerson.setId(null);
		carPerson.setCarOwnerId(carOwner.getId());
		carPerson.setCreateBy(customer.getId());
		carPerson.setCustomerId(newCustomerId);
		carPerson.setCreateDate(new Date());
		carPerson.setIsDelete(false);
		if(avoidAudit) {
			carPerson.setStatus(CarPersonConstants.PERSON_STATUS_UP);
		}else {
			carPerson.setStatus(CarPersonConstants.PERSON_STATUS_WAIT_CHECK);
		}
		carPersonRepository.save(carPerson);
		return ServerResponse.successWithData("添加成功");
	}
	
	
	
	
	/**
	 * 车主及车主子账号 - 司机与压货人分页
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
		com.github.pagehelper.Page<Object> result = PageHelper.startPage(pageNum, pageSize);
		List<CarPerson> list = carPersonMapper.carOwnerSelectSelective(dto, customer.getCarOwner().getId());
		for (CarPerson carPerson : list) {
			carPerson = this.bindingDriverCustomerStatus(carPerson);
		}
		PageData<CarPerson> pageData = new PageData<CarPerson>(result.getPageSize(), result.getPageNum(), result.getTotal(), list);
		return ServerResponse.successWithData(pageData);
	}
	
	
	/**
	 * 车主及车主子账号 - 司机或压货人详情
	 * @author cat
	 * 
	 * @param id	司机或压货人Id
	 * @param customer	当前登录人
	 * @return
	 */
	@Override
	public ServerResponse details(DetailsDto dto, Customer customer) {
		CarPerson carPerson = this.assertCarPersonBelongToCurrentUser(dto.getId(), customer.getCarOwner().getId());
		carPerson = this.bindingCarOwner(carPerson);
		return ServerResponse.successWithData(carPerson);
	}
	
	
	
	
	
	@Transactional
	@Override
	public ServerResponse delete(DeleteDto dto, Customer customer) {
		CarPerson carPerson = this.assertCarPersonBelongToCurrentUser(dto.getId(), customer.getCarOwner().getId());
		// TODO 判断当前司机或压货人是否可以删除
		// TODO 删除日志（暂未确认是否需要日志）
		if(carPerson.getPersonType().equalsIgnoreCase(CarPersonConstants.PERSON_TYPE_DRIVER)) {
			// TODO 禁用司机账号
		}
		carPersonRepository.deleteCarPersonById(dto.getId());
		return ServerResponse.successWithData("删除成功");
	}
	
	
	@Transactional
	@Override
	public ServerResponse enabled(CarPersonEnabledDto dto, Customer customer) {
		CarPerson carPerson = this.assertDriverBelongToCurrentUser(dto.getId(), customer.getCarOwner().getId());
		customerRepository.updateIsActiveById(carPerson.getCustomerId(), dto.getEnabled());
		if(dto.getEnabled() == false) { // 禁用
			// TODO 若司机已登录，注销司机账号
			return ServerResponse.successWithData("禁用成功");
		}else {
			return ServerResponse.successWithData("启用成功");
		}
	} 
	
	
	/**
	 * 修改司机压货人不需要审核的字段
	 * @author cat
	 * 
	 * @param dto	待修改的不需要审核的字段
	 * @param customer	当前登录人
	 * @return
	 */
	@Override
	public ServerResponse updateNoCheck(CarPersonNoCheckUpdateDto dto, Customer customer) {
		CarPerson carPerson = this.assertDriverBelongToCurrentUser(dto.getId(), customer.getCarOwner().getId());
		BeanUtils.copyProperties(dto, carPerson);
		carPersonRepository.save(carPerson);
		return ServerResponse.successWithData("修改成功");
	}
	
	
	/**
	 * 修改司机压货人需要审核字段
	 * @author cat
	 * 
	 * @param dto	待修改的需要审核的字段
	 * @param customer	当前登录人
	 * @return
	 */
	@Override
	public ServerResponse updateCheck(CarPersonCheckUpdateDto dto, Customer customer) {
		CarPerson carPerson = this.assertDriverBelongToCurrentUser(dto.getId(), customer.getCarOwner().getId());
		BeanUtils.copyProperties(dto, carPerson);
		if(customer.getCarOwner().getAvoidAudit()) { // 免审核
			// carPerson.setStatus(CarPersonConstants.PERSON_STATUS_UP); 保留原状态
		}else {
			carPerson.setStatus(CarPersonConstants.PERSON_STATUS_WAIT_CHECK);
		}
		carPersonRepository.save(carPerson);
		return ServerResponse.successWithData("修改成功");
	}
	
	
	
	
	
	
	/**
	 * 提交审核
	 * @author cat
	 * 
	 * @param dto	待提交审核的司机或压货人Id
	 * @param customer	当前登录人
	 * @return
	 */
	@Override
	public ServerResponse submitAudit(CommonDto dto, Customer customer) {
		CarPerson carPerson = this.assertDriverBelongToCurrentUser(dto.getId(), customer.getCarOwner().getId());
		if(!CarPersonConstants.PERSON_STATUS_DOWN.equalsIgnoreCase(carPerson.getStatus())) {
			throw new BusinessException(112, "提交审核失败：仅下架状态的司机或压货人可以提交审核");
		}
		if(customer.getCarOwner().getAvoidAudit()) { // 免审核
			carPersonRepository.updateStatusById(dto.getId(), CarPersonConstants.PERSON_STATUS_UP);
		}else {
			carPersonRepository.updateStatusById(dto.getId(), CarPersonConstants.PERSON_STATUS_WAIT_CHECK);
		}
		return ServerResponse.successWithData("提交审核成功");
	}
	
	
	
	@Override
	public ServerResponse submitApprove(CommonDto dto, Customer customer) {
		CarPerson carPerson = this.assertDriverBelongToCurrentUser(dto.getId(), customer.getCarOwner().getId());
		// 仅已取消状态的司机或压货人可以提交审批
		if(!CarPersonConstants.APPROVE_STATUS_CANCEL.equalsIgnoreCase(carPerson.getApproveStatus())) {
			throw new BusinessException(112, "提交审批失败：司机或押货人的状态不是已取消");
		}
		this.approve(customer.getCarOwner().getAvoidAudit(), carPerson);
		return ServerResponse.successWithData("提交审核成功");
	}
	
	
	/**
	 * 司机压货人的审批
	 * @author cat
	 * 
	 * @param result	审批结果
	 * @param carPerson	司机或压货人
	 */
	private void approve(boolean result, CarPerson carPerson) {
		Integer id = carPerson.getId();
		if(result) { 
			// 审批通过
			carPerson.setApproveStatus(CarPersonConstants.APPROVE_STATUS_BE_APPROVED);
			if(carPerson.getStatus().equalsIgnoreCase(CarPersonConstants.PERSON_STATUS_ORIGINAL)) {
				carPerson.setStatus(CarPersonConstants.PERSON_STATUS_FREE);
			}
			String approveContent = carPerson.getApproveContent();
			CarPersonCheckUpdateDto temp = JSON.parseObject(approveContent, CarPersonCheckUpdateDto.class);
			BeanUtils.copyProperties(temp, carPerson);
			carPerson.setId(id);
		}else { 
			// 审批未通过
			carPerson.setApproveStatus(CarPersonConstants.APPROVE_STATUS_NOT_APPROVED);
		}
		carPersonRepository.save(carPerson);
	}
	
	
	@Override
	public ServerResponse cancelApprove(CommonDto dto, Customer customer) {
		CarPerson carPerson = this.assertDriverBelongToCurrentUser(dto.getId(), customer.getCarOwner().getId());
		// 仅待审批状态的司机或压货人可以取消审批
		if(!CarPersonConstants.APPROVE_STATUS_APPROVAL_PENDING.equalsIgnoreCase(carPerson.getApproveStatus())) {
			throw new BusinessException(112, "取消审批失败：司机或押货人的状态不是待审批");
		}
		carPerson.setApproveStatus(CarPersonConstants.APPROVE_STATUS_CANCEL);
		carPersonRepository.save(carPerson);
		return ServerResponse.successWithData("取消审批成功");
	}
	
	
	
	@Transactional
	@Override
	public ServerResponse create(CarPersonAddDto dto, Customer customer) {
		// 创建登录账号（目前仅司机创建账号）
		Integer newCustomerId = null;
		if(CarPersonConstants.PERSON_TYPE_DRIVER.equalsIgnoreCase(dto.getPersonType())) {
			newCustomerId = this.createDriverCustomer(dto, customer.getCarOwner().getAvoidAudit(), customer.getId());
		}
		// 创建车主或压货人
		this.createCarPerson(dto, customer, newCustomerId);
		return ServerResponse.successWithData("添加成功");
	}
	
	
	/**
	 * 创建车主或压货人
	 * @author cat
	 * 
	 * @param dto	司机压货人基本信息
	 * @param customer	当前登录人
	 * @param newCustomerId	新创建的司机账号Id
	 * @return	新创建的司机或压货人
	 */
	private CarPerson createCarPerson(CarPersonAddDto dto, Customer customer, Integer newCustomerId) {
		CarCargoOwnner carOwner = customer.getCarOwner();
		CarPerson carPerson = new CarPerson();
		BeanUtils.copyProperties(dto, carPerson);
		carPerson.setCreateDate(new Date());
		carPerson.setIsDelete(false);
		carPerson.setId(null);
		carPerson.setCreateBy(customer.getId());
		carPerson.setCarOwnerId(carOwner.getId());
		carPerson.setCustomerId(newCustomerId);
		if(carOwner.getAvoidAudit()) { // 免审核
			carPerson.setStatus(CarPersonConstants.PERSON_STATUS_FREE); // 空闲
			carPerson.setApproveStatus(CarPersonConstants.APPROVE_STATUS_BE_APPROVED); // 审批通过
		}else {
			carPerson.setStatus(CarPersonConstants.PERSON_STATUS_ORIGINAL); // 初始状态
			carPerson.setApproveStatus(CarPersonConstants.APPROVE_STATUS_APPROVAL_PENDING); // 待审批
		}
		// 将需要审核的字段构建成JSON字符串保存到“approveContent”中
		CarPersonCheckUpdateDto temp = new CarPersonCheckUpdateDto();
		BeanUtils.copyProperties(dto, temp);
		String approveContent = JSON.toJSONString(temp);
		carPerson.setApproveContent(approveContent);
		carPersonRepository.save(carPerson);
		return carPerson;
	}
	
	
	@Override
	public ServerResponse updateNeedApprove(CarPersonCheckUpdateDto dto, Customer customer) {
		CarPerson carPerson = this.assertDriverBelongToCurrentUser(dto.getId(), customer.getCarOwner().getId());
		// 待审批状态的司机压货人不可修改
		if(CarPersonConstants.APPROVE_STATUS_APPROVAL_PENDING.equalsIgnoreCase(carPerson.getApproveStatus())) {
			throw new BusinessException(112, "修改失败：当前状态为待审批，需先取消审批");
		}
		if(customer.getCarOwner().getAvoidAudit()) { // 免审核
			BeanUtils.copyProperties(dto, carPerson);
			carPerson.setApproveStatus(CarPersonConstants.APPROVE_STATUS_BE_APPROVED);
			if(carPerson.getStatus().equalsIgnoreCase(CarPersonConstants.PERSON_STATUS_ORIGINAL)) {
				carPerson.setStatus(CarPersonConstants.PERSON_STATUS_FREE);
			}
		}else {
			carPerson.setApproveStatus(CarPersonConstants.APPROVE_STATUS_APPROVAL_PENDING);
		}
		carPerson.setApproveContent(JSON.toJSONString(dto));
		carPersonRepository.save(carPerson);
		return ServerResponse.successWithData("修改成功");
	}
	
	
	
}
