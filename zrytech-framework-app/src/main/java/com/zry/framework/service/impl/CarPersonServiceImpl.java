package com.zry.framework.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zry.framework.repository.ApproveLogRepository;
import com.zry.framework.repository.CarCargoOwnnerRepository;
import com.zry.framework.repository.CarPersonRepository;
import com.github.pagehelper.PageHelper;
import com.zry.framework.constants.ApproveLogConstants;
import com.zry.framework.constants.CarPersonConstants;
import com.zry.framework.dto.CarPersonPageDto;
import com.zry.framework.dto.CheckDto;
import com.zry.framework.entity.ApproveLog;
import com.zry.framework.entity.CarCargoOwnner;
import com.zry.framework.entity.CarPerson;
import com.zry.framework.mapper.CarPersonMapper;
import com.zry.framework.service.CarPersonService;
import com.zrytech.framework.base.entity.PageData;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.common.entity.User;

/**
 * 司机与押货人
 * @author cat
 *
 */
@Service
@Transactional
public class CarPersonServiceImpl implements CarPersonService {
	
	@Autowired private CarPersonRepository carPersonRepository;

	@Autowired private CarPersonMapper carPersonMapper;
	
	@Autowired private CarCargoOwnnerRepository carCargoOwnnerRepository;
	
	@Autowired private ApproveLogRepository approveLogRepository;
	
	
	
	/**
	 * 司机与压货人分页
	 * @author cat
	 * 
	 * @param dto
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Override
	public ServerResponse page(CarPersonPageDto dto, Integer pageNum, Integer pageSize){
		com.github.pagehelper.Page<Object> result = PageHelper.startPage(pageNum, pageSize);
		List<CarPerson> list = carPersonMapper.selectSelective(dto);
		for (CarPerson carPerson : list) {
			carPerson.setCarOwnerName(carCargoOwnnerRepository.findNameById(carPerson.getCreateBy()));
		}
		PageData<CarPerson> pageData = new PageData<CarPerson>(result.getPageSize(), result.getPageNum(), result.getTotal(), list);
		return ServerResponse.successWithData(pageData);
	}
	
	
	/**
	 * 司机或者押货人详情
	 * @author cat
	 * 
	 * @param id	司机或者押货人Id
	 * @return
	 */
	@Override
	public ServerResponse details(Integer id) {
		CarPerson carPerson = carPersonRepository.findOne(id);
		carPerson = this.bindingCarOwner(carPerson);
		return ServerResponse.successWithData(carPerson);
	}
	
	
	/**
	 * 为司机或者押货人绑定 车主信息
	 * @author cat
	 * 
	 * @param carPerson
	 * @return
	 */
	public CarPerson bindingCarOwner(CarPerson carPerson) {
		if(carPerson.getCreateBy() != null) {
			CarCargoOwnner carOwner = carCargoOwnnerRepository.findOne(carPerson.getCreateBy());
			carPerson.setCarOwnerName(carOwner.getName());
			carPerson.setCarOwner(carOwner);
		}
		return carPerson;
	}
	
	
	
	
	
	/**
	 * 司机或押货人审核
	 * @author cat
	 * 
	 * @param checkDto
	 */
	@Override
	public ServerResponse check(CheckDto checkDto, User user) {
		Integer businessId = checkDto.getBusinessId();
		
		CarPerson carPerson = assertCarPersonExist(businessId);
		
		if(!CarPersonConstants.PERSON_STATUS_WAIT_CHECK.equalsIgnoreCase(carPerson.getStatus())) {
			throw new BusinessException(112, "审核失败：司机或押货人的状态不是待审核");
		}
		if(carPerson.getIsDelete()) {
			throw new BusinessException(112, "审核失败：司机或押货人已被删除");
		}
		
		// 修改车辆状态
		String result = checkDto.getResult();
		if(ApproveLogConstants.APPROVE_RESULT_PASS.equals(result)) {
			carPerson.setStatus(CarPersonConstants.PERSON_STATUS_UP);
		}else {
			carPerson.setStatus(CarPersonConstants.PERSON_STATUS_DOWN);
		}
		carPersonRepository.save(carPerson);
		
		// 添加审核记录
		ApproveLog entity = new ApproveLog();
		entity.setApproveBy(user.getId());
		entity.setApproveContent(checkDto.getContent());
		entity.setApproveResult(checkDto.getResult());
		entity.setApproveTime(new Date());
		entity.setApproveType(ApproveLogConstants.APPROVE_TYPE_CAR_PERSON);
		entity.setBusinessId(businessId);
		approveLogRepository.save(entity);
		
		return ServerResponse.successWithData("审核成功");
	}
	
	
	/**
	 * 断言司机或押货人存在
	 * @author cat
	 * 
	 * @param carPersonId	司机或押货人Id
	 * @return
	 */
	public CarPerson assertCarPersonExist(Integer carPersonId) {
		CarPerson carPerson = carPersonRepository.findOne(carPersonId);
		if(carPerson == null){
			throw new BusinessException(112, "司机或押货人不存在");
		}
		return carPerson;
	}
	
	
}
