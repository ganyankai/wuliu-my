package com.zry.framework.service;

import org.springframework.stereotype.Service;

import com.zry.framework.dto.CarPageDto;
import com.zry.framework.dto.CheckDto;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.common.entity.User;

@Service
public interface CarService {

	public ServerResponse page(CarPageDto carPageDto, Integer pageNum, Integer pageSize);
	
	public ServerResponse details(Integer id);
	
	public ServerResponse check(CheckDto dto, User user);
	
	
}
