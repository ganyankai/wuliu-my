package com.zry.framework.service.impl;

import java.util.List;
import java.util.Optional;

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

@Service
public class CarServiceImpl implements CarService {
	
	@Autowired
	private CarRepository carRepository;
	
	
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
		Car car = carRepository.findById(id).get();
		
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
	public Page<Car> page(CarPageDto carPageDto, Integer pageNum, Integer pageSize){
		Car car = new Car();
		car.setCarNo(carPageDto.getCarNo());
		// 排序
		Sort sort = Sort.by(Direction.DESC, "id");
		// 分页（JPA分页中pageNumber从0开始）
		Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
	
		// 模糊搜索，忽略字段等
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("carNo", GenericPropertyMatchers.contains())
				.withIgnorePaths("id");
		// 查询条件
		Example<Car> example = Example.of(car, matcher);
		// 查询
		Page<Car> page = carRepository.findAll(example, pageable);
		
		return page;
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
