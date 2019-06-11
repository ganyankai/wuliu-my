package com.zrytech.framework.app.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zrytech.framework.app.repository.CarCargoOwnnerRepository;
import com.zrytech.framework.app.repository.CarPersonRepository;
import com.zrytech.framework.app.repository.CarRecordPlaceRepository;
import com.zrytech.framework.app.repository.CarSourceCarRepository;
import com.zrytech.framework.app.repository.CarSourceRepository;
import com.zrytech.framework.app.repository.LogisticsCustomerRepository;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.zrytech.framework.app.constants.ApproveConstants;
import com.zrytech.framework.app.constants.ApproveLogConstants;
import com.zrytech.framework.app.constants.CarSourceConstants;
import com.zrytech.framework.app.dto.CommonDto;
import com.zrytech.framework.app.dto.approve.ApproveDto;
import com.zrytech.framework.app.dto.carrecordplace.CarRecordPlaceAddDto;
import com.zrytech.framework.app.dto.carrecordplace.CarRecordPlaceDelDto;
import com.zrytech.framework.app.dto.carrecordplace.CarRecordPlaceSaveDto;
import com.zrytech.framework.app.dto.carrecordplace.CarRecordPlaceUpdateDto;
import com.zrytech.framework.app.dto.carsource.CarSourceAddDto;
import com.zrytech.framework.app.dto.carsourcecar.CarSourceCarAddDto;
import com.zrytech.framework.app.dto.carsourcecar.CarSourceCarDelDto;
import com.zrytech.framework.app.dto.carsourcecar.CarSourceCarSaveDto;
import com.zrytech.framework.app.dto.carsourcecar.CarSourceCarUpdateDto;
import com.zrytech.framework.app.dto.carsource.CarSourceCheckUpdateDto;
import com.zrytech.framework.app.dto.carsource.CarSourceNoCheckUpdateDto;
import com.zrytech.framework.app.dto.carsource.CarSourcePageDto;
import com.zrytech.framework.app.entity.Car;
import com.zrytech.framework.app.entity.CarCargoOwnner;
import com.zrytech.framework.app.entity.CarPerson;
import com.zrytech.framework.app.entity.CarRecordPlace;
import com.zrytech.framework.app.entity.CarSource;
import com.zrytech.framework.app.entity.CarSourceCar;
import com.zrytech.framework.app.entity.Cargo;
import com.zrytech.framework.app.entity.CargoMatter;
import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.app.mapper.CarSourceMapper;
import com.zrytech.framework.app.service.ApproveLogService;
import com.zrytech.framework.app.service.CarPersonService;
import com.zrytech.framework.app.service.CarService;
import com.zrytech.framework.app.service.CarSourceService;
import com.zrytech.framework.app.service.EvaluateService;
import com.zrytech.framework.app.service.MyFocusPersonService;
import com.zrytech.framework.base.entity.PageData;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.base.util.RequestUtil;
import com.zrytech.framework.common.entity.User;

/**
 * 车源
 * 
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
	
	@Autowired
	private MyFocusPersonService myFocusPersonService;
	
	/**
	 * 为车源绑定是否已关注
	 * @author cat
	 * 
	 * @param carSource
	 * @return
	 */
	private CarSource bindFocus(CarSource carSource) {
		Integer carOwnerId = carSource.getCarOwnerId();
		// 如果当前有用户登录，且登录人是货主，则判断是否已关注车源的车主
		try {
			Customer customer = RequestUtil.getCurrentUser(Customer.class);
			if (customer != null) {
				CarCargoOwnner cargoOwner = customer.getCargoOwner();
				if (cargoOwner != null) {
					boolean focus = myFocusPersonService.isFocus(cargoOwner.getId(), carOwnerId);
					carSource.setIsFocus(focus);
				}
			}
		} catch (Exception e) {
			// nothing to do
		}
		return carSource;
	}
	
	@Override
	public PageData<CarSource> carSourcePage(CarSourcePageDto dto, Integer pageNum, Integer pageSize) {
		com.github.pagehelper.Page<Object> result = PageHelper.startPage(pageNum, pageSize);
		List<CarSource> list = carSourceMapper.selectSelective(dto);
		for (CarSource carSource : list) {
			carSource = this.bindingCarRecordPlace(carSource);
			carSource = this.bindingCarOwnerNameAndTel(carSource);
			carSource = this.bindingCustomerUserName(carSource);
			carSource = this.bindingCarSourceCar(carSource);
			carSource = this.bindFocus(carSource);
			carSource.setLevelAVG(evaluateService.levelAVG(carSource.getCarOwnerId()));
		}
		return new PageData<CarSource>(result.getPageSize(), result.getPageNum(), result.getTotal(), list);
	}
	
	@Override
	public ServerResponse adminDetails(CommonDto dto) {
		CarSource carSource = this.assertCarSourceExist(dto.getId());
		carSource = this.bindingCarSourceCar(carSource);
		carSource = this.bindingCarRecordPlace(carSource);
		carSource = this.bindingCarOwnerNameAndTel(carSource);
		carSource = this.bindingCustomerUserName(carSource);
		CarSourceCheckUpdateDto temp = JSON.parseObject(carSource.getApproveContent(), CarSourceCheckUpdateDto.class);
		carSource.setApproveContentCN(temp);
		return ServerResponse.successWithData(carSource);
	}
	
	@Autowired
	private CarPersonRepository carPersonRepository;
	
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
			for (CarSourceCar carSourceCar : carSourceCars) {
				Car car = carService.assertCarAvailable(carSourceCar.getCarId());
				carSourceCar.setCar(car);
				
				CarPerson driver = carPersonRepository.findOne(carSourceCar.getDriverId());
				carSourceCar.setDriver(driver);
				
				CarPerson supercargo = carPersonRepository.findOne(carSourceCar.getSupercargoId());
				carSourceCar.setSupercargo(supercargo);
			}
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
	private CarSource bindingCarOwnerNameAndTel(CarSource carSource) {
		Integer carOwnerId = carSource.getCarOwnerId();
		if (carOwnerId != null) {
			// carSource.setCarOwnerName(carCargoOwnnerRepository.findNameById(carOwnerId));
			CarCargoOwnner carOwner = carCargoOwnnerRepository.findOne(carOwnerId);
			carSource.setCarOwnerName(carOwner.getName());
			carSource.setCarOwnerTel(carOwner.getTel());
			carSource.setLogo(carOwner.getHeadImg());
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
	
	
	@Transactional
	public ServerResponse createCarSource(CarSourceAddDto dto, Customer customer) {
		CarCargoOwnner carOwner = customer.getCarOwner();
		// 车源车辆关系入参校验
		List<CarSourceCarAddDto> carSourceCars = dto.getCarSourceCars();
		carSourceCars = this.carSourceCarAddCheck(carSourceCars, carOwner.getId());
		Integer total = this.sumTotal(carSourceCars);
		// 新增车源基本信息
		CarSource carSource = this.addCarSource(dto, customer.getId(), carOwner.getId(), carOwner.getAvoidAudit(), total);
		// 新增车源路线
		this.addCarRecordPlace(dto.getCarRecordPlaces(), carSource.getId());
		// 新增车源的车辆、司机、压货人
		this.addCarSourceCar(carSourceCars, carSource.getId());
		return ServerResponse.successWithData("添加成功");
	}
	
	
	/**
	 * 计算车源总运输量
	 * 
	 * @param carSourceCars
	 * @return
	 */
	private Integer sumTotal(List<CarSourceCarAddDto> carSourceCars) {
		Integer total = 0;
		for (CarSourceCarAddDto carSourceCarAddDto : carSourceCars) {
			Integer freeQty = carSourceCarAddDto.getFreeQty();
			total = total + freeQty;
		}
		return total;
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
	private CarSource addCarSource(CarSourceAddDto dto, Integer createBy, Integer carOwnerId, Boolean avoidAudit, Integer total) {
		CarSource carSource = new CarSource();
		BeanUtils.copyProperties(dto, carSource);
		
		// 设置车源的运输量单位为车辆的荷载量单位，2019-5-20 14:03:56新增的。
		Integer carId = dto.getCarSourceCars().get(0).getCarId();
		Car car = carService.assertCarBelongToCurrentUser(carId, carOwnerId);
		carSource.setUnit(car.getCarUnit());
		
		carSource.setQty(total);
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
			CarSourceCar carSourceCar = new CarSourceCar();
			BeanUtils.copyProperties(carSourceCarAddDto, carSourceCar);
			carSourceCar.setId(null);
			carSourceCar.setCarSourceId(carSourceId);
			list.add(carSourceCar);
		}
		carSourceCarRepository.save(list);
	}
	
	@Deprecated
	@Transactional
	@Override
	public ServerResponse updateNeedApprove(CarSourceCheckUpdateDto dto, Customer customer) {
		CarCargoOwnner carOwner = customer.getCarOwner();
		CarSource carSource = this.assertCarSourceExist(dto.getId());
		this.assertCarSourceBolongToCurrentUser(carOwner, carSource);
		if (carSource.getApproveStatus().equalsIgnoreCase(ApproveConstants.STATUS_APPROVAL_PENDING)) {
			throw new BusinessException(112, "修改失败：不能修改待审批的车源");
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
	public ServerResponse details(CommonDto dto, Customer customer) {
		CarCargoOwnner carOwner = customer.getCarOwner();
		CarSource carSource = this.assertCarSourceExist(dto.getId());
		this.assertCarSourceBolongToCurrentUser(carOwner, carSource);
		carSource = this.bindingCarRecordPlace(carSource);
		carSource = this.bindingCarSourceCar(carSource);
		carSource = this.bindingCustomerUserName(carSource);
		carSource = this.bindingCarOwnerNameAndTel(carSource);
		CarSourceCheckUpdateDto temp = JSON.parseObject(carSource.getApproveContent(), CarSourceCheckUpdateDto.class);
		carSource.setApproveContentCN(temp);
		carSource.setLevelAVG(evaluateService.levelAVG(carSource.getCarOwnerId()));
		return ServerResponse.successWithData(carSource);
	}

	/**
	 * 判断入参：车辆、司机、压货人是否属于当前登录车主
	 * @author cat
	 * 
	 * @param carSourceCars	车辆、司机、压货人
	 * @param carOwnerId	车主Id
	 */
	private List<CarSourceCarUpdateDto> carSourceCarsCheck(List<CarSourceCarUpdateDto> carSourceCars, Integer carOwnerId) {
		for (CarSourceCarUpdateDto carSourceCarUpdateDto : carSourceCars) {
			Integer driverId = carSourceCarUpdateDto.getDriverId();
			Integer supercargoId = carSourceCarUpdateDto.getSupercargoId();
			Integer carId = carSourceCarUpdateDto.getCarId();
			Car car = carService.assertCarBelongToCurrentUser(carId, carOwnerId);
			if (driverId != null) {
				carPersonService.assertDriverBelongToCurrentUser(driverId, carOwnerId);
			} else {
				carSourceCarUpdateDto.setDriverId(car.getDriverId());
			}
			if (supercargoId != null) {
				carPersonService.assertSupercargoBelongToCurrentUser(supercargoId, carOwnerId);
			} else {
				carSourceCarUpdateDto.setSupercargoId(car.getSupercargoId());
			}
		}
		return carSourceCars;
	}
	
	/**
	 * 判断入参：车辆、司机、压货人是否属于当前登录车主，如果司机压货人为空则将其设置为车辆的司机压货人
	 * @author cat
	 * 
	 * @param carSourceCars	车辆、司机、压货人
	 * @param carOwnerId	车主Id
	 */
	private List<CarSourceCarAddDto> carSourceCarAddCheck(List<CarSourceCarAddDto> carSourceCars, Integer carOwnerId) {
		for (CarSourceCarAddDto carSourceCarAddDto : carSourceCars) {
			Integer driverId = carSourceCarAddDto.getDriverId();
			Integer supercargoId = carSourceCarAddDto.getSupercargoId();
			Integer carId = carSourceCarAddDto.getCarId();
			Car car = carService.assertCarBelongToCurrentUser(carId, carOwnerId);
			/*Integer freeQty = carSourceCarAddDto.getFreeQty();
			if (car.getCarLoad() < freeQty) {
				throw new BusinessException(112, "车辆的空闲运输量不能大于车辆荷载量");
			}*/
			// 考虑到车辆实际运输量可能比荷载量要大，此处不做限制。
			if (driverId != null) {
				carPersonService.assertDriverBelongToCurrentUser(driverId, carOwnerId);
			} else {
				carSourceCarAddDto.setDriverId(car.getDriverId());
			}
			if (supercargoId != null) {
				carPersonService.assertSupercargoBelongToCurrentUser(supercargoId, carOwnerId);
			} else {
				carSourceCarAddDto.setSupercargoId(car.getSupercargoId());
			}
		}
		return carSourceCars;
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
		List<CarSourceCarUpdateDto> carSourceCars = this.carSourceCarsCheck(dto.getCarSourceCars(), carOwner.getId());
		List<CarSourceCar> save = new ArrayList<>();
		Integer totalQty = 0;
		for (CarSourceCarUpdateDto carSourceCarUpdateDto : carSourceCars) {
			Integer id = carSourceCarUpdateDto.getId();
			if (id != null) { // 更新
				CarSourceCar carSourceCar = carSourceCarRepository.findByIdAndCarSourceId(id, carSourceId);
				if (carSourceCar == null) {
					throw new BusinessException(112, "修改失败：要修改的车辆不存在");
				}
				Integer oldFreeQty = carSourceCar.getFreeQty();
				Integer newFreeQty = carSourceCarUpdateDto.getFreeQty();
				Integer temp = newFreeQty - oldFreeQty;  // 空闲运输量在原来的基础上所增加的数值
				totalQty = totalQty + temp;
				
				BeanUtils.copyProperties(carSourceCarUpdateDto, carSourceCar);
				save.add(carSourceCar);
			} else { // 新增
				CarSourceCar carSourceCar = new CarSourceCar();
				carSourceCar.setCarSourceId(carSourceId);
				BeanUtils.copyProperties(carSourceCarUpdateDto, carSourceCar);
				save.add(carSourceCar);
				
				Integer newFreeQty = carSourceCarUpdateDto.getFreeQty();
				totalQty = totalQty + newFreeQty;
			}
		}
		carSourceCarRepository.save(save);
		carSource.setQty(carSource.getQty() + totalQty);
		carSourceRepository.save(carSource);
		return ServerResponse.successWithData("修改成功");
	}

	@Transactional
	@Override
	public ServerResponse delCarRecordPlace(CarRecordPlaceDelDto dto) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		CarCargoOwnner carOwner = customer.getCarOwner();
		Integer carSourceId = dto.getCarSourceId();
		CarSource carSource = this.assertCarSourceExist(carSourceId);
		this.assertCarSourceBolongToCurrentUser(carOwner, carSource);

		Integer carRecordPlaceId = dto.getCarRecordPlaceId();
		CarRecordPlace carRecordPlace = carRecordPlaceRepository.findByIdAndCarSourceId(carRecordPlaceId, carSourceId);
		if (carRecordPlace == null) {
			throw new BusinessException(112, "删除失败：要删除的起止地不存在");
		}

		int count = carRecordPlaceRepository.countByCarSourceId(carSourceId);
		if (count > 1) {
			carRecordPlaceRepository.delete(carRecordPlaceId);
		} else {
			throw new BusinessException(112, "删除失败：需保留最后一条起止地数据");
		}
		return ServerResponse.successWithData("删除成功");
	}

	@Transactional
	@Override
	public ServerResponse delCarSourceCar(CarSourceCarDelDto dto) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		CarCargoOwnner carOwner = customer.getCarOwner();
		Integer carSourceId = dto.getCarSourceId();
		CarSource carSource = this.assertCarSourceExist(carSourceId);
		this.assertCarSourceBolongToCurrentUser(carOwner, carSource);

		Integer carSourceCarId = dto.getCarSourceCarId();
		CarSourceCar carSourceCar = carSourceCarRepository.findByIdAndCarSourceId(carSourceCarId, carSourceId);
		if (carSourceCar == null) {
			throw new BusinessException(112, "删除失败：要删除的车辆不存在");
		}

		int count = carSourceCarRepository.countByCarSourceId(carSourceId);
		if (count > 1) {
			carSourceCarRepository.delete(carSourceCarId);
			Integer freeQty = carSourceCar.getFreeQty();
			carSource.setQty(carSource.getQty() - freeQty);
			carSourceRepository.save(carSource);
		} else {
			throw new BusinessException(112, "删除失败：需保留最后一条车辆数据");
		}

		return ServerResponse.successWithData("删除成功");
	}

	
	@Transactional
	@Override
	public ServerResponse updateNoCheck(CarSourceNoCheckUpdateDto dto) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		CarCargoOwnner carOwner = customer.getCarOwner();
		Integer carSourceId = dto.getId();
		CarSource carSource = this.assertCarSourceExist(carSourceId);
		this.assertCarSourceBolongToCurrentUser(carOwner, carSource);

		int update = carSourceMapper.updateNoCheck(carSourceId, dto.getStartDate(), dto.getIsShare(), dto.getRemark());
		if (update == 1) {
			return ServerResponse.successWithData("修改成功");
		}
		return ServerResponse.successWithData("修改失败");
	}

	@Autowired
	private EvaluateService evaluateService;
	
	
	@Transactional
	@Override
	public ServerResponse down(CommonDto dto) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		CarCargoOwnner carOwner = customer.getCarOwner();
		Integer carSourceId = dto.getId();
		CarSource carSource = this.assertCarSourceExist(carSourceId);
		this.assertCarSourceBolongToCurrentUser(carOwner, carSource);

		String status = carSource.getStatus();
		if (!CarSourceConstants.STATUS_RELEASE.equalsIgnoreCase(status)) {
			throw new BusinessException(112, "仅发布中的车源可以下架");
		}

		int update = carSourceMapper.updateStatusById(carSourceId, CarSourceConstants.STATUS_DOWN);
		if (update == 1) {
			return ServerResponse.successWithData("修改成功");
		}
		return ServerResponse.successWithData("修改失败");
	}

	@Override
	public ServerResponse openDetails(CommonDto dto) {
		CarSource carSource = this.assertCarSourceExist(dto.getId());
		if (!CarSourceConstants.STATUS_RELEASE.equalsIgnoreCase(carSource.getStatus())) {
			throw new BusinessException(112, "仅可查看发布中的车源详情");
		}
		carSource = this.bindingCarRecordPlace(carSource);
		carSource = this.bindingCarSourceCar(carSource);
		carSource = this.bindingCustomerUserName(carSource);
		carSource = this.bindingCarOwnerNameAndTel(carSource);
		carSource = this.bindFocus(carSource);
		carSource.setLevelAVG(evaluateService.levelAVG(carSource.getCarOwnerId()));
		
		return ServerResponse.successWithData(carSource);
	}
	
	
}
