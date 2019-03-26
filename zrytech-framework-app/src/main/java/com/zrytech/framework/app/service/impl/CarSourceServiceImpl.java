package com.zrytech.framework.app.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zrytech.framework.app.repository.CarCargoOwnnerRepository;
import com.zrytech.framework.app.repository.CarRecordPlaceRepository;
import com.zrytech.framework.app.repository.CarSourceCarRepository;
import com.zrytech.framework.app.repository.CarSourceRepository;
import com.zrytech.framework.app.repository.LogisticsCustomerRepository;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.zrytech.framework.app.constants.ApproveConstants;
import com.zrytech.framework.app.constants.ApproveLogConstants;
import com.zrytech.framework.app.constants.CarSourceConstants;
import com.zrytech.framework.app.dto.DetailsDto;
import com.zrytech.framework.app.dto.approve.ApproveDto;
import com.zrytech.framework.app.dto.carrecordplace.CarRecordPlaceAddDto;
import com.zrytech.framework.app.dto.carrecordplace.CarRecordPlaceSaveDto;
import com.zrytech.framework.app.dto.carrecordplace.CarRecordPlaceUpdateDto;
import com.zrytech.framework.app.dto.carsource.CarSourceAddDto;
import com.zrytech.framework.app.dto.carsourcecar.CarSourceCarAddDto;
import com.zrytech.framework.app.dto.carsourcecar.CarSourceCarSaveDto;
import com.zrytech.framework.app.dto.carsourcecar.CarSourceCarUpdateDto;
import com.zrytech.framework.app.dto.carsource.CarSourceCheckUpdateDto;
import com.zrytech.framework.app.dto.carsource.CarSourcePageDto;
import com.zrytech.framework.app.entity.CarCargoOwnner;
import com.zrytech.framework.app.entity.CarRecordPlace;
import com.zrytech.framework.app.entity.CarSource;
import com.zrytech.framework.app.entity.CarSourceCar;
import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.app.mapper.CarSourceMapper;
import com.zrytech.framework.app.service.ApproveLogService;
import com.zrytech.framework.app.service.CarPersonService;
import com.zrytech.framework.app.service.CarService;
import com.zrytech.framework.app.service.CarSourceService;
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
	
	@Autowired
	private CarSourceRepository carSourceRepository;

	@Autowired
	private CarRecordPlaceRepository carRecordPlaceRepository;

	@Autowired
	private CarSourceMapper carSourceMapper;

	@Autowired
	private CarCargoOwnnerRepository carCargoOwnnerRepository;

	@Autowired
	private CarSourceCarRepository carSourceCarRepository;

	@Autowired
	private LogisticsCustomerRepository logisticsCustomerRepository;

	@Autowired
	private CarService carService;

	@Autowired
	private CarPersonService carPersonService;

	@Autowired
	private ApproveLogService approveLogService;
	
	
	
	@Override
	public PageData<CarSource> carSourcePage(CarSourcePageDto dto, Integer pageNum, Integer pageSize) {
		com.github.pagehelper.Page<Object> result = PageHelper.startPage(pageNum, pageSize);
		List<CarSource> list = carSourceMapper.selectSelective(dto);
		for (CarSource carSource : list) {
			carSource = this.bindingCarRecordPlace(carSource);
			carSource = this.bindingCarOwnerName(carSource);
			carSource = this.bindingCustomerUserName(carSource);
		}
		return new PageData<CarSource>(result.getPageSize(), result.getPageNum(), result.getTotal(), list);
	}
	
	@Override
	public ServerResponse adminDetails(DetailsDto dto) {
		CarSource carSource = this.assertCarSourceExist(dto.getId());
		carSource = this.bindingCarSourceCar(carSource);
		carSource = this.bindingCarRecordPlace(carSource);
		carSource = this.bindingCarOwnerName(carSource);
		carSource = this.bindingCustomerUserName(carSource);
		CarSourceCheckUpdateDto temp = JSON.parseObject(carSource.getApproveContent(), CarSourceCheckUpdateDto.class);
		carSource.setApproveContentCN(temp);
		return ServerResponse.successWithData(carSource);
	}
	
	/**
	 * 为车源设置车辆列表
	 * @author cat
	 * 
	 * @param carSource
	 * @return
	 */
	private CarSource bindingCarSourceCar(CarSource carSource) {
		Integer carSourceId = carSource.getId();
		if (carSourceId != null) {
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
	private CarSource bindingCarRecordPlace(CarSource carSource) {
		Integer carSourceId = carSource.getId();
		if (carSourceId != null) {
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
	private CarSource bindingCarOwnerName(CarSource carSource) {
		Integer carOwnerId = carSource.getCarOwnerId();
		if (carOwnerId != null) {
			carSource.setCarOwnerName(carCargoOwnnerRepository.findNameById(carOwnerId));
		}
		return carSource;
	}
	
	/**
	 * 为车源绑定创建者名称
	 * @author cat
	 * 
	 * @param carSource
	 * @return
	 */
	private CarSource bindingCustomerUserName(CarSource carSource) {
		Integer createBy = carSource.getCreateBy();
		if (createBy != null) {
			carSource.setUserName(logisticsCustomerRepository.findUserNameById(createBy));
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
	private CarSource assertCarSourceExist(Integer carSourceId) {
		CarSource carSource = carSourceRepository.findOne(carSourceId);
		if (carSource == null) {
			throw new BusinessException(112, "车源不存在");
		}
		return carSource;
	}
	
	/**
	 * 断言车源归于与当前登录人
	 * @author cat
	 * 
	 * @param carOwner	当前登录人
	 * @param carSource	车源
	 */
	private void assertCarSourceBolongToCurrentUser(CarCargoOwnner carOwner, CarSource carSource) {
		if (!carOwner.getId().equals(carSource.getCarOwnerId())) {
			throw new BusinessException(112, "车源不存在");
		}
	}
	
	/**
	 * 管理员 - 车源审批
	 * @author cat
	 * 
	 * @param dto
	 * @param user
	 * @return
	 */
	public ServerResponse adminApprove(ApproveDto dto, User user) {
		CarSource carSource = this.assertCarSourceExist(dto.getBusinessId());
		if (!ApproveConstants.STATUS_APPROVAL_PENDING.equalsIgnoreCase(carSource.getApproveStatus())) {
			throw new BusinessException(112, "审批失败：车源的状态不是待审批");
		}
		this.approve(carSource, ApproveConstants.RESULT_AGREE.equals(dto.getResult()));
		approveLogService.addApproveLog(dto, user.getId(), ApproveLogConstants.APPROVE_TYPE_CAR_SOURCE);
		return ServerResponse.successWithData("审批成功");
	}
	

	/**
	 * 车源的审批
	 * @author cat
	 * 
	 * @param carSource
	 * @param result
	 */
	private void approve(CarSource carSource, Boolean result) {
		Integer id = carSource.getId();
		if (result) {
			CarSourceCheckUpdateDto temp = JSON.parseObject(carSource.getApproveContent(),
					CarSourceCheckUpdateDto.class);
			BeanUtils.copyProperties(temp, carSource);
			carSource.setApproveStatus(ApproveConstants.STATUS_BE_APPROVED);
			carSource.setId(id);
			if (carSource.getStatus().equalsIgnoreCase(CarSourceConstants.STATUS_UNCERTIFIED)) {
				carSource.setStatus(CarSourceConstants.STATUS_RELEASE);
			}
		} else {
			carSource.setApproveStatus(ApproveConstants.STATUS_NOT_APPROVED);
		}
		carSourceRepository.save(carSource);
	}
	
	
	
	/*以下为车主及车主子账号接口*/
	
	
	/**
	 * 车主及车主子账号 - 新增车源
	 * @author cat
	 * 
	 * @param dto	新增车源入参
	 * @param customer	当前登录人（车主）
	 * @return
	 */
	@Transactional
	public ServerResponse createCarSource(CarSourceAddDto dto, Customer customer) {
		CarCargoOwnner carOwner = customer.getCarOwner();
		// 车源车辆关系入参校验
		List<CarSourceCarAddDto> carSourceCars = dto.getCarSourceCars();
		if (carSourceCars != null && carSourceCars.size() > 0) {
			this.carSourceCarAddCheck(carSourceCars, carOwner.getId());
		}
		// 新增车源基本信息
		CarSource carSource = this.addCarSource(dto, customer.getId(), carOwner.getId(), carOwner.getAvoidAudit());
		// 新增车源路线
		this.addCarRecordPlace(dto.getCarRecordPlaces(), carSource.getId());
		// 新增车源的车辆、司机、压货人
		if (carSourceCars != null && carSourceCars.size() > 0) {
			this.addCarSourceCar(carSourceCars, carSource.getId());
		}
		return ServerResponse.successWithData("添加成功");
	}
	
	/**
	 * 新增车源基本信息
	 * @author cat
	 * 
	 * @param dto	车源基本信息
	 * @param createBy	创建人
	 * @param carOwnerId	车主Id
	 * @param avoidAudit	车主是否免审核
	 * @return	新增的车源
	 */
	private CarSource addCarSource(CarSourceAddDto dto, Integer createBy, Integer carOwnerId, Boolean avoidAudit) {
		CarSource carSource = new CarSource();
		BeanUtils.copyProperties(dto, carSource);
		carSource.setId(null);
		carSource.setCarOwnerId(carOwnerId);
		carSource.setCreateBy(createBy);
		carSource.setCreateDate(new Date());
		if (avoidAudit) {
			carSource.setStatus(CarSourceConstants.STATUS_RELEASE);
			carSource.setApproveStatus(ApproveConstants.STATUS_BE_APPROVED);
		} else {
			carSource.setStatus(CarSourceConstants.STATUS_UNCERTIFIED);
			carSource.setApproveStatus(ApproveConstants.STATUS_APPROVAL_PENDING);
		}
		CarSourceCheckUpdateDto temp = new CarSourceCheckUpdateDto();
		BeanUtils.copyProperties(dto, temp);
		temp.setId(null);
		carSource.setApproveContent(JSON.toJSONString(temp));
		carSourceRepository.save(carSource);
		return carSource;
	}
	
	/**
	 * 新增车源路线
	 * @author cat
	 */
	private void addCarRecordPlace(List<CarRecordPlaceAddDto> carRecordPlaces, Integer carSourceId) {
		List<CarRecordPlace> lines = new ArrayList<>();
		for (CarRecordPlaceAddDto carRecordPlaceAddDto : carRecordPlaces) {
			CarRecordPlace carRecordPlace = new CarRecordPlace();
			BeanUtils.copyProperties(carRecordPlaceAddDto, carRecordPlace);
			carRecordPlace.setCarSourceId(carSourceId);
			carRecordPlace.setId(null);
			lines.add(carRecordPlace);
		}
		carRecordPlaceRepository.save(lines);
	}
	
	/**
	 * 新增车源的车辆、司机、压货人
	 * @author cat
	 */
	private void addCarSourceCar(List<CarSourceCarAddDto> carSourceCars, Integer carSourceId) {
		List<CarSourceCar> list = new ArrayList<>();
		for (CarSourceCarAddDto carSourceCarAddDto : carSourceCars) {
			Integer carId = carSourceCarAddDto.getCarId();
			Integer driverId = carSourceCarAddDto.getDriverId();
			Integer supercargoId = carSourceCarAddDto.getSupercargoId();
			if (driverId == null && supercargoId == null && carId == null) {
				continue;
			}
			CarSourceCar carSourceCar = new CarSourceCar();
			BeanUtils.copyProperties(carSourceCarAddDto, carSourceCar);
			carSourceCar.setId(null);
			carSourceCar.setCarSourceId(carSourceId);
			list.add(carSourceCar);
		}
		if (list.size() > 0) {
			carSourceCarRepository.save(list);
		}
	}
	
	@Override
	public ServerResponse updateNeedApprove(CarSourceCheckUpdateDto dto, Customer customer) {
		CarCargoOwnner carOwner = customer.getCarOwner();
		CarSource carSource = this.assertCarSourceExist(dto.getId());
		this.assertCarSourceBolongToCurrentUser(carOwner, carSource);
		if (carSource.getApproveStatus().equalsIgnoreCase(ApproveConstants.STATUS_APPROVAL_PENDING)) {
			throw new BusinessException(112, "修改失败：不能修改待审核的车源");
		}
		if (carOwner.getAvoidAudit()) {
			BeanUtils.copyProperties(dto, carSource);
			carSource.setApproveStatus(ApproveConstants.STATUS_BE_APPROVED);
			if (carSource.getStatus().equalsIgnoreCase(CarSourceConstants.STATUS_UNCERTIFIED)) {
				carSource.setStatus(CarSourceConstants.STATUS_RELEASE);
			}
		} else {
			carSource.setApproveStatus(ApproveConstants.STATUS_APPROVAL_PENDING);
		}
		dto.setId(null);
		carSource.setApproveContent(JSON.toJSONString(dto));
		carSourceRepository.save(carSource);
		return ServerResponse.successWithData("修改成功");
	}

	@Override
	public ServerResponse details(DetailsDto dto, Customer customer) {
		CarCargoOwnner carOwner = customer.getCarOwner();
		CarSource carSource = this.assertCarSourceExist(dto.getId());
		this.assertCarSourceBolongToCurrentUser(carOwner, carSource);
		carSource = this.bindingCarRecordPlace(carSource);
		carSource = this.bindingCarSourceCar(carSource);
		carSource = this.bindingCustomerUserName(carSource);
		carSource = this.bindingCarOwnerName(carSource);
		CarSourceCheckUpdateDto temp = JSON.parseObject(carSource.getApproveContent(), CarSourceCheckUpdateDto.class);
		carSource.setApproveContentCN(temp);
		return ServerResponse.successWithData(carSource);
	}

	/**
	 * 判断入参：车辆、司机、压货人是否属于当前登录车主
	 * @author cat
	 * 
	 * @param carSourceCars	车辆、司机、压货人
	 * @param carOwnerId	车主Id
	 */
	private void carSourceCarsCheck(List<CarSourceCarUpdateDto> carSourceCars, Integer carOwnerId) {
		for (CarSourceCarUpdateDto carSourceCarUpdateDto : carSourceCars) {
			Integer driverId = carSourceCarUpdateDto.getDriverId();
			Integer supercargoId = carSourceCarUpdateDto.getSupercargoId();
			Integer carId = carSourceCarUpdateDto.getCarId();
			if (driverId == null && supercargoId == null && carId == null) {
				throw new BusinessException(112, "车辆，司机，压货人不能同时为空");
			}
			if (driverId != null) {
				carPersonService.assertDriverBelongToCurrentUser(driverId, carOwnerId);
			}
			if (supercargoId != null) {
				carPersonService.assertSupercargoBelongToCurrentUser(supercargoId, carOwnerId);
			}
			if (carId != null) {
				carService.assertCarBelongToCurrentUser(carId, carOwnerId);
			}
		}
	}
	
	/**
	 * 判断入参：车辆、司机、压货人是否属于当前登录车主
	 * @author cat
	 * 
	 * @param carSourceCars	车辆、司机、压货人
	 * @param carOwnerId	车主Id
	 */
	private void carSourceCarAddCheck(List<CarSourceCarAddDto> carSourceCars, Integer carOwnerId) {
		for (CarSourceCarAddDto carSourceCarAddDto : carSourceCars) {
			Integer driverId = carSourceCarAddDto.getDriverId();
			Integer supercargoId = carSourceCarAddDto.getSupercargoId();
			Integer carId = carSourceCarAddDto.getCarId();
			if (driverId != null) {
				carPersonService.assertDriverBelongToCurrentUser(driverId, carOwnerId);
			}
			if (supercargoId != null) {
				carPersonService.assertSupercargoBelongToCurrentUser(supercargoId, carOwnerId);
			}
			if (carId != null) {
				carService.assertCarBelongToCurrentUser(carId, carOwnerId);
			}
		}
	}
	
	@Transactional
	@Override
	public ServerResponse addOrUpdateCarRecordPlace(CarRecordPlaceSaveDto dto, Customer customer) {
		CarCargoOwnner carOwner = customer.getCarOwner();
		Integer carSourceId = dto.getCarSourceId();
		CarSource carSource = this.assertCarSourceExist(dto.getCarSourceId());
		this.assertCarSourceBolongToCurrentUser(carOwner, carSource);
		// 新增或更新起止地
		List<CarRecordPlace> save = new ArrayList<>(); // 待更新和待新增的起止地
		for (CarRecordPlaceUpdateDto carRecordPlaceDto : dto.getCarRecordPlaces()) {
			Integer id = carRecordPlaceDto.getId();
			if (id != null) { // 更新
				CarRecordPlace carRecordPlace = carRecordPlaceRepository.findByIdAndCarSourceId(id, carSourceId);
				if (carRecordPlace == null) {
					throw new BusinessException(112, "修改失败：要修改的起止地不存在");
				}
				BeanUtils.copyProperties(carRecordPlaceDto, carRecordPlace);
				save.add(carRecordPlace);
			} else { // 新增
				CarRecordPlace carRecordPlace = new CarRecordPlace();
				carRecordPlace.setCarSourceId(carSourceId);
				BeanUtils.copyProperties(carRecordPlaceDto, carRecordPlace);
				save.add(carRecordPlace);
			}
		}
		carRecordPlaceRepository.save(save);
		return ServerResponse.successWithData("修改成功");
	}
	
	@Transactional
	@Override
	public ServerResponse saveCarSourceCar(CarSourceCarSaveDto dto, Customer customer) {
		CarCargoOwnner carOwner = customer.getCarOwner();
		Integer carSourceId = dto.getCarSourceId();
		CarSource carSource = this.assertCarSourceExist(carSourceId);
		this.assertCarSourceBolongToCurrentUser(carOwner, carSource);
		this.carSourceCarsCheck(dto.getCarSourceCars(), carOwner.getId());
		List<CarSourceCar> save = new ArrayList<>();
		for (CarSourceCarUpdateDto carSourceCarUpdateDto : dto.getCarSourceCars()) {
			Integer id = carSourceCarUpdateDto.getId();
			if (id != null) { // 更新
				CarSourceCar carSourceCar = carSourceCarRepository.findByIdAndCarSourceId(id, carSourceId);
				if (carSourceCar == null) {
					throw new BusinessException(112, "修改失败：要修改的车辆不存在");
				}
				BeanUtils.copyProperties(carSourceCarUpdateDto, carSourceCar);
				save.add(carSourceCar);
			} else { // 新增
				CarSourceCar carSourceCar = new CarSourceCar();
				carSourceCar.setCarSourceId(carSourceId);
				BeanUtils.copyProperties(carSourceCarUpdateDto, carSourceCar);
				save.add(carSourceCar);
			}
		}
		carSourceCarRepository.save(save);
		return ServerResponse.successWithData("修改成功");
	}
	
	
}
