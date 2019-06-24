package com.zrytech.framework.app.service.impl;

import java.util.Date;
import java.util.List;


import com.zrytech.framework.app.constants.*;
import com.zrytech.framework.app.entity.SysMessage;
import com.zrytech.framework.app.repository.SysMessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zrytech.framework.app.repository.CarCargoOwnnerRepository;
import com.zrytech.framework.app.repository.CarPersonRepository;
import com.zrytech.framework.app.repository.LogisticsCustomerRepository;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.zrytech.framework.app.dto.CommonDto;
import com.zrytech.framework.app.dto.approve.ApproveDto;
import com.zrytech.framework.app.dto.carperson.AdminDriverPageDto;
import com.zrytech.framework.app.dto.carperson.AdminSupercargoPageDto;
import com.zrytech.framework.app.dto.carperson.CarPersonAddDto;
import com.zrytech.framework.app.dto.carperson.CarPersonCheckUpdateDto;
import com.zrytech.framework.app.dto.carperson.CarPersonEnabledDto;
import com.zrytech.framework.app.dto.carperson.CarPersonNoCheckUpdateDto;
import com.zrytech.framework.app.dto.carperson.CarPersonPageDto;
import com.zrytech.framework.app.entity.CarCargoOwnner;
import com.zrytech.framework.app.entity.CarPerson;
import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.app.mapper.CarPersonMapper;
import com.zrytech.framework.app.service.ApproveLogService;
import com.zrytech.framework.app.service.CarPersonService;
import com.zrytech.framework.app.service.CustomerService;
import com.zrytech.framework.app.utils.PasswordUtils;
import com.zrytech.framework.base.entity.PageData;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.common.entity.User;
import org.springframework.transaction.annotation.Transactional;

/**
 * 司机与押货人
 * 
 * @author cat
 *
 */
@Service
@Transactional
@Slf4j
public class CarPersonServiceImpl implements CarPersonService {
	
	@Autowired
	private CarPersonRepository carPersonRepository;

	@Autowired
	private CarPersonMapper carPersonMapper;

	@Autowired
	private CarCargoOwnnerRepository carCargoOwnnerRepository;

	@Autowired
	private LogisticsCustomerRepository customerRepository;

	@Autowired
	private ApproveLogService approveLogService;
	
	@Autowired
	private CustomerService customerService;

	@Autowired
	private SysMessageRepository sysMessageRepository;

	@Override
	public ServerResponse page(CarPersonPageDto dto, Integer pageNum, Integer pageSize) {
		PageData<CarPerson> pageData = this.carPersonPage(dto, pageNum, pageSize);
		return ServerResponse.successWithData(pageData);
	}
	
	/**
	 * 司机压货人分页
	 * @author cat
	 * 
	 * @param dto
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageData<CarPerson> carPersonPage(CarPersonPageDto dto, Integer pageNum, Integer pageSize) {
		com.github.pagehelper.Page<Object> result = PageHelper.startPage(pageNum, pageSize);
		List<CarPerson> list = carPersonMapper.selectSelective(dto);
		for (CarPerson carPerson : list) {
			carPerson.setCarOwnerName(carCargoOwnnerRepository.findNameById(carPerson.getCarOwnerId()));
			carPerson = this.bindingDriverCustomerStatus(carPerson);
			if (carPerson.getPersonType().equalsIgnoreCase(CarPersonConstants.PERSON_TYPE_DRIVER)) {
				Customer customer = customerRepository.findOne(carPerson.getCustomerId());
				carPerson.setCustomerTel(customer.getTel());
				carPerson.setCustomerUserAccount(customer.getUserAccount());
			}
		}
		return new PageData<CarPerson>(result.getPageSize(), result.getPageNum(), result.getTotal(), list);
	}
	
	@Override
	public ServerResponse adminDriverPage(AdminDriverPageDto dto, Integer pageNum, Integer pageSize) {
		CarPersonPageDto carPersonPageDto = new CarPersonPageDto();
		BeanUtils.copyProperties(dto, carPersonPageDto);
		carPersonPageDto.setPersonType(CarPersonConstants.PERSON_TYPE_DRIVER);
		return page(carPersonPageDto, pageNum, pageSize);
	}

	@Override
	public ServerResponse adminSupercargoPage(AdminSupercargoPageDto dto, Integer pageNum, Integer pageSize) {
		CarPersonPageDto carPersonPageDto = new CarPersonPageDto();
		BeanUtils.copyProperties(dto, carPersonPageDto);
		carPersonPageDto.setPersonType(CarPersonConstants.PERSON_TYPE_SUPERCARGO);
		return page(carPersonPageDto, pageNum, pageSize);
	}

	public ServerResponse adminOneCarOwnerDriverPage(AdminDriverPageDto dto, Integer pageNum, Integer pageSize) {
		if (dto.getCarOwnerId() == null) {
			throw new BusinessException(112, "车主不能为空");
		}
		return adminDriverPage(dto, pageNum, pageSize);
	}

	public ServerResponse adminOneCarOwnerSupercargoPage(AdminSupercargoPageDto dto, Integer pageNum,
			Integer pageSize) {
		if (dto.getCarOwnerId() == null) {
			throw new BusinessException(112, "车主不能为空");
		}
		return adminSupercargoPage(dto, pageNum, pageSize);
	}

	@Override
	public ServerResponse adminApprovePendingDriverPage(AdminDriverPageDto dto, Integer pageNum, Integer pageSize) {
		dto.setIsDelete(false);
		dto.setApproveStatus(ApproveConstants.STATUS_APPROVAL_PENDING);
		return adminDriverPage(dto, pageNum, pageSize);
	}

	@Override
	public ServerResponse adminApprovePendingSupercargoPage(AdminSupercargoPageDto dto, Integer pageNum,
			Integer pageSize) {
		dto.setIsDelete(false);
		dto.setApproveStatus(ApproveConstants.STATUS_APPROVAL_PENDING);
		return adminSupercargoPage(dto, pageNum, pageSize);
	}
	
	@Override
	public ServerResponse adminDriverDetails(CommonDto dto) {
		CarPerson carPerson = this.assertDriverExist(dto.getId());
		carPerson = this.bindingCarOwner(carPerson);
		carPerson = this.bindingDriverCustomerStatus(carPerson);
		return ServerResponse.successWithData(carPerson);
	}

	@Override
	public ServerResponse adminSupercargoDetails(CommonDto dto) {
		CarPerson carPerson = this.assertSupercargoExist(dto.getId());
		carPerson = this.bindingCarOwner(carPerson);
		return ServerResponse.successWithData(carPerson);
	}

	@Override
	@Transactional(noRollbackFor = BusinessException.class)
	public ServerResponse adminDriverApprove(ApproveDto dto, User user) {
		CarPerson carPerson = this.assertDriverAvailable(dto.getBusinessId());
		if (!ApproveConstants.STATUS_APPROVAL_PENDING.equalsIgnoreCase(carPerson.getApproveStatus())) {
			String content = "您的认证资料审核不通过，原因如下：司机的状态不是待审批";
//			Object[] propArr = new Object[]{"系统消息",1,"系统",new Date(),
//					content,carOwner.getId(),"司机",0,null};
			Object[] propArr = new Object[]{SysMessageConstants.MSG_TYPE_APPROVING,1,SysMessageConstants.SEND_TYPE_SYSTEM,new Date(),
					content,carPerson.getId(),CustomerConstants.TYPE_DRIVER,0,null};
			createMes(propArr);

			throw new BusinessException(112, "审批失败：司机的状态不是待审批");
		}
		this.approve(ApproveConstants.RESULT_AGREE.equals(dto.getResult()), carPerson);
		approveLogService.addApproveLog(dto, user.getId(), ApproveLogConstants.APPROVE_TYPE_CAR_PERSON_DRIVER);

		//人为处理审核成功失败情况
		if(ApproveConstants.RESULT_AGREE.equalsIgnoreCase(dto.getResult())){
			String content = "您的认证资料已审核通过，请须知";
			Object[] propArr = new Object[]{SysMessageConstants.MSG_TYPE_APPROVING,1,SysMessageConstants.SEND_TYPE_SYSTEM,new Date(),
					content,carPerson.getId(),CustomerConstants.TYPE_DRIVER,0,null};
			createMes(propArr);
		}else{
			String content = "您的认证资料审核不通过，原因如下:"+dto.getContent();
			Object[] propArr = new Object[]{SysMessageConstants.MSG_TYPE_APPROVING,1,SysMessageConstants.SEND_TYPE_SYSTEM,new Date(),
					content,carPerson.getId(),CustomerConstants.TYPE_DRIVER,0,null};
			createMes(propArr);
		}

		return ServerResponse.successWithData("审批成功");
	}

	@Override
	public ServerResponse adminSupercargoApprove(ApproveDto dto, User user) {
		CarPerson carPerson = this.assertSupercargoAvailable(dto.getBusinessId());
		if (!ApproveConstants.STATUS_APPROVAL_PENDING.equalsIgnoreCase(carPerson.getApproveStatus())) {
			throw new BusinessException(112, "审批失败：压货人的状态不是待审批");
		}
		this.approve(ApproveConstants.RESULT_AGREE.equals(dto.getResult()), carPerson);
		approveLogService.addApproveLog(dto, user.getId(), ApproveLogConstants.APPROVE_TYPE_CAR_PERSON_SUPERCARGO);
		return ServerResponse.successWithData("审批成功");
	}

	/**
	 * 为司机或者押货人绑定车主信息
	 * @author cat
	 * 
	 * @param carPerson
	 * @return
	 */
	private CarPerson bindingCarOwner(CarPerson carPerson) {
		if (carPerson.getCarOwnerId() != null) {
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
		if (carPerson.getPersonType().equalsIgnoreCase(CarPersonConstants.PERSON_TYPE_DRIVER)) {
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
		if (carPerson == null) {
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
		if (carPerson.getIsDelete()) {
			throw new BusinessException(112, "司机或压货人不存在");
		}
	}
	
	/**
	 * 断言司机没有被删除
	 * @param carPerson	司机
	 */
	private void assertDriverNotDelete(CarPerson carPerson) {
		if (carPerson.getIsDelete()) {
			throw new BusinessException(112, "司机不存在");
		}
	}
	
	/**
	 * 断言司机存在
	 * @author cat
	 * 
	 * @param driverId
	 * @return
	 */
	public CarPerson assertDriverExist(Integer driverId) {
		CarPerson carPerson = carPersonRepository.findByIdAndPersonType(driverId,
				CarPersonConstants.PERSON_TYPE_DRIVER);
		if (carPerson == null)
			throw new BusinessException(112, "司机不存在");
		return carPerson;
	}
	
	/**
	 * 断言压货人存在
	 * @author cat
	 * 
	 * @param supercargoId
	 * @return
	 */
	public CarPerson assertSupercargoExist(Integer supercargoId) {
		CarPerson carPerson = carPersonRepository.findByIdAndPersonType(supercargoId,
				CarPersonConstants.PERSON_TYPE_SUPERCARGO);
		if (carPerson == null)
			throw new BusinessException(112, "压货人不存在");
		return carPerson;
	}
	
	/**
	 * 断言压货人没有被删除
	 * @param carPerson	压货人
	 */
	private void assertSupercargoNotDelete(CarPerson carPerson) {
		if (carPerson.getIsDelete()) {
			throw new BusinessException(112, "压货人不存在");
		}
	}
	
	@Override
	public CarPerson assertDriverAvailable(Integer driverId) {
		CarPerson carPerson = this.assertDriverExist(driverId);
		this.assertDriverNotDelete(carPerson);
		return carPerson;
	}

	@Override
	public CarPerson assertSupercargoAvailable(Integer supercargoId) {
		CarPerson carPerson = this.assertSupercargoExist(supercargoId);
		this.assertSupercargoNotDelete(carPerson);
		return carPerson;
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
		if (carPerson == null) {
			throw new BusinessException(112, "司机或压货人不存在");
		}
		return carPerson;
	}

	@Override
	public CarPerson assertDriverBelongToCurrentUser(Integer driverId, Integer carOwnerId) {
		CarPerson carPerson = carPersonRepository.findByIdAndIsDeleteAndCarOwnerId(driverId, false, carOwnerId);
		if (carPerson == null || !carPerson.getPersonType().equalsIgnoreCase(CarPersonConstants.PERSON_TYPE_DRIVER)) {
			throw new BusinessException(112, "司机不存在");
		}
		return carPerson;
	}

	@Override
	public CarPerson assertSupercargoBelongToCurrentUser(Integer supercargoId, Integer carOwnerId) {
		CarPerson carPerson = carPersonRepository.findByIdAndIsDeleteAndCarOwnerId(supercargoId, false, carOwnerId);
		if (carPerson == null
				|| !carPerson.getPersonType().equalsIgnoreCase(CarPersonConstants.PERSON_TYPE_SUPERCARGO)) {
			throw new BusinessException(112, "压货人不存在");
		}
		return carPerson;
	}
	

	/*以下为车主及车主子账号接口*/
	
	
	/**
	 * 若司机已登录,退出司机登录态
	 * @author cat
	 * 
	 * @param driverId	司机Id
	 */
	private void driverLogout(Integer driverId) {
		// TODO

	}
	
	@Transactional
	@Override
	public ServerResponse enabled(CarPersonEnabledDto dto, Customer customer) {
		CarPerson carPerson = this.assertDriverBelongToCurrentUser(dto.getId(), customer.getCarOwner().getId());
		if (carPerson.getStatus().equalsIgnoreCase(CarPersonConstants.PERSON_STATUS_UNCERTIFIED)) {
			throw new BusinessException(112, "未认证的司机无法修改账号的启用禁用状态");
		}
		customerRepository.updateIsActiveById(carPerson.getCustomerId(), dto.getEnabled());
		if (dto.getEnabled() == false) { // 禁用
			this.driverLogout(dto.getId());
			return ServerResponse.successWithData("禁用成功");
		} else {
			return ServerResponse.successWithData("启用成功");
		}
	}
	
	@Transactional
	@Override
	public ServerResponse delete(CommonDto dto, Customer customer) {
		CarPerson carPerson = this.assertCarPersonBelongToCurrentUser(dto.getId(), customer.getCarOwner().getId());
		if (!carPerson.getStatus().equalsIgnoreCase(CarPersonConstants.PERSON_STATUS_UNCERTIFIED)) {
			throw new BusinessException(112, "仅可删除未认证的司机或压货人");
		}
		if (carPerson.getPersonType().equalsIgnoreCase(CarPersonConstants.PERSON_TYPE_DRIVER)) {
			customerRepository.updateIsActiveById(carPerson.getCustomerId(), false);
			this.driverLogout(dto.getId());
		}
		carPersonRepository.deleteCarPersonById(dto.getId());
		return ServerResponse.successWithData("删除成功");
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
		if (result) {
			carPerson.setApproveStatus(ApproveConstants.STATUS_BE_APPROVED);
			if (carPerson.getStatus().equalsIgnoreCase(CarPersonConstants.PERSON_STATUS_UNCERTIFIED)) {
				carPerson.setStatus(CarPersonConstants.PERSON_STATUS_FREE);
			}
			String approveContent = carPerson.getApproveContent();
			CarPersonCheckUpdateDto temp = JSON.parseObject(approveContent, CarPersonCheckUpdateDto.class);
			BeanUtils.copyProperties(temp, carPerson);
			carPerson.setId(id);
		} else {
			carPerson.setApproveStatus(ApproveConstants.STATUS_NOT_APPROVED);
		}
		carPersonRepository.save(carPerson);
	}
	
	@Transactional
	@Override
	public ServerResponse submitApprove(CommonDto dto, Customer customer) {
		CarPerson carPerson = this.assertDriverBelongToCurrentUser(dto.getId(), customer.getCarOwner().getId());
		if (!ApproveConstants.STATUS_CANCEL.equalsIgnoreCase(carPerson.getApproveStatus())) {
			throw new BusinessException(112, "提交审批失败：司机或押货人的审批状态不是已取消");
		}
		carPerson.setApproveStatus(ApproveConstants.STATUS_APPROVAL_PENDING);
		carPersonRepository.save(carPerson);
		return ServerResponse.successWithData("提交审批成功");
	}
	
	@Transactional
	@Override
	public ServerResponse cancelApprove(CommonDto dto, Customer customer) {
		CarPerson carPerson = this.assertDriverBelongToCurrentUser(dto.getId(), customer.getCarOwner().getId());
		if (!ApproveConstants.STATUS_APPROVAL_PENDING.equalsIgnoreCase(carPerson.getApproveStatus())) {
			throw new BusinessException(112, "取消审批失败：司机或押货人的审批状态不是待审批");
		}
		carPerson.setApproveStatus(ApproveConstants.STATUS_CANCEL);
		carPersonRepository.save(carPerson);
		return ServerResponse.successWithData("取消审批成功");
	}
	
	@Transactional
	@Override
	public ServerResponse create(CarPersonAddDto dto, Customer customer) {
		// 创建登录账号（目前仅司机创建账号）
		Integer newCustomerId = null;
		if (CarPersonConstants.PERSON_TYPE_DRIVER.equalsIgnoreCase(dto.getPersonType())) {
			//设置账号为用户手机号
			dto.setUserAccount(dto.getTel());
			//密码随机生成6位字符串
			dto.setPassword(RandomStringUtils.randomAlphanumeric(6)+"8#");
			newCustomerId = this.createDriverCustomer(dto, customer.getId());
		}
		// 创建司机或压货人
		this.createCarPerson(dto, customer, newCustomerId);
		return ServerResponse.successWithData("添加成功");
	}
	
	/**   
	 * 创建司机账号
	 * @author cat
	 * 
	 * @param dto	司机账号信息
	 * @param createBy	创建人Id
	 * @return	司机账号Id
	 */
	private Integer createDriverCustomer(CarPersonAddDto dto, Integer createBy) {
		if (StringUtils.isBlank(dto.getUserAccount())) {
			throw new BusinessException(112, "用户名不能为空");
		}
		if (StringUtils.isBlank(dto.getPassword())) {
			throw new BusinessException(112, "密码不能为空");
		}
		if (StringUtils.isBlank(dto.getUserTel())) {
			throw new BusinessException(112, "手机号码不能为空");
		}
		customerService.assertCustomerNotExist(dto.getUserTel(), dto.getUserAccount());
		Customer customer = new Customer();
		customer.setCreateDate(new Date());
		customer.setUserAccount(dto.getUserAccount());
		customer.setUserName(dto.getUserAccount());
		customer.setPassword(PasswordUtils.encryptStringPassword(dto.getPassword(), dto.getUserAccount()));
		customer.setTel(dto.getUserTel());
		customer.setCreateBy(createBy);
		customer.setCustomerType(CustomerConstants.TYPE_DRIVER);
		customer.setUserStatus(1);
		customer.setIsActive(false);
		customerRepository.save(customer);
		return customer.getId();
	}
	
	/**
	 * 创建司机或压货人
	 * @author cat
	 * 
	 * @param dto	司机或压货人基本信息
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
		carPerson.setStatus(CarPersonConstants.PERSON_STATUS_UNCERTIFIED);
		carPerson.setApproveStatus(ApproveConstants.STATUS_APPROVAL_PENDING);
		CarPersonCheckUpdateDto temp = new CarPersonCheckUpdateDto();
		BeanUtils.copyProperties(dto, temp);
		carPerson.setApproveContent(JSON.toJSONString(temp));
		carPersonRepository.save(carPerson);
		return carPerson;
	}
	
	@Override
	public ServerResponse updateNoCheck(CarPersonNoCheckUpdateDto dto, Customer customer) {
		CarPerson carPerson = this.assertCarPersonBelongToCurrentUser(dto.getId(), customer.getCarOwner().getId());
		BeanUtils.copyProperties(dto, carPerson);
		carPersonRepository.save(carPerson);
		return ServerResponse.successWithData("修改成功");
	}

	@Transactional
	@Override
	public ServerResponse updateNeedApprove(CarPersonCheckUpdateDto dto, Customer customer) {
		CarPerson carPerson = this.assertCarPersonBelongToCurrentUser(dto.getId(), customer.getCarOwner().getId());
		if (ApproveConstants.STATUS_APPROVAL_PENDING.equalsIgnoreCase(carPerson.getApproveStatus())) {
			throw new BusinessException(112, "修改失败：不能修改审批状态是待审批的司机或压货人");
		}
		carPerson.setApproveStatus(ApproveConstants.STATUS_APPROVAL_PENDING);
		carPerson.setApproveContent(JSON.toJSONString(dto));
		carPersonRepository.save(carPerson);
		return ServerResponse.successWithData("修改成功");
	}

	@Override
	public ServerResponse myDriver(CarPersonPageDto dto, Integer pageNum, Integer pageSize, Customer customer) {
		dto.setCarOwnerId(customer.getCarOwner().getId());
		dto.setIsDelete(false);
		dto.setPersonType(CarPersonConstants.PERSON_TYPE_DRIVER);
		return page(dto, pageNum, pageSize);
	}
	
	public ServerResponse myDrivers(CarPersonPageDto dto, Integer pageNum, Integer pageSize, Customer customer) {
		dto.setCarOwnerId(customer.getCarOwner().getId());
		dto.setIsDelete(false);
		dto.setPersonType(CarPersonConstants.PERSON_TYPE_DRIVER);
		dto.setUnstatus(CarPersonConstants.PERSON_STATUS_UNCERTIFIED);

		com.github.pagehelper.Page<Object> result = PageHelper.startPage(pageNum, pageSize);
		List<CarPerson> list = carPersonMapper.selectDriver(dto);
		for (CarPerson carPerson : list) {
			carPerson.setCarOwnerName(carCargoOwnnerRepository.findNameById(carPerson.getCarOwnerId()));
			Customer temp = customerRepository.findOne(carPerson.getCustomerId());
			carPerson.setCustomerTel(temp.getTel());
			carPerson.setCustomerUserAccount(temp.getUserAccount());
			carPerson.setIsActive(temp.getIsActive());
		}

		PageData<CarPerson> pageData = new PageData<CarPerson>(result.getPageSize(), result.getPageNum(),
				result.getTotal(), list);
		return ServerResponse.successWithData(pageData);
	}
	
	
	public ServerResponse mySupercargos(CarPersonPageDto dto, Integer pageNum, Integer pageSize, Customer customer) {
		dto.setCarOwnerId(customer.getCarOwner().getId());
		dto.setIsDelete(false);
		dto.setPersonType(CarPersonConstants.PERSON_TYPE_SUPERCARGO);
		dto.setUnstatus(CarPersonConstants.PERSON_STATUS_UNCERTIFIED);

		com.github.pagehelper.Page<Object> result = PageHelper.startPage(pageNum, pageSize);
		List<CarPerson> list = carPersonMapper.selectDriver(dto);
		for (CarPerson carPerson : list) {
			carPerson.setCarOwnerName(carCargoOwnnerRepository.findNameById(carPerson.getCarOwnerId()));
		}

		PageData<CarPerson> pageData = new PageData<CarPerson>(result.getPageSize(), result.getPageNum(),
				result.getTotal(), list);
		return ServerResponse.successWithData(pageData);
	}

	@Override
	public ServerResponse mySupercargo(CarPersonPageDto dto, Integer pageNum, Integer pageSize, Customer customer) {
		dto.setCarOwnerId(customer.getCarOwner().getId());
		dto.setIsDelete(false);
		dto.setPersonType(CarPersonConstants.PERSON_TYPE_SUPERCARGO);
		return page(dto, pageNum, pageSize);
	}
	
	@Override
	public ServerResponse details(CommonDto dto, Customer customer) {
		CarPerson carPerson = this.assertCarPersonBelongToCurrentUser(dto.getId(), customer.getCarOwner().getId());
		carPerson = this.bindingCarOwner(carPerson);
		return ServerResponse.successWithData(carPerson);
	}

	@Override
	public void assertDriverCertified(CarPerson driver) {
		if(CarPersonConstants.PERSON_STATUS_UNCERTIFIED.equalsIgnoreCase(driver.getStatus())) {
			throw new BusinessException(112, "司机未认证");
		}
	}

	@Override
	public void assertSupercargoCertified(CarPerson supercargo) {
		if(CarPersonConstants.PERSON_STATUS_UNCERTIFIED.equalsIgnoreCase(supercargo.getStatus())) {
			throw new BusinessException(112, "压货人未认证");
		}
	}
	
	@Override
	public void assertCarPersonCertified(Integer carPersonId) {
		CarPerson carPerson = carPersonRepository.findOne(carPersonId);
		if (carPerson.getStatus().equalsIgnoreCase(CarPersonConstants.PERSON_STATUS_UNCERTIFIED)) {
			throw new BusinessException(112, "未认证");
		}
	}

	private void createMes(Object[] propArr) {
		try{
			SysMessage sysMessage = new SysMessage(propArr);
			sysMessageRepository.saveAndFlush(sysMessage);
		}catch (Exception e){
			log.warn(e.getMessage());
		}

	}
}
