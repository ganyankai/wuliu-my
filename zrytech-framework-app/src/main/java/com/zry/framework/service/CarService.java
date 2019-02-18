package com.zry.framework.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.zry.framework.dto.CarPageDto;
import com.zry.framework.entity.Car;

@Service
public interface CarService {

	public Page<Car> page(CarPageDto carPageDto, Integer pageNum, Integer pageSize);
	
	public Integer save(Car car);
	
	
}
