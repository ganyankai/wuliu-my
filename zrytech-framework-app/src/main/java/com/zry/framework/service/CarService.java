package com.zry.framework.service;

import org.springframework.stereotype.Service;

import com.zry.framework.dto.CarPageDto;
import com.zry.framework.dto.CheckDto;
import com.zry.framework.entity.Car;
import com.zrytech.framework.base.entity.ServerResponse;

@Service
public interface CarService {

	public ServerResponse page(CarPageDto carPageDto, Integer pageNum, Integer pageSize);
	
	public Integer save(Car car);
	
	public ServerResponse details(Integer id);
	
	public ServerResponse check(CheckDto dto);
	
	
}
