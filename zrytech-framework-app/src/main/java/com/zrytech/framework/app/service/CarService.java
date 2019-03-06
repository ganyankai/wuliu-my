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

	public ServerResponse page(CarPageDto carPageDto, Integer pageNum, Integer pageSize);
	
	public ServerResponse details(Integer id);
	
	public ServerResponse check(CheckDto dto, User user);
	
	public ServerResponse delete(DeleteDto dto, Customer customer);
	
	public ServerResponse submitAudit(CommonDto dto, Customer customer);
	
	public ServerResponse updateNoCheck(CarNoCheckUpdateDto dto, Customer customer);
	
	public ServerResponse updateCheck(CarCheckUpdateDto dto, Customer customer);
	
	public ServerResponse add(CarAddDto dto, Customer customer);
	
	public ServerResponse details(DetailsDto dto, Customer customer);
	
	public ServerResponse page(CarOwnerCarPageDto dto, Integer pageNum, Integer pageSize, Customer customer);
}
