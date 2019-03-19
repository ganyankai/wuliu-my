package com.zrytech.framework.app.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zrytech.framework.app.repository.ApproveLogRepository;
import com.zrytech.framework.app.repository.CarCargoOwnnerRepository;
import com.zrytech.framework.app.repository.CarPersonRepository;
import com.zrytech.framework.app.repository.CarRepository;
import com.github.pagehelper.PageHelper;
import com.zrytech.framework.app.constants.ApproveConstants;
import com.zrytech.framework.app.constants.ApproveLogConstants;
import com.zrytech.framework.app.constants.CarConstants;
import com.zrytech.framework.app.dto.CheckDto;
import com.zrytech.framework.app.dto.CommonDto;
import com.zrytech.framework.app.dto.DeleteDto;
import com.zrytech.framework.app.dto.DetailsDto;
import com.zrytech.framework.app.dto.car.CarAddDto;
import com.zrytech.framework.app.dto.car.CarCheckUpdateDto;
import com.zrytech.framework.app.dto.car.CarNoCheckUpdateDto;
import com.zrytech.framework.app.dto.car.CarOwnerCarPageDto;
import com.zrytech.framework.app.dto.car.CarPageDto;
import com.zrytech.framework.app.entity.ApproveLog;
import com.zrytech.framework.app.entity.Car;
import com.zrytech.framework.app.entity.CarCargoOwnner;
import com.zrytech.framework.app.entity.CarPerson;
import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.app.mapper.CarMapper;
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
	
	@Autowired private CarRepository carRepository;
	
	@Autowired private CarCargoOwnnerRepository carCargoOwnnerRepository;
	
	@Autowired private CarPersonRepository carPersonRepository;
	
	@Autowired private ApproveLogRepository approveLogRepository;
	
	@Autowired private CarMapper carMapper;
	
	@Autowired private CarPersonService carPersonService;
	
	
	@Override
	public ServerResponse page(CarPageDto dto, Integer pageNum, Integer pageSize){
		com.github.pagehelper.Page<Object> result = PageHelper.startPage(pageNum, pageSize);
		List<Car> list = carMapper.selectSelective(dto);
		for (Car temp : list) {
			temp.setCarOwnerName(carCargoOwnnerRepository.findNameById(temp.getCreateBy()));
			if(temp.getDriverId() != null) {
				temp.setDriverName(carPersonRepository.findNameById(temp.getDriverId()));
			}
			if(temp.getSupercargoId() != null) {
				temp.setSupercargoName(carPersonRepository.findNameById(temp.getSupercargoId()));
			}
		}
		PageData<Car> pageData = new PageData<Car>(result.getPageSize(), result.getPageNum(), result.getTotal(), list);
		return ServerResponse.successWithData(pageData);
	}
	
	
	@Override
	public ServerResponse details(DetailsDto dto) {
		Car car = this.assertCarExist(dto.getId());
		car = bindingCarOwner(car);		// 车主
		car = bindingDriver(car);		// 司机
		car = bindingSupercargo(car);	// 压货人
		return ServerResponse.successWithData(car);
	}
	
	
	@Override
	@Transactional
	public ServerResponse check(CheckDto checkDto, User user) {
		Integer businessId = checkDto.getBusinessId();
		Car car = this.assertCarAvailable(businessId);
		if(!CarConstants.CAR_STATUS_WAIT_CHECK.equalsIgnoreCase(car.getStatus())) {
			throw new BusinessException(112, "审核失败：车辆状态不是待审核");
		}
		
		// 修改车辆状态
		String result = checkDto.getResult();
		if(ApproveConstants.RESULT_AGREE.equals(result)) {
			car.setStatus(CarConstants.CAR_STATUS_UP);
		}else {
			car.setStatus(CarConstants.CAR_STATUS_DOWN);
		}
		carRepository.save(car);
		
		// 添加审核记录
		ApproveLog entity = new ApproveLog();
		entity.setApproveBy(user.getId());
		entity.setApproveContent(checkDto.getContent());
		entity.setApproveResult(checkDto.getResult());
		entity.setApproveTime(new Date());
		entity.setApproveType(ApproveLogConstants.APPROVE_TYPE_CAR);
		entity.setBusinessId(businessId);
		approveLogRepository.save(entity);
		
		return ServerResponse.successWithData("审核成功");
	}
	

	/**
	 * 为车辆绑定车主信息
	 * @author cat
	 * 
	 * @param car
	 * @return
	 */
	private Car bindingCarOwner(Car car) {
		if(car.getCarOwnerId() != null) {
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
		if(car.getDriverId() != null) {
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
		if(car.getSupercargoId() != null) {
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
		if(car == null) {
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
		if(car.getIsDelete()){
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
		if(car == null) {
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
		if(list != null) {
			if(list.size() > 0) {
				throw new BusinessException(112, "车牌号为[" + carNo + "]的车辆已存在");
			}
		}
	}
	
	
	
	
	
	
	/*以下为车主及车主子账号接口*/
	
	
	
	
	
	

	@Override
	public ServerResponse page(CarOwnerCarPageDto dto, Integer pageNum, Integer pageSize, Customer customer) {
		com.github.pagehelper.Page<Object> result = PageHelper.startPage(pageNum, pageSize);
		List<Car> list = carMapper.carOwnerSelectSelective(dto, customer.getCarOwner().getId());
		for (Car temp : list) {
			if(temp.getDriverId() != null) {
				temp.setDriverName(carPersonRepository.findNameById(temp.getDriverId()));
			}
			if(temp.getSupercargoId() != null) {
				temp.setSupercargoName(carPersonRepository.findNameById(temp.getSupercargoId()));
			}
		}
		PageData<Car> pageData = new PageData<Car>(result.getPageSize(), result.getPageNum(), result.getTotal(), list);
		return ServerResponse.successWithData(pageData);
	}
	
	
	@Override
	public ServerResponse details(DetailsDto dto, Customer customer) {
		Car car = this.assertCarBelongToCurrentUser(dto.getId(), customer.getCarOwner().getId());
		car = bindingCarOwner(car); // 车主
		car = bindingDriver(car); // 司机
		car = bindingSupercargo(car); // 压货人
		return ServerResponse.successWithData(car);
	}
	
	
	@Transactional
	@Override
	public ServerResponse add(CarAddDto dto, Customer customer) {
		this.assertCarNoNotExist(dto.getCarNo()); // 车牌号全局唯一
		CarCargoOwnner carOwner = customer.getCarOwner();
		Car car = new Car();
		BeanUtils.copyProperties(dto, car);
		car.setCreateBy(customer.getId());
		car.setCarOwnerId(carOwner.getId());
		if(carOwner.getAvoidAudit()) { // 免审核
			car.setStatus(CarConstants.CAR_STATUS_UP);
		}else {
			car.setStatus(CarConstants.CAR_STATUS_WAIT_CHECK);
		}
		car.setId(null);
		car.setCreateDate(new Date());
		car.setIsDelete(false);
		if(!dto.getMulStore()) { // 如果不分仓，默认仓位数为【1】
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
		// 司机压货人检验
		if(dto.getDriverId() != null) {
			carPersonService.assertDriverBelongToCurrentUser(dto.getDriverId(), carOwnerId);
		}
		if(dto.getSupercargoId() != null) {
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
		BeanUtils.copyProperties(dto, car);
		if(!dto.getMulStore()) { // 如果不分仓，默认仓位数为【1】
			car.setStoreQty(1);
		}
		if(customer.getCarOwner().getAvoidAudit()) { // 免审核
			// car.setStatus(CarConstants.CAR_STATUS_DOWN); 保留原状态
		}else {
			car.setStatus(CarConstants.CAR_STATUS_WAIT_CHECK);
		}
		carRepository.save(car);
		return ServerResponse.successWithData("修改成功");
	}
	
	
	@Transactional
	@Override
	public ServerResponse delete(DeleteDto dto, Customer customer) {
		this.assertCarBelongToCurrentUser(dto.getId(), customer.getCarOwner().getId());
		// TODO 判断当前车辆是否可以删除（暂未确认哪些条件下车辆可以删除）
		// TODO 车辆删除日志（暂未确认是否需要日志）
		carRepository.deleteCarById(dto.getId());
		return ServerResponse.successWithData("删除成功");
	}
	
	
	@Transactional
	@Override
	public ServerResponse submitAudit(CommonDto dto, Customer customer) {
		Integer carId = dto.getId();
		Car car = this.assertCarBelongToCurrentUser(carId, customer.getCarOwner().getId());
		if (!CarConstants.CAR_STATUS_DOWN.equalsIgnoreCase(car.getStatus())) {
			throw new BusinessException(112, "提交审核失败：仅下架状态的车辆可以提交审核");
		}
		if (customer.getCarOwner().getAvoidAudit()) { // 免审核
			carRepository.updateStatusById(carId, CarConstants.CAR_STATUS_UP);
		} else {
			carRepository.updateStatusById(carId, CarConstants.CAR_STATUS_WAIT_CHECK);
		}
		return ServerResponse.successWithData("提交审核成功");
	}
	
	
	
}
