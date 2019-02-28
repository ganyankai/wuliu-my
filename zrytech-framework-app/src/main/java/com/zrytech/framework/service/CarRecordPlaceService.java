package com.zrytech.framework.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.zrytech.framework.dto.CarRecordPlacePageDto;
import com.zrytech.framework.entity.CarRecordPlace;

@Service
public interface CarRecordPlaceService {

	public Page<CarRecordPlace> page(Integer pageNumber, Integer pageSize, CarRecordPlacePageDto dto);
	
	public CarRecordPlace details(Integer id);
	
}
