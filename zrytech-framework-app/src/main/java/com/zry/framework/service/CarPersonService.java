package com.zry.framework.service;

import org.springframework.stereotype.Service;

import com.zry.framework.dto.CarPersonPageDto;
import com.zry.framework.dto.CheckDto;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.common.entity.User;


@Service
public interface CarPersonService {
	
	public ServerResponse page(CarPersonPageDto dto, Integer pageNumber, Integer pageSize);
	
	public ServerResponse details(Integer id);
	
	public ServerResponse check(CheckDto checkDto, User user);
	
}
