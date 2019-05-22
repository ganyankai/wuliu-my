package com.zrytech.framework.app.service;

import org.springframework.stereotype.Service;

import com.zrytech.framework.app.dto.CarCargoOwnnerPageDto;
import com.zrytech.framework.app.dto.CommonDto;
import com.zrytech.framework.app.dto.DetailsDto;
import com.zrytech.framework.app.dto.approve.ApproveDto;
import com.zrytech.framework.app.dto.carcargoowner.CarCargoOwnerUpdateAvoidAuditDto;
import com.zrytech.framework.app.dto.carcargoowner.CarCargoOwnerUpdateDto;
import com.zrytech.framework.app.dto.carcargoowner.OrganizeInfoUpdateDto;
import com.zrytech.framework.app.dto.carcargoowner.PersonInfoUpdateDto;
import com.zrytech.framework.app.dto.customer.CustomerRegisterDto;
import com.zrytech.framework.app.entity.CarCargoOwnner;
import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.base.entity.PageData;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.common.entity.User;

@Service
public interface CarCargoOwnerService {

	/**
	 * 车主货主分页
	 * @author cat
	 * 
	 * @param dto
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageData<CarCargoOwnner> carCargoOwnerPage(CarCargoOwnnerPageDto dto, Integer pageNum, Integer pageSize);
	
	/**
	 * 车主货主注册
	 * @author cat
	 * 
	 * @param dto
	 * @return
	 */
	public ServerResponse register(CustomerRegisterDto dto);
	
	/**
	 * 管理员 - 车主详情
	 * @author cat
	 * 
	 * @param dto	车主Id
	 * @return
	 */
	public ServerResponse adminCarOwnerDetails(DetailsDto dto);
	
	/**
	 * 管理员 - 修改车主免审核状态
	 * @author cat
	 * 
	 * @param dto
	 * @param customer
	 * @return
	 */
	public ServerResponse carOwnerUpdateAvoidAudit(CarCargoOwnerUpdateAvoidAuditDto dto);
	
	/**
	 * 管理员 - 车主审批
	 * @author cat
	 * 
	 * @param checkDto	审批结果
	 * @param user	管理员
	 * @return
	 */
	public ServerResponse adminApproveCarOwner(ApproveDto checkDto, User user);
	
	/**
	 * 管理员 - 货主详情
	 * @author cat
	 * 
	 * @param dto	货主Id
	 * @return
	 */
	public ServerResponse adminCargoOwnerDetails(DetailsDto dto);
	
	/**
	 * 管理员 - 启用车主
	 * @author cat
	 * 
	 * @param dto	车主Id
	 * @param user
	 * @return
	 */
	public ServerResponse adminEnableCarOwner(CommonDto dto, User user);
	
	/**
	 * 管理员 - 禁用车主
	 * @author cat
	 * 
	 * @param dto	车主Id
	 * @param user
	 * @return
	 */
	public ServerResponse adminDisableCarOwner(CommonDto dto, User user);
	
	/**
	 * 管理员 - 启用货主
	 * @author cat
	 * 
	 * @param dto	货主Id
	 * @param user
	 * @return
	 */
	public ServerResponse adminEnableCargoOwner(CommonDto dto, User user);
	
	/**
	 * 管理员 - 禁用货主
	 * @author cat
	 * 
	 * @param dto	货主Id
	 * @param user
	 * @return
	 */
	public ServerResponse adminDisableCargoOwner(CommonDto dto, User user);
	
	/**
	 * 管理员 - 修改货主免审核状态
	 * @author cat
	 * 
	 * @param dto
	 * @param customer
	 * @return
	 */
	public ServerResponse cargoOwnerUpdateAvoidAudit(CarCargoOwnerUpdateAvoidAuditDto dto);
	
	/**
	 * 管理员 - 货主审批
	 * @author cat
	 * 
	 * @param checkDto	审批结果
	 * @param user	管理员
	 * @return
	 */
	public ServerResponse adminApproveCargoOwner(ApproveDto dto, User user);
	
	/**
	 * 个人货主修改认证信息
	 * @author cat
	 * 
	 * @param dto
	 * @param customer
	 * @return
	 */
	public ServerResponse cargoOwnerUpdatePersonInfo(PersonInfoUpdateDto dto, Customer customer);
	
	/**
	 * 个人车主修改认证信息
	 * @author cat
	 * 
	 * @param dto
	 * @param customer
	 * @return
	 */
	public ServerResponse carOwnerUpdatePersonInfo(PersonInfoUpdateDto dto, Customer customer);
	
	/**
	 * 企业货主修改认证信息
	 * @author cat
	 * 
	 * @param dto
	 * @param customer
	 * @return
	 */
	public ServerResponse cargoOwnerUpdateOrganizeInfo(OrganizeInfoUpdateDto dto, Customer customer);
	
	/**
	 * 企业车主修改认证信息
	 * @author cat
	 * 
	 * @param dto
	 * @param customer
	 * @return
	 */
	public ServerResponse carOwnerUpdateOrganizeInfo(OrganizeInfoUpdateDto dto, Customer customer);
	
	/**
	 * 修改车主不需要审批的字段
	 * @author cat
	 * 
	 * @param dto
	 * @param customer
	 * @return
	 */
	public ServerResponse updateCarOwner(CarCargoOwnerUpdateDto dto, Customer customer);
	
	/**
	 * 修改货主不需要审批的字段
	 * @author cat
	 * 
	 * @param dto
	 * @param customer
	 * @return
	 */
	public ServerResponse updateCargoOwner(CarCargoOwnerUpdateDto dto, Customer customer);
	
	
	/**
	 * 车主详情
	 * @author cat
	 * 
	 * @param customer
	 * @return
	 */
	public ServerResponse carOwnerDetails(Customer customer);
	
	/**
	 * 货主详情
	 * @author cat
	 * 
	 * @param customer
	 * @return
	 */
	public ServerResponse cargoOwnerDetails(Customer customer);
	
	/**
	 * 验证手机号是否已注册
	 * 
	 * @param tel
	 * @return
	 */
	public ServerResponse checkTel(String tel);
	
	
	/**
	 * 验证用户名是否已注册
	 * 
	 * @param userAccount
	 * @return
	 */
	public ServerResponse checkUserAccount(String userAccount);
	
}
