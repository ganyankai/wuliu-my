package com.zrytech.framework.app.service;

import org.springframework.stereotype.Service;

import com.zrytech.framework.app.dto.CommonDto;
import com.zrytech.framework.app.dto.approve.ApproveDto;
import com.zrytech.framework.app.dto.car.CarAddDto;
import com.zrytech.framework.app.dto.car.CarCheckUpdateDto;
import com.zrytech.framework.app.dto.car.CarNoCheckUpdateDto;
import com.zrytech.framework.app.dto.car.CarPageDto;
import com.zrytech.framework.app.entity.Car;
import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.base.entity.PageData;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.common.entity.User;

@Service
public interface CarService {
	
	/**
	 * 断言车辆已认证
	 * @author cat
	 * 
	 * @param car
	 */
	public void assertCarCertified(Car car);
	
	/**
	 * 车辆分页
	 * @author cat
	 * 
	 * @param dto
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageData<Car> carPage(CarPageDto dto, Integer pageNum, Integer pageSize);
	
	
	/**
	 * 管理员 - 车辆详情
	 * @author cat
	 * 
	 * @param dto
	 * @return
	 */
	public ServerResponse adminDetails(CommonDto dto);
	
	
	/**
	 * 管理员 - 车辆审批
	 * @author cat
	 * 
	 * @param dto
	 * @param user
	 * @return
	 */
	public ServerResponse adminApprove(ApproveDto dto, User user);
	
	
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
	 * 车主或者车主子账号 - 删除车辆
	 * @author cat
	 * 
	 * @param dto	待删除的车辆Id
	 * @param customer	当前登录人
	 * @return
	 */
	public ServerResponse delete(CommonDto dto, Customer customer);
	
	
	
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
	public ServerResponse details(CommonDto dto, Customer customer);
	
	
}
