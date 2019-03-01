package com.zrytech.framework.app.service;

import org.springframework.stereotype.Service;

import com.zrytech.framework.app.dto.CarLocationPageDto;
import com.zrytech.framework.base.entity.ServerResponse;

@Service
public interface CarLocationService {

	public ServerResponse page(CarLocationPageDto dto, Integer pageNum, Integer pageSize);
	
}
