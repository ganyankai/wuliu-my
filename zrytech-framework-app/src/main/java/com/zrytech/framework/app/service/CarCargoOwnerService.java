package com.zrytech.framework.app.service;

import org.springframework.stereotype.Service;

import com.zrytech.framework.app.dto.CarCargoOwnnerPageDto;
import com.zrytech.framework.app.dto.CheckDto;
import com.zrytech.framework.app.dto.DetailsDto;
import com.zrytech.framework.app.dto.carcargoowner.CarCargoOwnerUpdateAvoidAuditDto;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.common.entity.User;

@Service
public interface CarCargoOwnerService {

	public ServerResponse page(CarCargoOwnnerPageDto dto, Integer pageNum, Integer pageSize);
	
	public ServerResponse details(Integer id);
	
	public ServerResponse check(CheckDto checkDto, User user);
	
	
	public ServerResponse approveCarOwner(CheckDto checkDto, User user);
	
	public ServerResponse approveCargoOwner(CheckDto dto, User user);
	
	
	public ServerResponse carOwnerPage(CarCargoOwnnerPageDto dto, Integer pageNum, Integer pageSize);
	
	public ServerResponse cargoOwnerPage(CarCargoOwnnerPageDto dto, Integer pageNum, Integer pageSize);
	
	public ServerResponse carOwnerDetails(DetailsDto dto);
	
	public ServerResponse cargoOwnerDetails(DetailsDto dto);
	
	
	/**
	 * 管理员 - 修改车主免审核状态
	 * @author cat
	 * 
	 * @param dto
	 * @param customer
	 * @return
	 */
	public ServerResponse carOwnerUpdateAvoidAudit(CarCargoOwnerUpdateAvoidAuditDto dto);
	
	
	/**
	 * 管理员 - 修改货主免审核状态
	 * @author cat
	 * 
	 * @param dto
	 * @param customer
	 * @return
	 */
	public ServerResponse cargoOwnerUpdateAvoidAudit(CarCargoOwnerUpdateAvoidAuditDto dto);
}
