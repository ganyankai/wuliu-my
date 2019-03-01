package com.zrytech.framework.app.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.zrytech.framework.app.dto.CarRecordPlacePageDto;
import com.zrytech.framework.app.entity.CarRecordPlace;

@Service
public interface CarRecordPlaceService {

	public Page<CarRecordPlace> page(Integer pageNumber, Integer pageSize, CarRecordPlacePageDto dto);
	
	public CarRecordPlace details(Integer id);
	
}
