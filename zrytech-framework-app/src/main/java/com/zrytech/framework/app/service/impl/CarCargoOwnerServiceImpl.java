package com.zrytech.framework.app.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.zrytech.framework.app.constants.ApproveConstants;
import com.zrytech.framework.app.constants.ApproveLogConstants;
import com.zrytech.framework.app.constants.CarCargoOwnerConstants;
import com.zrytech.framework.app.dto.CarCargoOwnnerPageDto;
import com.zrytech.framework.app.dto.CommonDto;
import com.zrytech.framework.app.dto.DetailsDto;
import com.zrytech.framework.app.dto.approve.ApproveDto;
import com.zrytech.framework.app.dto.carcargoowner.CarCargoOwnerAddDto;
import com.zrytech.framework.app.dto.carcargoowner.CarCargoOwnerNeedApproveDto;
import com.zrytech.framework.app.dto.carcargoowner.CarCargoOwnerUpdateAvoidAuditDto;
import com.zrytech.framework.app.dto.carcargoowner.CarCargoOwnerUpdateDto;
import com.zrytech.framework.app.dto.carcargoowner.OrganizeInfoUpdateDto;
import com.zrytech.framework.app.dto.carcargoowner.PersonInfoUpdateDto;
import com.zrytech.framework.app.dto.customer.CustomerRegisterDto;
import com.zrytech.framework.app.dto.oftenaddress.OftenAddressAddDto;
import com.zrytech.framework.app.entity.CarCargoOwnner;
import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.app.entity.OftenAddress;
import com.zrytech.framework.app.mapper.CarCargoOwnerMapper;
import com.zrytech.framework.app.repository.CarCargoOwnnerRepository;
import com.zrytech.framework.app.repository.LogisticsCustomerRepository;
import com.zrytech.framework.app.repository.OftenAddressRepository;
import com.zrytech.framework.app.service.ApproveLogService;
import com.zrytech.framework.app.service.CarCargoOwnerService;
import com.zrytech.framework.app.service.CustomerService;
import com.zrytech.framework.app.utils.PasswordUtils;
import com.zrytech.framework.base.entity.PageData;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.common.entity.User;

@Service
public class CarCargoOwnerServiceImpl implements CarCargoOwnerService {

	@Autowired
	private CarCargoOwnnerRepository carCargoOwnnerRepository;

	@Autowired
	private CarCargoOwnerMapper carCargoOwnerMapper;

	@Autowired
	private LogisticsCustomerRepository customerRepository;

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private OftenAddressRepository oftenAddressRepository;
	
	
	
	/**
	 * 添加用户账号数据
	 * @author cat
	 * 
	 * @param dto
	 * @return
	 */
	private Customer saveCustomer(CustomerRegisterDto dto) {
		Customer customer = new Customer();
		BeanUtils.copyProperties(dto, customer);
		if (StringUtils.isBlank(dto.getUserName())) {
			customer.setUserName(dto.getUserAccount());
		}
		customer.setId(null);
		customer.setCreateBy(0);
		customer.setUserStatus(1);
		customer.setCreateDate(new Date());
		customer.setIsActive(true);
		customer.setPassword(PasswordUtils.encryptStringPassword(dto.getPassword(), dto.getUserAccount()));
		customerRepository.save(customer);
		return customer;
	}
	
	/**
	 * 验证码校验
	 * @author cat
	 * 
	 * @param code	验证码
	 * @param tel	手机号
	 */
	private void codeCheck(String code, String tel) {
		// TODO 
	}
	
	/**
	 * 添加车主或货主
	 * @author cat
	 * 
	 * @param dto	车主或货主信息
	 * @param customerId	车主或货主账号Id
	 * @param type	类型：车主、货主
	 * @param referrerId	推荐人Id
	 * @return
	 */
	private CarCargoOwnner saveCarCargoOwnner(CarCargoOwnerAddDto dto, Integer customerId, String type,
			Integer referrerId) {
		this.check(dto);
		CarCargoOwnner carCargoOwner = new CarCargoOwnner();
		if (dto.getJump()) { // 跳过资料认证，资料默认为空
			carCargoOwner.setStatus(CarCargoOwnerConstants.STATUS_UNCERTIFIED);
		} else {
			carCargoOwner.setStatus(CarCargoOwnerConstants.STATUS_INCERTIFIED);
			BeanUtils.copyProperties(dto, carCargoOwner);
			if (dto.getCustomerType().equalsIgnoreCase(CarCargoOwnerConstants.CUSTOMER_TYPE_ORGANIZE)) { // 企业
				OrganizeInfoUpdateDto temp = new OrganizeInfoUpdateDto();
				BeanUtils.copyProperties(dto, temp);
				carCargoOwner.setApproveContent(JSON.toJSONString(temp));
				carCargoOwner.setGender(null);
			}else if (dto.getCustomerType().equalsIgnoreCase(CarCargoOwnerConstants.CUSTOMER_TYPE_PERSON)) { // 个人
				PersonInfoUpdateDto temp = new PersonInfoUpdateDto();
				BeanUtils.copyProperties(dto, temp);
				carCargoOwner.setApproveContent(JSON.toJSONString(temp));
				carCargoOwner.setName(temp.getLegalerName());
				carCargoOwner.setCreditCode(null);
				carCargoOwner.setBusinessLicense(null);
			}
		}
		carCargoOwner.setCustomerType(dto.getCustomerType());
		carCargoOwner.setType(type);
		carCargoOwner.setCustomerId(customerId);
		carCargoOwner.setRefereesId(referrerId);
		carCargoOwner.setCreateDate(new Date());
		carCargoOwner.setAvoidAudit(false);
		carCargoOwner.setApproveStatus(ApproveConstants.STATUS_APPROVAL_PENDING);
		carCargoOwnnerRepository.save(carCargoOwner);
		return carCargoOwner;
	}
	
	/**
	 * 认证参数校验
	 * @param dto
	 */
	private void check(CarCargoOwnerAddDto dto) {
		if (!dto.getJump()) {
			if (StringUtils.isBlank(dto.getLegalerIdCardNo())) {
				throw new BusinessException(112, "身份证号码不能为空");
			}
			if (StringUtils.isBlank(dto.getLegalerIdCardFront())) {
				throw new BusinessException(112, "身份证照片不能为空");
			}
			if (StringUtils.isBlank(dto.getLegalerName())) {
				throw new BusinessException(112, "姓名不能为空");
			}
			if (StringUtils.isBlank(dto.getTel())) {
				throw new BusinessException(112, "联系电话不能为空");
			}
			if (dto.getCustomerType().equalsIgnoreCase(CarCargoOwnerConstants.CUSTOMER_TYPE_ORGANIZE)) { // 企业
				if (StringUtils.isBlank(dto.getName())) {
					throw new BusinessException(112, "企业名称不能为空");
				}
				if (StringUtils.isBlank(dto.getCreditCode())) {
					throw new BusinessException(112, "信用代码不能为空");
				}
				if (StringUtils.isBlank(dto.getBusinessLicense())) {
					throw new BusinessException(112, "营业执照不能为空");
				}
			} else if (dto.getCustomerType().equalsIgnoreCase(CarCargoOwnerConstants.CUSTOMER_TYPE_PERSON)) { // 个人
				if (dto.getGender() == null) {
					throw new BusinessException(112, "性别不能为空");
				}
			}
		}
	}
	
	/**
	 * 推荐人电话校验
	 * @author cat
	 * 
	 * @param referrerTel	推荐人电话
	 * @return 推荐人Id
	 */
	private Integer checkReferencesTel(String referrerTel) {
		if (StringUtils.isNotBlank(referrerTel)) {
			try {
				Customer customer = customerService.assertTelExist(referrerTel);
				return customer.getId();
			} catch (Exception e) {
				throw new BusinessException(112, "推荐人不存在");
			}
		} else {
			return null;
		}
	}
	
	/**
	 * 添加常用地址
	 * @author cat
	 * 
	 * @param list
	 * @param customerId
	 * @return
	 */
	private void saveOftenAddress(List<OftenAddressAddDto> list, Integer customerId) {
		List<OftenAddress> save = new ArrayList<>();
		for (OftenAddressAddDto oftenAddressAddDto : list) {
			OftenAddress oftenAddress = new OftenAddress();
			BeanUtils.copyProperties(oftenAddressAddDto, oftenAddress);
			oftenAddress.setCustomerId(customerId);
			save.add(oftenAddress);
		}
		oftenAddressRepository.save(save);
	}
	
	@Transactional
	@Override
	public ServerResponse register(CustomerRegisterDto dto) {
		// 推荐人手机号校验
		Integer referrerId = this.checkReferencesTel(dto.getReferrerTel());
		// 手机号、用户名校验
		customerService.assertCustomerNotExist(dto.getTel(), dto.getUserAccount());
		// 验证码校验
		this.codeCheck(dto.getCode(), dto.getTel());
		// 添加用户账号数据
		Customer customer = this.saveCustomer(dto);
		// 添加车主或货主
		this.saveCarCargoOwnner(dto.getCarCargoOwner(), customer.getId(), dto.getCustomerType(), referrerId);
		// 添加常用地址
		List<OftenAddressAddDto> oftenAddress = dto.getOftenAddress();
		if (oftenAddress != null && oftenAddress.size() > 0) {
			this.saveOftenAddress(oftenAddress, customer.getId());
		}
		return ServerResponse.success();
	}
	
	@Override
	public ServerResponse checkTel(String tel) {
		customerService.assertTelNotExist(tel);
		return ServerResponse.success();
	}
	
	@Override
	public ServerResponse checkUserAccount(String userAccount) {
		customerService.assertUserAccountNotExist(userAccount);
		return ServerResponse.success();
	}
	
	@Autowired
	private ApproveLogService approveLogService;
	
	@Transactional
	@Override
	public ServerResponse adminApproveCarOwner(ApproveDto dto, User user) {
		CarCargoOwnner carOwner = this.assertCarOwnerExist(dto.getBusinessId());
		if (CarCargoOwnerConstants.STATUS_UNCERTIFIED.equalsIgnoreCase(carOwner.getStatus())) {
			throw new BusinessException(112, "审批失败：车主暂未填写认证信息");
		}
		if (!carOwner.getApproveStatus().equalsIgnoreCase(ApproveConstants.STATUS_APPROVAL_PENDING)) {
			throw new BusinessException(112, "审批失败：车主状态不是待审批");
		}
		this.approveCarCargoOwner(carOwner, ApproveConstants.RESULT_AGREE.equalsIgnoreCase(dto.getResult()));
		approveLogService.addApproveLog(dto, user.getId(), ApproveLogConstants.APPROVE_TYPE_CAR_OWNER);
		return ServerResponse.successWithData("审批成功");
	}
	
	@Transactional
	@Override
	public ServerResponse adminApproveCargoOwner(ApproveDto dto, User user) {
		CarCargoOwnner cargoOwner = this.assertCargoOwnerExist(dto.getBusinessId());
		if (CarCargoOwnerConstants.STATUS_UNCERTIFIED.equalsIgnoreCase(cargoOwner.getStatus())) {
			throw new BusinessException(112, "审批失败：货主暂未填写认证信息");
		}
		if (!cargoOwner.getApproveStatus().equalsIgnoreCase(ApproveConstants.STATUS_APPROVAL_PENDING)) {
			throw new BusinessException(112, "审批失败：货主状态不是待审批");
		}
		this.approveCarCargoOwner(cargoOwner, ApproveConstants.RESULT_AGREE.equalsIgnoreCase(dto.getResult()));
		approveLogService.addApproveLog(dto, user.getId(), ApproveLogConstants.APPROVE_TYPE_CARGO_OWNER);
		return ServerResponse.successWithData("审批成功");
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
	
	@Override
	public PageData<CarCargoOwnner> carCargoOwnerPage(CarCargoOwnnerPageDto dto, Integer pageNum, Integer pageSize) {
		com.github.pagehelper.Page<Object> result = PageHelper.startPage(pageNum, pageSize);
		List<CarCargoOwnner> list = carCargoOwnerMapper.selectSelective(dto);
		for (CarCargoOwnner carCargoOwnner : list) {
			carCargoOwnner = this.bindingCustomer(carCargoOwnner);
		}
		return new PageData<CarCargoOwnner>(result.getPageSize(), result.getPageNum(), result.getTotal(), list);
	}
	
	@Override
	public ServerResponse adminCarOwnerDetails(DetailsDto dto) {
		CarCargoOwnner carOwner = this.assertCarOwnerExist(dto.getId());
		CarCargoOwnerNeedApproveDto temp = JSON.parseObject(carOwner.getApproveContent(),
				CarCargoOwnerNeedApproveDto.class);
		carOwner.setApproveContentCN(temp);
		carOwner = this.bindingCustomer(carOwner);
		return ServerResponse.successWithData(carOwner);
	}

	@Override
	public ServerResponse adminCargoOwnerDetails(DetailsDto dto) {
		CarCargoOwnner cargoOwner = this.assertCargoOwnerExist(dto.getId());
		CarCargoOwnerNeedApproveDto temp = JSON.parseObject(cargoOwner.getApproveContent(),
				CarCargoOwnerNeedApproveDto.class);
		cargoOwner.setApproveContentCN(temp);
		cargoOwner = this.bindingCustomer(cargoOwner);
		return ServerResponse.successWithData(cargoOwner);
	}
	
	/**
	 * 断言车主存在
	 * @author cat
	 * 
	 * @param carOwnerId	车主Id
	 * @return
	 */
	public CarCargoOwnner assertCarOwnerExist(Integer carOwnerId) {
		CarCargoOwnner carOwner = carCargoOwnnerRepository.findByIdAndType(carOwnerId,
				CarCargoOwnerConstants.TYPE_CAR_OWNER);
		if (carOwner == null) {
			throw new BusinessException(112, "车主不存在");
		}
		return carOwner;
	}

	/**
	 * 断言货主存在
	 * @author cat
	 * 
	 * @param carOwnerId	货主Id
	 * @return
	 */
	public CarCargoOwnner assertCargoOwnerExist(Integer cargoOwnerId) {
		CarCargoOwnner cargoOwner = carCargoOwnnerRepository.findByIdAndType(cargoOwnerId,
				CarCargoOwnerConstants.TYPE_CARGO_OWNER);
		if (cargoOwner == null) {
			throw new BusinessException(112, "货主不存在");
		}
		return cargoOwner;
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
			if (carCargoOwner.getCustomerType().equalsIgnoreCase(CarCargoOwnerConstants.CUSTOMER_TYPE_PERSON)) { // 个人
				carCargoOwner.setName(temp.getLegalerName());
			}
		} else {
			carCargoOwner.setApproveStatus(ApproveConstants.STATUS_NOT_APPROVED);
		}
		carCargoOwnnerRepository.save(carCargoOwner);
	}
	
	@Transactional
	public ServerResponse cargoOwnerUpdatePersonInfo(PersonInfoUpdateDto dto, Customer customer) {
		CarCargoOwnner cargoOwner = customer.getCargoOwner();
		cargoOwner = this.assertCargoOwnerExist(cargoOwner.getId());
		if (!cargoOwner.getCustomerType().equalsIgnoreCase(CarCargoOwnerConstants.CUSTOMER_TYPE_PERSON)) {
			throw new BusinessException(112, "修改失败：无访问权限");
		}
		if (cargoOwner.getApproveStatus().contentEquals(ApproveConstants.STATUS_APPROVAL_PENDING)) {
			throw new BusinessException(112, "修改失败：待审批状态下不能修改资料");
		}
		cargoOwner.setApproveStatus(ApproveConstants.STATUS_APPROVAL_PENDING);
		cargoOwner.setApproveContent(JSON.toJSONString(dto));
		carCargoOwnnerRepository.save(cargoOwner);
		return ServerResponse.successWithData("修改成功");
	}

	@Transactional
	public ServerResponse carOwnerUpdatePersonInfo(PersonInfoUpdateDto dto, Customer customer) {
		CarCargoOwnner carOwner = customer.getCarOwner();
		carOwner = this.assertCarOwnerExist(carOwner.getId());
		if (!carOwner.getCustomerType().equalsIgnoreCase(CarCargoOwnerConstants.CUSTOMER_TYPE_PERSON)) {
			throw new BusinessException(112, "修改失败：无访问权限");
		}
		if (carOwner.getApproveStatus().contentEquals(ApproveConstants.STATUS_APPROVAL_PENDING)) {
			throw new BusinessException(112, "修改失败：待审批状态下不能修改资料");
		}
		carOwner.setApproveStatus(ApproveConstants.STATUS_APPROVAL_PENDING);
		carOwner.setApproveContent(JSON.toJSONString(dto));
		carCargoOwnnerRepository.save(carOwner);
		return ServerResponse.successWithData("修改成功");
	}

	@Transactional
	public ServerResponse cargoOwnerUpdateOrganizeInfo(OrganizeInfoUpdateDto dto, Customer customer) {
		CarCargoOwnner cargoOwner = customer.getCargoOwner();
		cargoOwner = this.assertCargoOwnerExist(cargoOwner.getId());
		if (!cargoOwner.getCustomerType().equalsIgnoreCase(CarCargoOwnerConstants.CUSTOMER_TYPE_ORGANIZE)) {
			throw new BusinessException(112, "修改失败：无访问权限");
		}
		if (cargoOwner.getApproveStatus().contentEquals(ApproveConstants.STATUS_APPROVAL_PENDING)) {
			throw new BusinessException(112, "修改失败：待审批状态下不能修改资料");
		}
		cargoOwner.setApproveStatus(ApproveConstants.STATUS_APPROVAL_PENDING);
		cargoOwner.setApproveContent(JSON.toJSONString(dto));
		carCargoOwnnerRepository.save(cargoOwner);
		return ServerResponse.successWithData("修改成功");
	}

	@Transactional
	public ServerResponse carOwnerUpdateOrganizeInfo(OrganizeInfoUpdateDto dto, Customer customer) {
		CarCargoOwnner carOwner = customer.getCarOwner();
		carOwner = this.assertCarOwnerExist(carOwner.getId());
		if (!carOwner.getCustomerType().equalsIgnoreCase(CarCargoOwnerConstants.CUSTOMER_TYPE_ORGANIZE)) {
			throw new BusinessException(112, "修改失败：无访问权限");
		}
		if (carOwner.getApproveStatus().contentEquals(ApproveConstants.STATUS_APPROVAL_PENDING)) {
			throw new BusinessException(112, "修改失败：待审批状态下不能修改资料");
		}
		carOwner.setApproveStatus(ApproveConstants.STATUS_APPROVAL_PENDING);
		carOwner.setApproveContent(JSON.toJSONString(dto));
		carCargoOwnnerRepository.save(carOwner);
		return ServerResponse.successWithData("修改成功");
	}
	
	@Transactional
	@Override
	public ServerResponse updateCarOwner(CarCargoOwnerUpdateDto dto, Customer customer) {
		CarCargoOwnner carCargoOwner = new CarCargoOwnner();
		BeanUtils.copyProperties(dto, carCargoOwner);
		carCargoOwner.setId(customer.getCarOwner().getId());
		carCargoOwnerMapper.updateByPrimaryKeySelective(carCargoOwner);
		return ServerResponse.successWithData("修改成功");
	}
	
	@Transactional
	@Override
	public ServerResponse updateCargoOwner(CarCargoOwnerUpdateDto dto, Customer customer) {
		CarCargoOwnner carCargoOwner = new CarCargoOwnner();
		BeanUtils.copyProperties(dto, carCargoOwner);
		carCargoOwner.setId(customer.getCargoOwner().getId());
		carCargoOwnerMapper.updateByPrimaryKeySelective(carCargoOwner);
		return ServerResponse.successWithData("修改成功");
	}
	
	@Override
	public ServerResponse carOwnerDetails(Customer customer) {
		CarCargoOwnner carOwner = this.assertCarOwnerExist(customer.getCarOwner().getId());
		CarCargoOwnerNeedApproveDto temp = JSON.parseObject(carOwner.getApproveContent(),
				CarCargoOwnerNeedApproveDto.class);
		carOwner.setApproveContentCN(temp);
		carOwner = this.bindingCustomer(carOwner);
		return ServerResponse.successWithData(carOwner);
	}

	@Override
	public ServerResponse cargoOwnerDetails(Customer customer) {
		CarCargoOwnner cargoOwner = this.assertCargoOwnerExist(customer.getCargoOwner().getId());
		CarCargoOwnerNeedApproveDto temp = JSON.parseObject(cargoOwner.getApproveContent(),
				CarCargoOwnerNeedApproveDto.class);
		cargoOwner.setApproveContentCN(temp);
		cargoOwner = this.bindingCustomer(cargoOwner);
		return ServerResponse.successWithData(cargoOwner);
	}

	@Transactional
	@Override
	public ServerResponse adminEnableCarOwner(CommonDto dto, User user) {
		CarCargoOwnner carOwner = this.assertCarOwnerExist(dto.getId());
		customerRepository.updateIsActiveById(carOwner.getCustomerId(), true);
		// 启用子账号 TODO
		return ServerResponse.success();
	}

	@Transactional
	@Override
	public ServerResponse adminDisableCarOwner(CommonDto dto, User user) {
		CarCargoOwnner carOwner = this.assertCarOwnerExist(dto.getId());
		customerRepository.updateIsActiveById(carOwner.getCustomerId(), false);
		// 退出主账号、子账号登录态，禁用子账号 TODO
		return ServerResponse.success();
	}

	@Transactional
	@Override
	public ServerResponse adminEnableCargoOwner(CommonDto dto, User user) {
		CarCargoOwnner cargoOwner = this.assertCargoOwnerExist(dto.getId());
		customerRepository.updateIsActiveById(cargoOwner.getCustomerId(), true);
		// 启用子账号 TODO
		return ServerResponse.success();
	}

	@Transactional
	@Override
	public ServerResponse adminDisableCargoOwner(CommonDto dto, User user) {
		CarCargoOwnner cargoOwner = this.assertCargoOwnerExist(dto.getId());
		customerRepository.updateIsActiveById(cargoOwner.getCustomerId(), false);
		// 退出主账号、子账号登录态，禁用子账号 TODO
		return ServerResponse.success();
	}
	
}
