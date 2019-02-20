package com.zry.framework.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zry.framework.repository.ApproveLogRepository;
import com.zry.framework.repository.CarCargoOwnnerRepository;
import com.zry.framework.repository.CarRecordPlaceRepository;
import com.zry.framework.repository.CarSourceCarRepository;
import com.zry.framework.repository.CarSourceRepository;
import com.github.pagehelper.PageHelper;
import com.zry.framework.constants.ApproveLogConstants;
import com.zry.framework.constants.CarSourceConstants;
import com.zry.framework.dto.CarSourcePageDto;
import com.zry.framework.dto.CheckDto;
import com.zry.framework.entity.ApproveLog;
import com.zry.framework.entity.CarRecordPlace;
import com.zry.framework.entity.CarSource;
import com.zry.framework.entity.CarSourceCar;
import com.zry.framework.mapper.CarSourceMapper;
import com.zry.framework.service.CarSourceService;
import com.zrytech.framework.base.entity.PageData;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.common.entity.User;

/**
 * 车源
 * @author cat
 *
 */
@Service
@Transactional
public class CarSourceServiceImpl implements CarSourceService {
	
	@Autowired private CarSourceRepository carSourceRepository;
	
	@Autowired private CarRecordPlaceRepository carRecordPlaceRepository;
	
	@Autowired private CarSourceMapper carSourceMapper;
	
	@Autowired private CarCargoOwnnerRepository carCargoOwnnerRepository;
	
	@Autowired private CarSourceCarRepository carSourceCarRepository;
	
	@Autowired private ApproveLogRepository approveLogRepository;
	
	
	
	/**
	 * 车源分页
	 * @author cat
	 * 
	 * @param dto
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Override
	public ServerResponse page(CarSourcePageDto dto, Integer pageNum, Integer pageSize){
		com.github.pagehelper.Page<Object> result = PageHelper.startPage(pageNum, pageSize);
		List<CarSource> list = carSourceMapper.selectSelective(dto);
		for (CarSource carSource : list) {
			// 路线列表
			List<CarRecordPlace> carRecordPlaces = carRecordPlaceRepository.findByCarSourceId(carSource.getId());
			carSource.setCarRecordPlaces(carRecordPlaces);
			// 车主企业名称
			carSource = bindingCarOwnerName(carSource);
		}
		PageData<CarSource> pageData = new PageData<CarSource>(result.getPageSize(), result.getPageNum(), result.getTotal(), list);
		return ServerResponse.successWithData(pageData);
	}
	
	
	/**
	 * 车源详情
	 * @author cat
	 * 
	 * @param id	车源Id
	 * @return
	 */
	@Override
	public ServerResponse details(Integer id) {
		CarSource carSource = carSourceRepository.findOne(id);
		// 路线列表
		carSource = bindingCarRecordPlace(carSource);
		// 车主企业名称
		carSource = bindingCarOwnerName(carSource);
		// 车辆列表
		carSource = bindingCarSourceCar(carSource);
		return ServerResponse.successWithData(carSource);
	}
	
	
	/**
	 * 为车源设置车辆列表
	 * @author cat
	 * 
	 * @param carSource
	 * @return
	 */
	public CarSource bindingCarSourceCar(CarSource carSource) {
		Integer carSourceId = carSource.getId();
		if(carSourceId != null ) {
			List<CarSourceCar> carSourceCars = carSourceCarRepository.findByCarSourceId(carSourceId);
			carSource.setCarSourceCars(carSourceCars);
		}
		return carSource;
	}
	
	
	/**
	 * 为车源设置路线列表
	 * @author cat
	 * 
	 * @param carSource
	 * @return
	 */
	public CarSource bindingCarRecordPlace(CarSource carSource) {
		Integer carSourceId = carSource.getId();
		if(carSourceId != null ) {
			List<CarRecordPlace> carRecordPlaces = carRecordPlaceRepository.findByCarSourceId(carSourceId);
			carSource.setCarRecordPlaces(carRecordPlaces);
		}
		return carSource;
	}
	
	
	/**
	 * 为车源设置车主企业名称
	 * @author cat
	 * 
	 * @param carSource
	 * @return
	 */
	public CarSource bindingCarOwnerName(CarSource carSource) {
		Integer createBy = carSource.getCreateBy();
		if(createBy != null ) {
			carSource.setCarOwnerName(carCargoOwnnerRepository.findNameById(createBy));
		}
		return carSource;
	}
	
	/**
	 * 断言车源存在
	 * @author cat
	 * 
	 * @param carSourceId	车源Id
	 * @return
	 */
	public CarSource assertCarSourceExist(Integer carSourceId) {
		CarSource carSource = carSourceRepository.findOne(carSourceId);
		if(carSource == null) {
			throw new BusinessException(112, "车源不存在");
		}
		return carSource;
	}
	
	
	/**
	 * 车源审核
	 * @author cat
	 * 
	 * @param checkDto
	 */
	@Override
	public ServerResponse check(CheckDto checkDto, User user) {
		Integer businessId = checkDto.getBusinessId();
		
		CarSource carSource = assertCarSourceExist(businessId);
		
		if(!CarSourceConstants.STATUS_WAIT_CHECK.equalsIgnoreCase(carSource.getStatus())) {
			throw new BusinessException(112, "审核失败：车源的状态不是待审核");
		}
		
		// 修改车源状态
		String result = checkDto.getResult();
		if(ApproveLogConstants.APPROVE_RESULT_PASS.equals(result)) {
			carSource.setStatus(CarSourceConstants.STATUS_UP);
		}else {
			carSource.setStatus(CarSourceConstants.STATUS_DOWN);
		}
		carSourceRepository.save(carSource);
		
		// 添加审核记录
		ApproveLog entity = new ApproveLog();
		entity.setApproveBy(user.getId());
		entity.setApproveContent(checkDto.getContent());
		entity.setApproveResult(checkDto.getResult());
		entity.setApproveTime(new Date());
		entity.setApproveType(ApproveLogConstants.APPROVE_TYPE_CAR_SOURCE);
		entity.setBusinessId(businessId);
		approveLogRepository.save(entity);
		
		return ServerResponse.successWithData("审核成功");
	}
	
}
