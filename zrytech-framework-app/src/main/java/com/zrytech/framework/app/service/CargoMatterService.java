package com.zrytech.framework.app.service;

import org.springframework.stereotype.Service;

import com.zrytech.framework.app.dto.CargoMatterPageDto;
import com.zrytech.framework.app.dto.DetailsDto;
import com.zrytech.framework.app.dto.approve.ApproveDto;
import com.zrytech.framework.app.dto.cargomatter.CarOwnerCargoMatterPageDto;
import com.zrytech.framework.app.dto.cargomatter.CargoMatterAddDto;
import com.zrytech.framework.app.dto.cargomatter.CargoMatterUpdateDto;
import com.zrytech.framework.app.entity.CargoMatter;
import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.base.entity.PageData;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.entity.User;

@Service
public interface CargoMatterService {
	
	/**
	 * 报价单分页
	 * @author cat
	 * 
	 * @param dto
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageData<CargoMatter> cargoMatterPage(CargoMatterPageDto dto, Integer pageNum, Integer pageSize);
	
	/**
	 * 管理员 - 报价单详情
	 * @author cat
	 * 
	 * @param dto
	 * @return
	 */
	public ServerResponse adminDetails(DetailsDto dto);
	
	/**
	 * 管理员 -  报价单审批
	 * @author cat
	 * 
	 * @param dto
	 * @param user
	 * @return
	 */
	public ServerResponse adminApprove(ApproveDto dto, User user);
	
	
	public ServerResponse add(CargoMatterAddDto dto, Customer customer);
	
	public ServerResponse update(CargoMatterUpdateDto dto, Customer customer);
	
	public ServerResponse details(DetailsDto dto, Customer customer);
	
	public ServerResponse page(CarOwnerCargoMatterPageDto dto, Integer pageNum, Integer pageSize, Customer customer);
	

}
