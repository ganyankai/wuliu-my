package com.zrytech.framework.app.service;

import org.springframework.stereotype.Service;

import com.zrytech.framework.app.dto.CarPersonPageDto;
import com.zrytech.framework.app.dto.CheckDto;
import com.zrytech.framework.app.dto.CommonDto;
import com.zrytech.framework.app.dto.DeleteDto;
import com.zrytech.framework.app.dto.DetailsDto;
import com.zrytech.framework.app.dto.carperson.CarOwnerCarPersonPageDto;
import com.zrytech.framework.app.dto.carperson.CarPersonAddDto;
import com.zrytech.framework.app.dto.carperson.CarPersonCheckUpdateDto;
import com.zrytech.framework.app.dto.carperson.CarPersonEnabledDto;
import com.zrytech.framework.app.dto.carperson.CarPersonNoCheckUpdateDto;
import com.zrytech.framework.app.entity.Customer;
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
