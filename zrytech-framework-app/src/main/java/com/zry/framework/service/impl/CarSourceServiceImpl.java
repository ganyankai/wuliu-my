package com.zry.framework.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
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
import com.zry.framework.dto.CommonDto;
import com.zry.framework.dto.DetailsDto;
import com.zry.framework.dto.carsource.CarRecordPlaceAddDto;
import com.zry.framework.dto.carsource.CarSourceAddDto;
import com.zry.framework.dto.carsource.CarSourceCarAddDto;
import com.zry.framework.dto.carsource.CarSourceCheckUpdateDto;
import com.zry.framework.entity.ApproveLog;
import com.zry.framework.entity.CarRecordPlace;
import com.zry.framework.entity.CarSource;
import com.zry.framework.entity.CarSourceCar;
import com.zry.framework.entity.Customer;
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
	
	
	
	/*以下为车主及车主子账号接口*/
	/////////////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * 新增车源（车主及车主子账号）
	 * @author cat
	 * 
	 * @param dto	新增车源入参
	 * @param customer	当前登录人
	 * @return
	 */
	@Override
	public ServerResponse add(CarSourceAddDto dto, Customer customer) {
		// TODO 鉴权
		
		// 新增车源
		CarSource carSource = new  CarSource();
		BeanUtils.copyProperties(dto, carSource);
		carSource.setId(null);
		// carSource.setCarOwnerId(carOwnerId); TODO
		carSource.setCreateBy(customer.getId());
		carSource.setCreateDate(new Date());
		carSource.setStatus(CarSourceConstants.STATUS_DOWN);
		carSource = carSourceRepository.save(carSource);
		Integer carSourceId = carSource.getId();
		
		// 新增车源路线
		List<CarRecordPlace> lines = new ArrayList<>();
		List<CarRecordPlaceAddDto> carRecordPlaces = dto.getCarRecordPlaces();
		for (CarRecordPlaceAddDto carRecordPlaceAddDto : carRecordPlaces) {
			CarRecordPlace carRecordPlace = new CarRecordPlace();
			BeanUtils.copyProperties(carRecordPlaceAddDto, carRecordPlace);
			carRecordPlace.setCarSourceId(carSourceId);
			carRecordPlace.setId(null);
			lines.add(carRecordPlace);
		}
		carRecordPlaceRepository.save(lines);
		
		// 新增车源的车辆，司机，压货人
		List<CarSourceCar> list = new ArrayList<>();
		List<CarSourceCarAddDto> carSourceCars = dto.getCarSourceCars();
		for (CarSourceCarAddDto carSourceCarAddDto : carSourceCars) {
			CarSourceCar carSourceCar = new CarSourceCar();
			BeanUtils.copyProperties(carSourceCarAddDto, carSourceCar);
			carSourceCar.setId(null);
			carSourceCar.setCarSourceId(carSourceId);
			list.add(carSourceCar);
		}
		carSourceCarRepository.save(list);
		
		return ServerResponse.successWithData("添加成功");
	}
	
	
	/**
	 * 修改车源基本信息需要审核的字段
	 * @author cat
	 * 
	 * @param dto	待修改字段
	 * @param customer	当前登录人
	 * @return
	 */
	@Override
	public ServerResponse updateCheck(CarSourceCheckUpdateDto dto, Customer customer) {
		// TODO 鉴权
		CarSource carSource = this.assertCarSourceExist(dto.getId());
		BeanUtils.copyProperties(dto, carSource);
		carSource.setStatus(CarSourceConstants.STATUS_DOWN);
		carSourceRepository.save(carSource);
		return ServerResponse.successWithData("修改成功");
	}
	
	
	/**
	 * 提交审核
	 * @author cat
	 * 
	 * @param dto	车源Id
	 * @param customer	当前登录人
	 * @return
	 */
	@Override
	public ServerResponse submitAudit(CommonDto dto, Customer customer) {
		// TODO 鉴权
		CarSource carSource = this.assertCarSourceExist(dto.getId());
		if(!CarSourceConstants.STATUS_DOWN.equalsIgnoreCase(carSource.getStatus())) {
			throw new BusinessException(112, "提交审核失败：仅下架状态的车源可以提交审核");
		}
		carSourceRepository.updateStatusById(dto.getId(), CarSourceConstants.STATUS_WAIT_CHECK);
		return ServerResponse.successWithData("提交审核成功");
	}
	
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
	public ServerResponse page(CarSourcePageDto dto, Integer pageNum, Integer pageSize, Customer customer){
		// TODO 搜索条件，搜索结果待定
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
	 * @param dto	车源Id
	 * @return
	 */
	@Override
	public ServerResponse details(DetailsDto dto, Customer customer) {
		// TODO 鉴权
		CarSource carSource = this.assertCarSourceExist(dto.getId());
		// 路线列表
		carSource = bindingCarRecordPlace(carSource);
		// 车主企业名称
		carSource = bindingCarOwnerName(carSource);
		// 车辆列表
		carSource = bindingCarSourceCar(carSource);
		return ServerResponse.successWithData(carSource);
	}
	
}
