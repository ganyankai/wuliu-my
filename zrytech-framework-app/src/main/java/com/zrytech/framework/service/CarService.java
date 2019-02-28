package com.zrytech.framework.service;

import org.springframework.stereotype.Service;

import com.zrytech.framework.dto.CarAddDto;
import com.zrytech.framework.dto.CarCheckUpdateDto;
import com.zrytech.framework.dto.CarNoCheckUpdateDto;
import com.zrytech.framework.dto.CarPageDto;
import com.zrytech.framework.dto.CheckDto;
import com.zrytech.framework.dto.CommonDto;
import com.zrytech.framework.dto.DeleteDto;
import com.zrytech.framework.dto.DetailsDto;
import com.zrytech.framework.dto.car.CarOwnerCarPageDto;
import com.zrytech.framework.entity.Customer;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.common.entity.User;

@Service
public interface CarService {

	public ServerResponse page(CarPageDto carPageDto, Integer pageNum, Integer pageSize);
	
	public ServerResponse details(Integer id);
	
	public ServerResponse check(CheckDto dto, User user);
	
	public ServerResponse delete(DeleteDto dto, Customer customer);
	
	public ServerResponse submitAudit(CommonDto dto, Customer customer);
	
	public ServerResponse updateNoCheck(CarNoCheckUpdateDto dto, Customer customer);
	
	public ServerResponse updateCheck(CarCheckUpdateDto dto, Customer customer);
	
	public ServerResponse add(CarAddDto dto, Customer customer);
	
	public ServerResponse details(DetailsDto dto, Customer customer);
	
	public ServerResponse page(CarOwnerCarPageDto dto, Integer pageNum, Integer pageSize, Customer customer);
}
