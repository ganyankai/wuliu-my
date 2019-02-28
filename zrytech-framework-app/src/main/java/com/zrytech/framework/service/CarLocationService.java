package com.zrytech.framework.service;

import org.springframework.stereotype.Service;

import com.zrytech.framework.dto.CarLocationPageDto;
import com.zrytech.framework.base.entity.ServerResponse;

@Service
public interface CarLocationService {

	public ServerResponse page(CarLocationPageDto dto, Integer pageNum, Integer pageSize);
	
}
