package com.zrytech.framework.app.service;

import org.springframework.stereotype.Service;

import com.zrytech.framework.app.dto.CommonDto;
import com.zrytech.framework.app.dto.DetailsDto;
import com.zrytech.framework.app.dto.approve.ApproveDto;
import com.zrytech.framework.app.dto.carrecordplace.CarRecordPlaceSaveDto;
import com.zrytech.framework.app.dto.carsource.CarOwnerCarSourcePageDto;
import com.zrytech.framework.app.dto.carsource.CarSourceAddDto;
import com.zrytech.framework.app.dto.carsource.CarSourceCheckUpdateDto;
import com.zrytech.framework.app.dto.carsource.CarSourcePageDto;
import com.zrytech.framework.app.dto.carsourcecar.CarSourceCarSaveDto;
import com.zrytech.framework.app.entity.CarSource;
import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.base.entity.PageData;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.common.entity.User;

@Service
public interface CarSourceService {
	
	/**
	 * 车源分页
	 * @author cat
	 * 
	 * @param dto
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageData<CarSource> carSourcePage(CarSourcePageDto dto, Integer pageNum, Integer pageSize);
	
	/**
	 * 管理员 - 车源详情
	 * @author cat
	 * 
	 * @param dto	车源Id
	 * @return
	 */
	public ServerResponse adminDetails(DetailsDto dto);
	
	/**
	 * 管理员 - 车源审批
	 * @author cat
	 * 
	 * @param dto
	 * @param user
	 * @return
	 */
	public ServerResponse adminApprove(ApproveDto dto, User user);
	
	
	
	
	public ServerResponse add(CarSourceAddDto dto, Customer customer);
	
	public ServerResponse updateCheck(CarSourceCheckUpdateDto dto, Customer customer);
	
	public ServerResponse submitAudit(CommonDto dto, Customer customer);
	
	public ServerResponse details(DetailsDto dto, Customer customer);
	
	public ServerResponse page(CarOwnerCarSourcePageDto dto, Integer pageNum, Integer pageSize, Customer customer);
	
	public ServerResponse saveLine(CarRecordPlaceSaveDto dto, Customer customer);
	
	public ServerResponse saveCarSourceCar(CarSourceCarSaveDto dto, Customer customer);
	
	
}
