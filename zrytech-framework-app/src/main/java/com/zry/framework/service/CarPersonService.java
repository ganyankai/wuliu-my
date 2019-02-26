package com.zry.framework.service;

import org.springframework.stereotype.Service;

import com.zry.framework.dto.CarPersonPageDto;
import com.zry.framework.dto.CheckDto;
import com.zry.framework.dto.CommonDto;
import com.zry.framework.dto.DeleteDto;
import com.zry.framework.dto.DetailsDto;
import com.zry.framework.dto.carperson.CarOwnerCarPersonPageDto;
import com.zry.framework.dto.carperson.CarPersonAddDto;
import com.zry.framework.dto.carperson.CarPersonCheckUpdateDto;
import com.zry.framework.dto.carperson.CarPersonEnabledDto;
import com.zry.framework.dto.carperson.CarPersonNoCheckUpdateDto;
import com.zry.framework.entity.Customer;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.common.entity.User;


@Service
public interface CarPersonService {
	
	public ServerResponse page(CarPersonPageDto dto, Integer pageNumber, Integer pageSize);
	
	public ServerResponse details(Integer id);
	
	public ServerResponse check(CheckDto checkDto, User user);
	
	public ServerResponse page(CarOwnerCarPersonPageDto dto, Integer pageNum, Integer pageSize, Customer customer);
	
	public ServerResponse details(DetailsDto dto, Customer customer);
	
	public ServerResponse add(CarPersonAddDto dto, Customer customer);
	
	public ServerResponse delete(DeleteDto dto, Customer customer);
	
	public ServerResponse enabled(CarPersonEnabledDto dto, Customer customer);
	
	public ServerResponse updateNoCheck(CarPersonNoCheckUpdateDto dto, Customer customer);
	
	public ServerResponse updateCheck(CarPersonCheckUpdateDto dto, Customer customer);
	
	public ServerResponse submitAudit(CommonDto dto, Customer customer);
	
}
