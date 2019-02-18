package com.zry.framework.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.zry.framework.dto.CarRecordPlacePageDto;
import com.zry.framework.entity.CarRecordPlace;

@Service
public interface CarRecordPlaceService {

	public Page<CarRecordPlace> page(Integer pageNumber, Integer pageSize, CarRecordPlacePageDto dto);
	
	public CarRecordPlace details(Integer id);
	
}
