package com.zrytech.framework.app.service;

import org.springframework.stereotype.Service;

import com.zrytech.framework.app.dto.CheckDto;
import com.zrytech.framework.app.dto.CommonDto;
import com.zrytech.framework.app.dto.DeleteDto;
import com.zrytech.framework.app.dto.DetailsDto;
import com.zrytech.framework.app.dto.carperson.CarOwnerCarPersonPageDto;
import com.zrytech.framework.app.dto.carperson.CarPersonAddDto;
import com.zrytech.framework.app.dto.carperson.CarPersonCheckUpdateDto;
import com.zrytech.framework.app.dto.carperson.CarPersonEnabledDto;
import com.zrytech.framework.app.dto.carperson.CarPersonNoCheckUpdateDto;
import com.zrytech.framework.app.dto.carperson.CarPersonPageDto;
import com.zrytech.framework.app.entity.CarPerson;
import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.common.entity.User;


@Service
public interface CarPersonService {
	
	/**
	 * 断言司机压货人属于当前登录人车主
	 * @author cat
	 * 
	 * @param carPersonId	司机压货人Id
	 * @param carOwnerId	车主Id
	 * @param personType	类型
	 */
	public void assertCarPersonBelongToCurrentUser(Integer carPersonId, Integer carOwnerId, String personType);
	
	/**
	 * 断言司机压货人可用
	 * <p>数据库存在数据且未删除</p>
	 * @author cat
	 * 
	 * @param carPersonId	司机压货人Id
	 * @return
	 */
	public CarPerson assertCarPersonAvailable(Integer carPersonId);
	
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
