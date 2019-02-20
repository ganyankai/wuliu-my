package com.zry.framework.service;

import org.springframework.stereotype.Service;

import com.zry.framework.dto.CargoMatterPageDto;
import com.zrytech.framework.base.entity.ServerResponse;

@Service
public interface CargoMatterService {
	
	public ServerResponse page(CargoMatterPageDto dto, Integer pageNum, Integer pageSize);
	
	public ServerResponse details(Integer id);

}
