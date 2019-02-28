package com.zrytech.framework.service;

import org.springframework.stereotype.Service;

import com.zrytech.framework.dto.CarCargoOwnnerPageDto;
import com.zrytech.framework.dto.CheckDto;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.common.entity.User;

@Service
public interface CarCargoOwnerService {

	public ServerResponse page(CarCargoOwnnerPageDto dto, Integer pageNum, Integer pageSize);
	
	public ServerResponse details(Integer id);
	
	public ServerResponse check(CheckDto checkDto, User user);
	
}
