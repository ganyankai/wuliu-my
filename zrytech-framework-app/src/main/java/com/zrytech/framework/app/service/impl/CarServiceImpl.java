package com.zrytech.framework.app.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zrytech.framework.app.repository.ApproveLogRepository;
import com.zrytech.framework.app.repository.CarCargoOwnnerRepository;
import com.zrytech.framework.app.repository.CarPersonRepository;
import com.zrytech.framework.app.repository.CarRepository;
import com.github.pagehelper.PageHelper;
import com.zrytech.framework.app.constants.ApproveLogConstants;
import com.zrytech.framework.app.constants.CarConstants;
import com.zrytech.framework.app.dto.CheckDto;
import com.zrytech.framework.app.dto.CommonDto;
import com.zrytech.framework.app.dto.DeleteDto;
import com.zrytech.framework.app.dto.DetailsDto;
import com.zrytech.framework.app.dto.car.CarAddDto;
import com.zrytech.framework.app.dto.car.CarCheckUpdateDto;
import com.zrytech.framework.app.dto.car.CarNoCheckUpdateDto;
import com.zrytech.framework.app.dto.car.CarOwnerCarPageDto;
import com.zrytech.framework.app.dto.car.CarPageDto;
import com.zrytech.framework.app.entity.ApproveLog;
import com.zrytech.framework.app.entity.Car;
import com.zrytech.framework.app.entity.CarCargoOwnner;
import com.zrytech.framework.app.entity.CarPerson;
import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.app.mapper.CarMapper;
import com.zrytech.framework.app.service.CarService;
import com.zrytech.framework.base.entity.PageData;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.common.entity.User;

/**
 * 车辆
 * 
 * @author cat
 *
 */
@Service
@Transactional
public class CarServiceImpl implements CarService {
	
	@Autowired private CarRepository carRepository;
	
	@Autowired private CarCargoOwnnerRepository carCargoOwnnerRepository;
	
	@Autowired private CarPersonRepository carPersonRepository;
	
	@Autowired private ApproveLogRepository approveLogRepository;
	
	@Autowired private CarMapper carMapper;
	
	
	/**
	 * 车辆分页
	 * @author cat
	 * 
	 * @param dto	查询条件，详见{@link CarPageDto}
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Override
	public ServerResponse page(CarPageDto dto, Integer pageNum, Integer pageSize){
		com.github.pagehelper.Page<Object> result = PageHelper.startPage(pageNum, pageSize);
		
		List<Car> list = carMapper.selectSelective(dto);
		for (Car temp : list) {
			temp.setCarOwnerName(carCargoOwnnerRepository.findNameById(temp.getCreateBy()));
			temp.setDriverName(carPersonRepository.findNameById(temp.getDriverId()));
			temp.setSupercargoName(carPersonRepository.findNameById(temp.getSupercargoId()));
		}
		
		PageData<Car> pageData = new PageData<Car>(result.getPageSize(), result.getPageNum(), result.getTotal(), list);
		return ServerResponse.successWithData(pageData);
	}
	
	
	/**
	 * 车辆详情（包含车主、司机、压货人）
	 * @author cat
	 * 
	 * @param id	车辆Id
	 * @return
	 */
	@Override
	public ServerResponse details(Integer id) {
		Car car = carRepository.findOne(id);
		car = bindingCarOwner(car);		// 车主
		car = bindingDriver(car);		// 司机
		car = bindingSupercargo(car);	// 压货人
		return ServerResponse.successWithData(car);
	}
	

	/**
	 * 为车辆绑定车主信息
	 * @author cat
	 * 
	 * @param car
	 * @return
	 */
	public Car bindingCarOwner(Car car) {
		if(car.getCreateBy() != null) {
			CarCargoOwnner carOwner = carCargoOwnnerRepository.findOne(car.getCreateBy());
			car.setCarOwnerName(carOwner.getName());
			car.setCarOwner(carOwner);
		}
		return car;
	}
	
	
	/**
	 * 为车辆绑定车司机信息
	 * @author cat
	 * 
	 * @param car
	 * @return
	 */
	public Car bindingDriver(Car car) {
		if(car.getDriverId() != null) {
			CarPerson driver = carPersonRepository.findOne(car.getDriverId());
			car.setDriver(driver);
			car.setDriverName(driver.getName());
		}
		return car;
	}
	
	
	/**
	 * 为车辆绑定车压货人信息
	 * @author cat
	 * 
	 * @param car
	 * @return
	 */
	public Car bindingSupercargo(Car car) {
		if(car.getSupercargoId() != null) {
			CarPerson supercargo = carPersonRepository.findOne(car.getSupercargoId());
			car.setSupercargo(supercargo);
			car.setSupercargoName(supercargo.getName());
		}
		return car;
	}
	
	
	/**
	 * 车辆审核
	 * @author cat
	 * 
	 * @param checkDto 审核结果
	 * @return
	 */
	@Override
	public ServerResponse check(CheckDto checkDto, User user) {
		Integer businessId = checkDto.getBusinessId();
		
		Car car = this.assertCarExist(businessId);
		
		if(!CarConstants.CAR_STATUS_WAIT_CHECK.equalsIgnoreCase(car.getStatus())) {
			throw new BusinessException(112, "审核失败：车辆状态不是待审核");
		}
		if(car.getIsDelete()) {
			throw new BusinessException(112, "审核失败：车辆已被删除");
		}
		
		// 修改车辆状态
		String result = checkDto.getResult();
		if(ApproveLogConstants.APPROVE_RESULT_PASS.equals(result)) {
			car.setStatus(CarConstants.CAR_STATUS_UP);
		}else {
			car.setStatus(CarConstants.CAR_STATUS_DOWN);
		}
		carRepository.save(car);
		
		// 添加审核记录
		ApproveLog entity = new ApproveLog();
		entity.setApproveBy(user.getId());
		entity.setApproveContent(checkDto.getContent());
		entity.setApproveResult(checkDto.getResult());
		entity.setApproveTime(new Date());
		entity.setApproveType(ApproveLogConstants.APPROVE_TYPE_CAR);
		entity.setBusinessId(businessId);
		approveLogRepository.save(entity);
		
		return ServerResponse.successWithData("审核成功");
	}
	
	
	/**
	 * 断言车辆存在
	 * @author cat
	 * 
	 * @param id 车辆Id
	 * @return
	 */
	public Car assertCarExist(Integer id) {
		Car car = carRepository.findOne(id);
		if(car == null) {
			throw new BusinessException(112, "车辆不存在");
		}
		return car;
	}
	
	
	/**
	 * 断言车辆未被删除
	 * @author cat
	 * 
	 * @param car 车辆
	 * @return
	 */
	public void assertCarNotDelete(Car car) {
		if(car.getIsDelete()){
			throw new BusinessException(112, "车辆不存在");
		}
	}
	
	
	/*以下为车主及车主子账号接口*/
	//////////////////////////////////////////////////////////////////////////////////////
	
	
	// TODO 搜索条件，返回结果有待完善
	/**
	 * 车辆分页（车主或者车主子账号）
	 * @author cat
	 * 
	 * @param dto	搜索条件
	 * @param pageNum
	 * @param pageSize
	 * @param customer	当前登录人
	 * @return
	 */
	@Override
	public ServerResponse page(CarOwnerCarPageDto dto, Integer pageNum, Integer pageSize, Customer customer) {
		com.github.pagehelper.Page<Object> result = PageHelper.startPage(pageNum, pageSize);
		
		List<Car> list = carMapper.carOwnerCarPage(dto, 1);	// TODO carOwnerId取值
		for (Car temp : list) {
			if(temp.getDriverId() != null) {
				temp.setDriverName(carPersonRepository.findNameById(temp.getDriverId()));
			}
			if(temp.getSupercargoId() != null) {
				temp.setSupercargoName(carPersonRepository.findNameById(temp.getSupercargoId()));
			}
		}
		
		PageData<Car> pageData = new PageData<Car>(result.getPageSize(), result.getPageNum(), result.getTotal(), list);
		return ServerResponse.successWithData(pageData);
	}
	
	
	// TODO 返回结果有待完善
	/**
	 * 车辆详情（车主或者车主子账号）
	 * @author cat
	 * 
	 * @param dto	车辆Id
	 * @param customer	当前登录人
	 * @return
	 */
	@Override
	public ServerResponse details(DetailsDto dto, Customer customer) {
		Car car = this.assertCarExist(dto.getId());
		this.assertCarNotDelete(car);
		// TODO 判断当前用户是否可以查看该车辆信息
		car = bindingCarOwner(car); // 车主
		car = bindingDriver(car); // 司机
		car = bindingSupercargo(car); // 压货人
		return ServerResponse.successWithData(car);
	}
	
	
	/**
	 * 添加车辆（车主或者车主子账号）
	 * @author cat
	 * 
	 * @param dto	要添加的车辆信息
	 * @param customer	当前登录人
	 */
	@Override
	public ServerResponse add(CarAddDto dto, Customer customer) {
		// TODO 判断当前用户是否有权限添加车辆
		
		Car car = new Car();
		BeanUtils.copyProperties(dto, car);
		// car.setCreateBy(customer.getId()); TODO 登录信息待处理
		// car.setCarOwnerId(carOwnerId); TODO 车主Id
		car.setStatus(CarConstants.CAR_STATUS_DOWN); // 新添加的车辆默认下架状态
		car.setId(null);
		car.setCreateDate(new Date());
		car.setIsDelete(false);
		carRepository.save(car);
		
		return ServerResponse.successWithData("添加成功");
	}

	
	/**
	 * 修改车辆不需要审核的内容（车主或者车主子账号）
	 * @author cat
	 * 
	 * @param dto	车辆不需要审核的内容
	 * @param customer	当前登录人
	 * @return
	 */
	@Override
	public ServerResponse updateNoCheck(CarNoCheckUpdateDto dto, Customer customer) {
		Car car = this.assertCarExist(dto.getId());
		this.assertCarNotDelete(car);
		// TODO 验证当前用户是否有权限修改数据
		
		// Integer driverId = dto.getDriverId();
		// Integer supercargoId = dto.getSupercargoId();
		// TODO 司机压货人检验
		
		BeanUtils.copyProperties(dto, car);
		carRepository.save(car);
		return ServerResponse.successWithData("修改成功");
	}
	
	
	/**
	 * 修改车辆需要审核的内容（车主或者车主子账号）
	 * <p>更新需审核内容时，状态改为下架</p>
	 * @author cat
	 * 
	 * @param dto	车辆需要审核的内容
	 * @param customer	当前登录人
	 * @return
	 */
	@Override
	public ServerResponse updateCheck(CarCheckUpdateDto dto, Customer customer) {
		Car car = this.assertCarExist(dto.getId());
		this.assertCarNotDelete(car);
		// TODO 验证当前用户是否有权限修改数据
		
		BeanUtils.copyProperties(dto, car);
		car.setStatus(CarConstants.CAR_STATUS_DOWN);
		carRepository.save(car);
		return ServerResponse.successWithData("修改成功");
	}
	
	
	/**
	 * 删除车辆（车主或者车主子账号）
	 * @author cat
	 * 
	 * @param dto	待删除的车辆Id
	 * @param customer	当前登录人
	 * @return
	 */
	@Override
	public ServerResponse delete(DeleteDto dto, Customer customer) {
		Car car = this.assertCarExist(dto.getId());
		this.assertCarNotDelete(car);
		// TODO 验证当前用户是否有权限删除车辆
		// TODO 判断当前车辆是否可以删除（暂未确认哪些条件下车辆可以删除）
		// TODO 车辆删除日志（暂未确认是否需要日志）
		carRepository.deleteCarById(dto.getId());
		return ServerResponse.successWithData("删除成功");
	}
	
	
	/**
	 * 提交审核（车主或者车主子账号）
	 * <p>下架状态的车辆可以提交审核，将状态修改为待审核</p>
	 * @author cat
	 * 
	 * @param dto	待提交审核的车辆Id
	 * @param customer	当前登录人
	 * @return
	 */
	@Override
	public ServerResponse submitAudit(CommonDto dto, Customer customer) {
		Car car = this.assertCarExist(dto.getId());
		this.assertCarNotDelete(car);
		// TODO 验证当前用户是否有权限修改数据
		if(!CarConstants.CAR_STATUS_DOWN.equalsIgnoreCase(car.getStatus())) {
			throw new BusinessException(112, "提交审核失败：仅下架状态的车辆可以提交审核");
		}
		carRepository.updateStatusById(dto.getId(), CarConstants.CAR_STATUS_WAIT_CHECK);
		return ServerResponse.successWithData("提交审核成功");
	}
	
	
	/**
	 * 断言车辆可用
	 * <p>数据库存在数据且未删除</p>
	 * @author cat
	 * 
	 * @param carId	车辆Id
	 * @return
	 */
	@Override
	public Car assertCarAvailable(Integer carId) {
		Car car = this.assertCarExist(carId);
		this.assertCarNotDelete(car);
		return car;
	}
	
	
	/**
	 * 断言车辆属于当前登录车主
	 * @author cat
	 * 
	 * @param carId	车辆Id
	 * @param carOwnerId	车主Id
	 */
	@Override
	public void assertCarBelongToCurrentUser(Integer carId, Integer carOwnerId) {
		Car car = this.assertCarAvailable(carId);
		if(!carOwnerId.equals(car.getCarOwnerId())) {
			throw new BusinessException(112, "参数有误");
		}
	}
}
