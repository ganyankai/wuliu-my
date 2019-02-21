package com.zry.framework.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.zry.framework.constants.ApproveLogConstants;
import com.zry.framework.dto.CarCargoOwnnerPageDto;
import com.zry.framework.dto.CheckDto;
import com.zry.framework.entity.ApproveLog;
import com.zry.framework.entity.CarCargoOwnner;
import com.zry.framework.entity.Customer;
import com.zry.framework.mapper.CarCargoOwnerMapper;
import com.zry.framework.repository.ApproveLogRepository;
import com.zry.framework.repository.CarCargoOwnnerRepository;
import com.zry.framework.repository.CustomerRepository;
import com.zry.framework.service.CarCargoOwnerService;
import com.zrytech.framework.base.entity.PageData;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.common.entity.User;

@Service
public class CarCargoOwnerServiceImpl implements CarCargoOwnerService {


	@Autowired private CarCargoOwnnerRepository carCargoOwnnerRepository;
	
	@Autowired private CarCargoOwnerMapper carCargoOwnerMapper;
	
	@Autowired private CustomerRepository customerRepository;
	
	@Autowired private ApproveLogRepository approveLogRepository;
	
	
	/**
	 * 车主货主分页
	 * @author cat
	 * 
	 * @param dto
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Override
	public ServerResponse page(CarCargoOwnnerPageDto dto, Integer pageNum, Integer pageSize){
		com.github.pagehelper.Page<Object> result = PageHelper.startPage(pageNum, pageSize);
		List<CarCargoOwnner> list = carCargoOwnerMapper.selectSelective(dto);
		for (CarCargoOwnner carCargoOwnner : list) {
			carCargoOwnner = bindingCustomer(carCargoOwnner);
		}
		PageData<CarCargoOwnner> pageData = new PageData<CarCargoOwnner>(result.getPageSize(), result.getPageNum(), result.getTotal(), list);
		return ServerResponse.successWithData(pageData);
	}
	
	
	/**
	 * 车主货主详情
	 * @author cat
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ServerResponse details(Integer id) {
		CarCargoOwnner carCargoOwnner = carCargoOwnnerRepository.findOne(id);
		carCargoOwnner = bindingCustomer(carCargoOwnner);
		return ServerResponse.successWithData(carCargoOwnner);
	}
	
	
	/**
	 * 为车主货主绑定账号信息
	 * @author cat
	 * 
	 * @param carCargoOwnner
	 * @return
	 */
	public CarCargoOwnner bindingCustomer(CarCargoOwnner carCargoOwnner) {
		Integer customerId = carCargoOwnner.getCustomerId();
		if(customerId != null) {
			Customer customer = customerRepository.findOne(customerId);
			customer.setPassword(null);
			carCargoOwnner.setCustomer(customer);
		}
		return carCargoOwnner;
	}
	
	/**
	 * 车主货主审核
	 * @author cat
	 * 
	 * @param checkDto
	 * @param user
	 * @return
	 */
	@Override
	public ServerResponse check(CheckDto checkDto, User user) {
		Integer businessId = checkDto.getBusinessId();
		// TODO
		/*Car car = this.assertCarExist(businessId);
		
		if(!CarConstants.CAR_STATUS_WAIT_CHECK.equalsIgnoreCase(car.getStatus())) {
			throw new BusinessException(112, "审核失败：车辆状态不是待审核");
		}
		if(car.getIsDelete()) {
			throw new BusinessException(112, "审核失败：车辆已被删除");
		}*/
		
		// 修改车辆状态
		/*String result = checkDto.getResult();
		if(ApproveLogConstants.APPROVE_RESULT_PASS.equals(result)) {
			car.setStatus(CarConstants.CAR_STATUS_UP);
		}else {
			car.setStatus(CarConstants.CAR_STATUS_DOWN);
		}
		carRepository.save(car);*/
		
		// 添加审核记录
		ApproveLog entity = new ApproveLog();
		entity.setApproveBy(user.getId());
		entity.setApproveContent(checkDto.getContent());
		entity.setApproveResult(checkDto.getResult());
		entity.setApproveTime(new Date());
		entity.setApproveType(ApproveLogConstants.APPROVE_TYPE_CAR_OWNER);
		entity.setBusinessId(businessId);
		approveLogRepository.save(entity);
		
		return ServerResponse.successWithData("审核成功");
	}
}
