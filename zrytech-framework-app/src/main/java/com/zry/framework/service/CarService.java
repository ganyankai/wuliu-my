package com.zry.framework.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.zry.framework.dto.CarPageDto;
import com.zry.framework.entity.Car;
import com.zrytech.framework.base.entity.ServerResponse;

@Service
public interface CarService {

	public ServerResponse page(CarPageDto carPageDto, Integer pageNum, Integer pageSize);
	
	public Integer save(Car car);
	
	
}
