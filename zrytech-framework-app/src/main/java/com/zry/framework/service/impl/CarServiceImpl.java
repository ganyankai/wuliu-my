package com.zry.framework.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.zry.framework.repository.CarRepository;
import com.zry.framework.dto.CarPageDto;
import com.zry.framework.dto.CheckDto;
import com.zry.framework.entity.Car;
import com.zry.framework.service.CarService;
import com.zry.framework.utils.PageDataUtils;
import com.zrytech.framework.base.entity.PageData;
import com.zrytech.framework.base.entity.ServerResponse;

@Service
public class CarServiceImpl implements CarService {
	
	@Autowired private CarRepository carRepository;
	
	@Autowired private PageDataUtils<Car> pageDataUtils;
	
	public Integer save(Car car) {
		Car save = carRepository.save(car);
		
		return save.getId();
	}
	
	
	/**
	 * 详情
	 * @param id
	 * @return
	 */
	public Car details(Integer id) {
		Car car = carRepository.findOne(id);
		// TODO 车主
		// TODO 司机
		// TODO 压货人
		
		return car;
	}
	

	
	/**
	 * 
	 * @return
	 */
	@Override
	public ServerResponse page(CarPageDto dto, Integer pageNum, Integer pageSize){
		Car car = new Car();
		BeanUtils.copyProperties(dto, car);
		
		Sort sort = new Sort(Direction.DESC, "createDate");
		
		Pageable pageable = new PageRequest(pageNum - 1, pageSize, sort);
	
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("carNo", GenericPropertyMatchers.contains())
				.withIgnorePaths("id");
		
		Example<Car> example = Example.of(car, matcher);
		
		Page<Car> page = carRepository.findAll(example, pageable);
		
		PageData<Car> pageData = pageDataUtils.bindingData(page);
		
		return ServerResponse.successWithData(pageData);
	}
	
	
	
	/**
	 * 车辆审核
	 * @param checkDto
	 */
	public void check(CheckDto checkDto) {
		// 判断车辆是否存在 TODO
		
		// 修改车辆状态 TODO
		
		// 添加审核记录 TODO
		
		
	}
	
	
	
	
}
