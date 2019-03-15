package com.zrytech.framework.app.service;

import org.springframework.stereotype.Service;

import com.zrytech.framework.app.dto.CheckDto;
import com.zrytech.framework.app.dto.CommonDto;
import com.zrytech.framework.app.dto.DeleteDto;
import com.zrytech.framework.app.dto.DetailsDto;
import com.zrytech.framework.app.dto.car.CarAddDto;
import com.zrytech.framework.app.dto.car.CarCheckUpdateDto;
import com.zrytech.framework.app.dto.car.CarNoCheckUpdateDto;
import com.zrytech.framework.app.dto.car.CarOwnerCarPageDto;
import com.zrytech.framework.app.dto.car.CarPageDto;
import com.zrytech.framework.app.entity.Car;
import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.common.entity.User;

@Service
public interface CarService {
	
	/**
	 * 断言车辆存在，且属于当前登录车主
	 * @author cat
	 * 
	 * @param carId	车辆Id
	 * @param carOwnerId	车主Id
	 * @return 车辆
	 */
	public Car assertCarBelongToCurrentUser(Integer carId, Integer carOwnerId);
	
	
	/**
	 * 断言车辆可用（数据库存在数据且未删除）
	 * @author cat
	 * 
	 * @param carId	车辆Id
	 * @return	车辆
	 */
	public Car assertCarAvailable(Integer carId);

	
	/**
	 * 后台管理员 - 车辆分页
	 * @author cat
	 * 
	 * @param dto	查询条件，详见{@link CarPageDto}
	 * @param pageNum
	 * @param pageSize
	 * @return 包含车主企业名称、司机名称、压货人名称的车辆列表
	 */
	public ServerResponse page(CarPageDto dto, Integer pageNum, Integer pageSize);
	
	
	/**
	 * 后台管理员 - 车辆详情
	 * @author cat
	 * 
	 * @param dto
	 * @return 包含车主，司机，压货人信息的车辆
	 */
	public ServerResponse details(DetailsDto dto);
	
	
	/**
	 * 后台管理员 - 车辆审核
	 * @author cat
	 * 
	 * @param checkDto 审核结果
	 * @param user 管理员
	 * @return
	 */
	public ServerResponse check(CheckDto dto, User user);
	
	
	/**
	 * 车主或者车主子账号 - 删除车辆
	 * @author cat
	 * 
	 * @param dto	待删除的车辆Id
	 * @param customer	当前登录人
	 * @return
	 */
	public ServerResponse delete(DeleteDto dto, Customer customer);
	
	
	/**
	 * 车主或者车主子账号 - 提交审核
	 * <p>仅下架状态的车辆可以提交审核</p>
	 * @author cat
	 * 
	 * @param dto	待提交审核的车辆Id
	 * @param customer	当前登录人
	 * @return
	 */
	public ServerResponse submitAudit(CommonDto dto, Customer customer);
	
	
	/**
	 * 车主或者车主子账号 - 修改车辆不需要审核的内容
	 * @author cat
	 * 
	 * @param dto	车辆不需要审核的内容
	 * @param customer	当前登录人
	 * @return
	 */
	public ServerResponse updateNoCheck(CarNoCheckUpdateDto dto, Customer customer);
	
	
	/**
	 * 车主或者车主子账号 - 修改车辆需要审核的内容
	 * @author cat
	 * 
	 * @param dto	车辆需要审核的内容
	 * @param customer	当前登录人
	 * @return
	 */
	public ServerResponse updateCheck(CarCheckUpdateDto dto, Customer customer);
	
	
	/**
	 * 车主或者车主子账号 - 添加车辆
	 * @author cat
	 * 
	 * @param dto	要添加的车辆信息
	 * @param customer	当前登录人
	 */
	public ServerResponse add(CarAddDto dto, Customer customer);
	
	
	/**
	 * 车主或者车主子账号 - 车辆详情
	 * @author cat
	 * 
	 * @param dto	车辆Id
	 * @param customer	当前登录人
	 * @return
	 */
	public ServerResponse details(DetailsDto dto, Customer customer);
	
	
	/**
	 * 车主或者车主子账号 - 车辆分页
	 * @author cat
	 * 
	 * @param dto	搜索条件
	 * @param pageNum
	 * @param pageSize
	 * @param customer	当前登录人
	 * @return
	 */
	public ServerResponse page(CarOwnerCarPageDto dto, Integer pageNum, Integer pageSize, Customer customer);
}
