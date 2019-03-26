package com.zrytech.framework.app.service;

import org.springframework.stereotype.Service;

import com.zrytech.framework.app.dto.CheckDto;
import com.zrytech.framework.app.dto.CommonDto;
import com.zrytech.framework.app.dto.DeleteDto;
import com.zrytech.framework.app.dto.DetailsDto;
import com.zrytech.framework.app.dto.approve.ApproveDto;
import com.zrytech.framework.app.dto.carperson.AdminDriverPageDto;
import com.zrytech.framework.app.dto.carperson.AdminSupercargoPageDto;
import com.zrytech.framework.app.dto.carperson.CarOwnerCarPersonPageDto;
import com.zrytech.framework.app.dto.carperson.CarPersonAddDto;
import com.zrytech.framework.app.dto.carperson.CarPersonCheckUpdateDto;
import com.zrytech.framework.app.dto.carperson.CarPersonEnabledDto;
import com.zrytech.framework.app.dto.carperson.CarPersonNoCheckUpdateDto;
import com.zrytech.framework.app.dto.carperson.CarPersonPageDto;
import com.zrytech.framework.app.entity.CarPerson;
import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.common.entity.User;


@Service
public interface CarPersonService {
	
	/**
	 * 断言司机数据存在且未被删除
	 * @author cat
	 * 
	 * @param driverId	司机Id
	 * @return
	 */
	public CarPerson assertDriverAvailable(Integer driverId);
	
	
	/**
	 * 断言压货人数据存在且未被删除
	 * @author cat
	 * 
	 * @param supercargoId	压货人Id
	 * @return
	 */
	public CarPerson assertSupercargoAvailable(Integer supercargoId);
	
	
	/**
	 * 断言司机压货人可用
	 * <p>数据库存在数据且未删除</p>
	 * @author cat
	 * 
	 * @param carPersonId	司机压货人Id
	 * @return
	 */
	public CarPerson assertCarPersonAvailable(Integer carPersonId);
	
	
	/**
	 * 管理员 - 司机与压货人分页
	 * @author cat
	 * 
	 * @param dto
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public ServerResponse page(CarPersonPageDto dto, Integer pageNumber, Integer pageSize);
	
	
	/**
	 * 管理员 - 司机或押货人详情
	 * @author cat
	 * 
	 * @param id	司机或者押货人Id
	 * @return
	 */
	public ServerResponse details(DetailsDto dto);
	
	
	/**
	 * 司机或押货人审核
	 * @author cat
	 * 
	 * @param checkDto
	 * @param user	管理员
	 * @return
	 */
	public ServerResponse check(CheckDto checkDto, User user);
	
	@Deprecated
	public ServerResponse page(CarOwnerCarPersonPageDto dto, Integer pageNum, Integer pageSize, Customer customer);
	
	
	/**
	 * 车主及车主子账号 - 司机或压货人详情
	 * @author cat
	 * 
	 * @param id	司机或压货人Id
	 * @param customer	当前登录人
	 * @return
	 */
	public ServerResponse details(DetailsDto dto, Customer customer);
	
	
	/**
	 * 车主及车主子账号 - 添加司机或压货人
	 * <p>添加司机与压货人时需要同时创建登录账号（目前压货人不创建账号）</p>
	 * @author cat
	 * 
	 * @param dto	司机压货人的个人信息和账号信息
	 * @param customer	当前登录人
	 * @return
	 */
	@Deprecated
	public ServerResponse add(CarPersonAddDto dto, Customer customer);
	
	
	/**
	 * 车主及车主子账号 - 删除司机或压货人
	 * @author cat
	 * 
	 * @param dto	待删除的司机或压货人Id
	 * @param customer	当前登录人
	 * @return
	 */
	public ServerResponse delete(DeleteDto dto, Customer customer);
	
	
	/**
	 * 车主及车主子账号 - 司机的禁用与启用
	 * @author cat
	 * 
	 * @param dto
	 * @param customer
	 * @return
	 */
	public ServerResponse enabled(CarPersonEnabledDto dto, Customer customer);
	
	
	/**
	 * 车主及车主子账号 - 修改司机或压货人不需要审核的字段
	 * @author cat
	 * 
	 * @param dto	待修改的不需要审核的字段
	 * @param customer	当前登录人
	 * @return
	 */
	public ServerResponse updateNoCheck(CarPersonNoCheckUpdateDto dto, Customer customer);
	
	@Deprecated
	public ServerResponse updateCheck(CarPersonCheckUpdateDto dto, Customer customer);
	
	@Deprecated
	public ServerResponse submitAudit(CommonDto dto, Customer customer);
	
	
	/**
	 * 断言司机存在，且属于当前登录的车主
	 * @author cat
	 * 
	 * @param driverId	司机Id
	 * @param carOwnerId	车主Id
	 * @return	司机
	 */
	public CarPerson assertDriverBelongToCurrentUser(Integer driverId, Integer carOwnerId);
	
	
	/**
	 * 断言压货人存在，且属于当前登录的车主
	 * @author cat
	 * 
	 * @param supercargoId	压货人Id
	 * @param carOwnerId	车主Id
	 * @return	压货人
	 */
	public CarPerson assertSupercargoBelongToCurrentUser(Integer supercargoId, Integer carOwnerId);
	
	
	
	/**
	 * 车主及车主子账号 - 修改司机压货人需要审批的字段（待审批状态的司机压货人不可修改）
	 * @author cat
	 * 
	 * @param dto	待修改需要审批的字段
	 * @param customer	当前登录人（车主账号）
	 * @return
	 */
	public ServerResponse updateNeedApprove(CarPersonCheckUpdateDto dto, Customer customer);
	
	
	/**
	 * 车主及车主子账号 - 提交审批（仅已取消状态的司机或压货人可以提交审批）
	 * @author cat
	 * 
	 * @param dto	待提交审批的司机或压货人id
	 * @param customer	当前登录人（车主账号）
	 * @return
	 */
	public ServerResponse submitApprove(CommonDto dto, Customer customer);
	
	
	/**
	 * 车主及车主子账号 - 取消审批（ 仅待审批状态的司机或压货人可以取消审批）
	 * @author cat
	 * 
	 * @param dto	待取消审批的司机或压货人id
	 * @param customer	当前登录人（车主账号）
	 * @return
	 */
	public ServerResponse cancelApprove(CommonDto dto, Customer customer);
	
	
	/**
	 * 车主及车主子账号 - 新增司机或压货人
	 * @author cat
	 * 
	 * @param dto	司机或压货人基本信息，司机的账号信息
	 * @param customer	当前登录人（车主账号）
	 * @return
	 */
	public ServerResponse create(CarPersonAddDto dto, Customer customer);
	
	
	/**
	 * 管理员 - 审批司机或压货人
	 * @author cat
	 * 
	 * @param checkDto	审批内容
	 * @param user	管理员
	 * @return
	 */
	public ServerResponse approve(CheckDto checkDto, User user);
	
	
	
	/**
	 * 车主及车主子账号 - 我的压货人
	 * @author cat
	 * 
	 * @param dto
	 * @param pageNum
	 * @param pageSize
	 * @param customer	当前登录人（车主账号）
	 * @return
	 */
	public ServerResponse mySupercargo(CarPersonPageDto dto, Integer pageNum, Integer pageSize, Customer customer);
	
	
	/**
	 * 车主及车主子账号 - 我的司机
	 * @author cat
	 * 
	 * @param dto
	 * @param pageNum
	 * @param pageSize
	 * @param customer	当前登录人（车主账号）
	 * @return
	 */
	public ServerResponse myDriver(CarPersonPageDto dto, Integer pageNum, Integer pageSize, Customer customer);
	
	
	
	
	/**
	 * 管理员 - 司机分页
	 * @author cat
	 * 
	 * @param dto	搜索条件
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public ServerResponse adminDriverPage(AdminDriverPageDto dto, Integer pageNum, Integer pageSize);
	
	
	/**
	 * 管理员 - 压货人分页
	 * @author cat
	 * 
	 * @param dto	搜索条件
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public ServerResponse adminSupercargoPage(AdminSupercargoPageDto dto, Integer pageNum, Integer pageSize);
	
	
	/**
	 * 管理员 - 某一个车主的司机分页
	 * @author cat
	 * 
	 * @param dto	搜索条件
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public ServerResponse adminOneCarOwnerDriverPage(AdminDriverPageDto dto, Integer pageNum, Integer pageSize);
	
	
	/**
	 * 管理员 - 某一个车主的压货人分页
	 * @author cat
	 * 
	 * @param dto	搜索条件
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public ServerResponse adminOneCarOwnerSupercargoPage(AdminSupercargoPageDto dto, Integer pageNum, Integer pageSize);
	
	
	/**
	 * 管理员 - 待审批的司机分页
	 * @author cat
	 * 
	 * @param dto
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public ServerResponse adminApprovePendingDriverPage(AdminDriverPageDto dto, Integer pageNum, Integer pageSize);
	
	
	/**
	 * 管理员 - 待审批的压货人分页
	 * @author cat
	 * 
	 * @param dto
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public ServerResponse adminApprovePendingSupercargoPage(AdminSupercargoPageDto dto, Integer pageNum, Integer pageSize);
	
	
	/**
	 * 管理员 - 司机详情
	 * @author cat
	 * 
	 * @param dto	司机Id
	 * @return
	 */
	public ServerResponse adminDriverDetails(DetailsDto dto);
	
	
	/**
	 * 管理员 - 压货人详情
	 * @author cat
	 * 
	 * @param dto	压货人Id
	 * @return
	 */
	public ServerResponse adminSupercargoDetails(DetailsDto dto);
	
	
	/**
	 * 管理员 - 司机审批
	 * @author cat
	 * 
	 * @param dto	审批结果
	 * @param user	管理员
	 * @return
	 */
	public ServerResponse adminDriverApprove(ApproveDto dto, User user);
	
	
	/**
	 * 管理员 - 压货人审批
	 * @author cat
	 * 
	 * @param dto	审批结果
	 * @param user	管理员
	 * @return
	 */
	public ServerResponse adminSupercargoApprove(ApproveDto dto, User user);
	
	
	
	
	
	
	
	
	
	
	
	
	
}