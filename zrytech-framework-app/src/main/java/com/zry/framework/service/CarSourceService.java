package com.zry.framework.service;

import org.springframework.stereotype.Service;

import com.zry.framework.dto.CarSourcePageDto;
import com.zry.framework.dto.CheckDto;
import com.zry.framework.dto.CommonDto;
import com.zry.framework.dto.DetailsDto;
import com.zry.framework.dto.carrecordplace.CarRecordPlaceSaveDto;
import com.zry.framework.dto.carsource.CarSourceAddDto;
import com.zry.framework.dto.carsource.CarSourceCheckUpdateDto;
import com.zry.framework.dto.carsourcecar.CarSourceCarSaveDto;
import com.zry.framework.entity.Customer;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.common.entity.User;

@Service
public interface CarSourceService {
	
	public ServerResponse page(CarSourcePageDto dto, Integer pageNum, Integer pageSize);
	
	public ServerResponse details(Integer id);
	
	public ServerResponse check(CheckDto checkDto, User user);
	
	
	public ServerResponse add(CarSourceAddDto dto, Customer customer);
	
	public ServerResponse updateCheck(CarSourceCheckUpdateDto dto, Customer customer);
	
	public ServerResponse submitAudit(CommonDto dto, Customer customer);
	
	public ServerResponse details(DetailsDto dto, Customer customer);
	
	public ServerResponse page(CarSourcePageDto dto, Integer pageNum, Integer pageSize, Customer customer);
	
	public ServerResponse saveLine(CarRecordPlaceSaveDto dto, Customer customer);
	
	public ServerResponse saveCarSourceCar(CarSourceCarSaveDto dto, Customer customer);
	
	
}
