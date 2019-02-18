package com.zry.framework.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.zry.framework.dto.CarPersonPageDto;
import com.zry.framework.entity.CarPerson;


@Service
public interface CarPersonService {
	
	public Page<CarPerson> page(Integer pageNumber, Integer pageSize, CarPersonPageDto dto);
	
}
