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
	
	
	
	
	
	/**
	 * 管理员 - 车主分页
	 * @author cat
	 * 
	 * @param dto
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public ServerResponse adminCarOwnerPage(CarCargoOwnnerPageDto dto, Integer pageNum, Integer pageSize);
	
	/**
	 * 管理员 - 待审批的车主分页
	 * @author cat
	 * 
	 * @param dto
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public ServerResponse adminApprovePendingCarOwnerPage(CarCargoOwnnerPageDto dto, Integer pageNum, Integer pageSize);
	
	/**
	 * 管理员 - 车主详情
	 * @author cat
	 * 
	 * @param dto	车主Id
	 * @return
	 */
	public ServerResponse adminCarOwnerDetails(DetailsDto dto);
	
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
	 * 管理员 - 车主审批
	 * @author cat
	 * 
	 * @param checkDto	审批结果
	 * @param user	管理员
	 * @return
	 */
	public ServerResponse adminApproveCarOwner(CheckDto checkDto, User user);
	

	
	
	/**
	 * 管理员 - 货主分页
	 * @author cat
	 * 
	 * @param dto
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public ServerResponse adminCargoOwnerPage(CarCargoOwnnerPageDto dto, Integer pageNum, Integer pageSize);
	
	/**
	 * 管理员 - 待审批的货主分页
	 * @author cat
	 * 
	 * @param dto
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public ServerResponse adminApprovePendingCargoOwnerPage(CarCargoOwnnerPageDto dto, Integer pageNum, Integer pageSize);
	
	/**
	 * 管理员 - 货主详情
	 * @author cat
	 * 
	 * @param dto	货主Id
	 * @return
	 */
	public ServerResponse adminCargoOwnerDetails(DetailsDto dto);
	
	/**
	 * 管理员 - 修改货主免审核状态
	 * @author cat
	 * 
	 * @param dto
	 * @param customer
	 * @return
	 */
	public ServerResponse cargoOwnerUpdateAvoidAudit(CarCargoOwnerUpdateAvoidAuditDto dto);
	
	/**
	 * 管理员 - 货主审批
	 * @author cat
	 * 
	 * @param checkDto	审批结果
	 * @param user	管理员
	 * @return
	 */
	public ServerResponse adminApproveCargoOwner(CheckDto dto, User user);
	
	
}
