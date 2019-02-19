package com.zry.framework.service;

import org.springframework.stereotype.Service;

import com.zry.framework.dto.CarLocationPageDto;
import com.zrytech.framework.base.entity.ServerResponse;

@Service
public interface CarLocationService {

	public ServerResponse page(CarLocationPageDto dto, Integer pageNum, Integer pageSize);
	
}
