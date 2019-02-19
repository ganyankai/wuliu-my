package com.zry.framework.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.ExampleMatcher.MatcherConfigurer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.zry.framework.repository.CarCargoOwnnerRepository;
import com.zry.framework.repository.CarPersonRepository;
import com.zry.framework.repository.CarRepository;
import com.github.pagehelper.PageHelper;
import com.zry.framework.constants.CarConstants;
import com.zry.framework.dto.CarPageDto;
import com.zry.framework.dto.CheckDto;
import com.zry.framework.entity.Car;
import com.zry.framework.entity.CarCargoOwnner;
import com.zry.framework.entity.CarPerson;
import com.zry.framework.mapper.CarMapper;
import com.zry.framework.service.CarService;
import com.zry.framework.utils.PageDataUtils;
import com.zrytech.framework.base.entity.PageData;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;

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
	
	@Autowired private PageDataUtils<Car> pageDataUtils;
	
	@Autowired private CarCargoOwnnerRepository carCargoOwnnerRepository;
	
	@Autowired private CarPersonRepository carPersonRepository;
	
	@Autowired private CarMapper carMapper;
	
	
	/**
	 * 车辆分页
	 * @author cat
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
	 * 
	 * @param checkDto 审核结果
	 */
	public ServerResponse check(CheckDto checkDto) {
		Integer businessId = checkDto.getBusinessId();
		
		Car car = this.assertCarExist(businessId);
		
		if(!CarConstants.CAR_STATUS_WAIT_CHECK.equalsIgnoreCase(car.getStatus())) {
			throw new BusinessException(112, "审核失败：车辆状态不是待审核");
		}
		
		// 修改车辆状态
		Boolean result = checkDto.getResult();
		if(result) {
			car.setStatus(CarConstants.CAR_STATUS_UP);
		}else {
			car.setStatus(CarConstants.CAR_STATUS_DOWN);
		}
		carRepository.save(car);
		
		// 添加审核记录 TODO
		
		return ServerResponse.success();
	}
	
	
	/**
	 * 断言车辆存在
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
	
	
	public Integer save(Car car) {
		Car save = carRepository.save(car);
		
		return save.getId();
	}
	
	
}
