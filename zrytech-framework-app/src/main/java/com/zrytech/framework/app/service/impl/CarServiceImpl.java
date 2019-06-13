package com.zrytech.framework.app.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zrytech.framework.app.repository.CarCargoOwnnerRepository;
import com.zrytech.framework.app.repository.CarPersonRepository;
import com.zrytech.framework.app.repository.CarRepository;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.zrytech.framework.app.constants.ApproveConstants;
import com.zrytech.framework.app.constants.ApproveLogConstants;
import com.zrytech.framework.app.constants.CarCargoOwnerConstants;
import com.zrytech.framework.app.constants.CarConstants;
import com.zrytech.framework.app.dto.CommonDto;
import com.zrytech.framework.app.dto.approve.ApproveDto;
import com.zrytech.framework.app.dto.car.CarAddDto;
import com.zrytech.framework.app.dto.car.CarCheckUpdateDto;
import com.zrytech.framework.app.dto.car.CarNoCheckUpdateDto;
import com.zrytech.framework.app.dto.car.CarPageDto;
import com.zrytech.framework.app.entity.Car;
import com.zrytech.framework.app.entity.CarCargoOwnner;
import com.zrytech.framework.app.entity.CarPerson;
import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.app.mapper.CarMapper;
import com.zrytech.framework.app.service.ApproveLogService;
import com.zrytech.framework.app.service.CarPersonService;
import com.zrytech.framework.app.service.CarService;
import com.zrytech.framework.base.entity.PageData;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.common.entity.User;

/**
 * 车辆
 * 
 * @author cat
 *
 */
@Service
@Transactional
public class CarServiceImpl implements CarService {
	
	@Autowired
	private CarRepository carRepository;

	@Autowired
	private CarCargoOwnnerRepository carCargoOwnnerRepository;

	@Autowired
	private CarPersonRepository carPersonRepository;

	@Autowired
	private CarMapper carMapper;

	@Autowired
	private CarPersonService carPersonService;
	
	@Autowired
	private ApproveLogService approveLogService;
	
	
	
	
	@Override
	public PageData<Car> carPage(CarPageDto dto, Integer pageNum, Integer pageSize) {
		com.github.pagehelper.Page<Object> result = PageHelper.startPage(pageNum, pageSize);
		List<Car> list = carMapper.selectSelective(dto);
		for (Car temp : list) {
			temp.setCarOwnerName(carCargoOwnnerRepository.findNameById(temp.getCarOwnerId()));
			if (temp.getDriverId() != null) {
				temp.setDriverName(carPersonRepository.findNameById(temp.getDriverId()));
			}
			if (temp.getSupercargoId() != null) {
				temp.setSupercargoName(carPersonRepository.findNameById(temp.getSupercargoId()));
			}
		}
		return new PageData<Car>(result.getPageSize(), result.getPageNum(), result.getTotal(), list);
	}

	@Override
	public ServerResponse adminDetails(CommonDto dto) {
		Car car = this.assertCarExist(dto.getId());
		car = this.bindingCarOwner(car);
		car = this.bindingDriver(car);
		car = this.bindingSupercargo(car);
		CarCheckUpdateDto temp = JSON.parseObject(car.getApproveContent(), CarCheckUpdateDto.class);
		car.setApproveContentCN(temp);
		return ServerResponse.successWithData(car);
	}

	@Override
	public ServerResponse adminApprove(ApproveDto dto, User user) {
		Car car = this.assertCarAvailable(dto.getBusinessId());
		if (!ApproveConstants.STATUS_APPROVAL_PENDING.equalsIgnoreCase(car.getApproveStatus())) {
			throw new BusinessException(112, "审批失败：车辆的状态不是待审批");
		}
		this.approve(car, ApproveConstants.RESULT_AGREE.equals(dto.getResult()));
		approveLogService.addApproveLog(dto, user.getId(), ApproveLogConstants.APPROVE_TYPE_CAR);
		return ServerResponse.successWithData("审批成功");
	}
	
	
	/**
	 * 车辆的审批
	 * @author cat
	 * 
	 * @param car
	 * @param result
	 */
	private void approve(Car car, Boolean result) {
		if (result) {
			CarCheckUpdateDto temp = JSON.parseObject(car.getApproveContent(), CarCheckUpdateDto.class);
			Integer driverId = car.getDriverId();
			Integer supercargoId = car.getSupercargoId();
			Integer id = car.getId();
			BeanUtils.copyProperties(temp, car);
			if (temp.getSupercargoId() == null) {
				car.setSupercargoId(supercargoId);
			}
			if (temp.getDriverId() == null) {
				car.setDriverId(driverId);
			}
			car.setApproveStatus(ApproveConstants.STATUS_BE_APPROVED);
			car.setId(id);
			if (car.getStatus().equalsIgnoreCase(CarConstants.CAR_STATUS_UNCERTIFIED)) {
				car.setStatus(CarConstants.CAR_STATUS_FREE);
			}
		} else {
			car.setApproveStatus(ApproveConstants.STATUS_NOT_APPROVED);
		}
		carRepository.save(car);
	}
	

	/**
	 * 为车辆绑定车主信息
	 * @author cat
	 * 
	 * @param car
	 * @return
	 */
	private Car bindingCarOwner(Car car) {
		if (car.getCarOwnerId() != null) {
			CarCargoOwnner carOwner = carCargoOwnnerRepository.findOne(car.getCarOwnerId());
			car.setCarOwnerName(carOwner.getName());
			car.setCarOwner(carOwner);
		}
		return car;
	}
	
	
	/**
	 * 为车辆绑定车司机信息
	 * @author cat
	 * 
	 * @param car
	 * @return
	 */
	private Car bindingDriver(Car car) {
		if (car.getDriverId() != null) {
			CarPerson driver = carPersonRepository.findOne(car.getDriverId());
			car.setDriver(driver);
			car.setDriverName(driver.getName());
		}
		return car;
	}
	
	
	/**
	 * 为车辆绑定车压货人信息
	 * @author cat
	 * 
	 * @param car
	 * @return
	 */
	private Car bindingSupercargo(Car car) {
		if (car.getSupercargoId() != null) {
			CarPerson supercargo = carPersonRepository.findOne(car.getSupercargoId());
			car.setSupercargo(supercargo);
			car.setSupercargoName(supercargo.getName());
		}
		return car;
	}
	
	
	/**
	 * 断言车辆存在
	 * @author cat
	 * 
	 * @param id 车辆Id
	 * @return
	 */
	private Car assertCarExist(Integer id) {
		Car car = carRepository.findOne(id);
		if (car == null) {
			throw new BusinessException(112, "车辆不存在");
		}
		return car;
	}
	
	
	/**
	 * 断言车辆未被删除
	 * @author cat
	 * 
	 * @param car 车辆
	 * @return
	 */
	private void assertCarNotDelete(Car car) {
		if (car.getIsDelete()) {
			throw new BusinessException(112, "车辆不存在");
		}
	}

	@Override
	public Car assertCarAvailable(Integer carId) {
		Car car = this.assertCarExist(carId);
		this.assertCarNotDelete(car);
		return car;
	}

	@Override
	public Car assertCarBelongToCurrentUser(Integer carId, Integer carOwnerId) {
		Car car = carRepository.findByIdAndIsDeleteAndCarOwnerId(carId, false, carOwnerId);
		if (car == null) {
			throw new BusinessException(112, "车辆不存在");
		}
		return car;
	}
	
	/**
	 * 断言车牌号未被使用
	 * @author cat
	 * 
	 * @param carNo	车牌号，参数不能为空
	 */
	private void assertCarNoNotExist(String carNo) {
		List<Car> list = carRepository.findByCarNo(carNo);
		if (list != null) {
			if (list.size() > 0) {
				throw new BusinessException(112, "车牌号为[" + carNo + "]的车辆已存在");
			}
		}
	}
	
	
	@Override
	public ServerResponse details(CommonDto dto, Customer customer) {
		Car car = this.assertCarBelongToCurrentUser(dto.getId(), customer.getCarOwner().getId());
		car = this.bindingCarOwner(car);
		car = this.bindingDriver(car);
		car = this.bindingSupercargo(car);
		return ServerResponse.successWithData(car);
	}

	private void personCheck(CarCargoOwnner carOwner) {
		if(carOwner.getCustomerType().equalsIgnoreCase(CarCargoOwnerConstants.CUSTOMER_TYPE_PERSON)) {
			List<Car> list = carRepository.findByIsDeleteAndCarOwnerId(false, carOwner.getId());
			if(!list.isEmpty()) {
				throw new BusinessException(112, "个人车主最多只能添加一辆车");
			}
		}
	}
	
	@Transactional
	@Override
	public ServerResponse add(CarAddDto dto, Customer customer) {
		CarCargoOwnner carOwner = customer.getCarOwner();
		this.personCheck(carOwner);
		
		this.assertCarNoNotExist(dto.getCarNo()); // 车牌号全局唯一
		
		if (dto.getDriverId() != null) {
			carPersonService.assertDriverBelongToCurrentUser(dto.getDriverId(), carOwner.getId());
		}
		if (dto.getSupercargoId() != null) {
			carPersonService.assertSupercargoBelongToCurrentUser(dto.getSupercargoId(), carOwner.getId());
		}
		
		Car car = new Car();
		BeanUtils.copyProperties(dto, car);
		car.setCreateBy(customer.getId());
		car.setCarOwnerId(carOwner.getId());
		car.setStatus(CarConstants.CAR_STATUS_UNCERTIFIED);
		car.setApproveStatus(ApproveConstants.STATUS_APPROVAL_PENDING);
		CarCheckUpdateDto temp = new CarCheckUpdateDto();
		BeanUtils.copyProperties(dto, temp);
		car.setApproveContent(JSON.toJSONString(temp));
		car.setId(null);
		car.setCreateDate(new Date());
		car.setIsDelete(false);
		if (!dto.getMulStore()) { // 如果不分仓，默认仓位数为【1】
			car.setStoreQty(1);
		}
		carRepository.save(car);
		return ServerResponse.successWithData("添加成功");
	}

	@Transactional
	@Override
	public ServerResponse updateNoCheck(CarNoCheckUpdateDto dto, Customer customer) {
		CarCargoOwnner carOwner = customer.getCarOwner();
		Integer carOwnerId = carOwner.getId();
		Car car = this.assertCarBelongToCurrentUser(dto.getId(), carOwnerId);
		if (dto.getDriverId() != null) {
			carPersonService.assertDriverBelongToCurrentUser(dto.getDriverId(), carOwnerId);
		}
		if (dto.getSupercargoId() != null) {
			carPersonService.assertSupercargoBelongToCurrentUser(dto.getSupercargoId(), carOwnerId);
		}
		BeanUtils.copyProperties(dto, car);
		carRepository.save(car);
		return ServerResponse.successWithData("修改成功");
	}

	@Transactional
	@Override
	public ServerResponse updateCheck(CarCheckUpdateDto dto, Customer customer) {
		Car car = this.assertCarBelongToCurrentUser(dto.getId(), customer.getCarOwner().getId());
		if (car.getApproveStatus().equalsIgnoreCase(ApproveConstants.STATUS_APPROVAL_PENDING)) {
			throw new BusinessException(112, "修改失败：不能修改待审批的车辆");
		}
		if (!dto.getMulStore()) { // 如果不分仓，默认仓位数为【1】
			dto.setStoreQty(1);
		}
		
		if (dto.getDriverId() != null) {
			carPersonService.assertDriverBelongToCurrentUser(dto.getDriverId(), customer.getCarOwner().getId());
		}
		if (dto.getSupercargoId() != null) {
			carPersonService.assertSupercargoBelongToCurrentUser(dto.getSupercargoId(), customer.getCarOwner().getId());
		}
		CarCheckUpdateDto temp = new CarCheckUpdateDto();
		BeanUtils.copyProperties(dto, temp);

		car.setCarNo(dto.getCarNo());
		car.setCarLoad(dto.getCarLoad());
		car.setCarUnit(dto.getCarUnit());
		car.setCarType(dto.getCarType());
		car.setMulStore(dto.getMulStore());
		car.setStoreQty(dto.getStoreQty());

		car.setApproveStatus(ApproveConstants.STATUS_APPROVAL_PENDING);
		car.setApproveContent(JSON.toJSONString(temp));
		carRepository.save(car);
		return ServerResponse.successWithData("修改成功");
	}

	@Transactional
	@Override
	public ServerResponse delete(CommonDto dto, Customer customer) {
		Car car = this.assertCarBelongToCurrentUser(dto.getId(), customer.getCarOwner().getId());
		if (!car.getStatus().equalsIgnoreCase(CarConstants.CAR_STATUS_UNCERTIFIED)) {
			throw new BusinessException(112, "删除失败：仅可删除未认证的车辆");
		}
		carRepository.deleteCarById(dto.getId());
		return ServerResponse.successWithData("删除成功");
	}

	@Override
	public void assertCarCertified(Car car) {
		if(CarConstants.CAR_STATUS_UNCERTIFIED.equalsIgnoreCase(car.getStatus())) {
			throw new BusinessException(112, "车辆未认证");
		}
	}
	
}
