package com.zrytech.framework.app.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zrytech.framework.app.repository.ApproveLogRepository;
import com.zrytech.framework.app.repository.CarCargoOwnnerRepository;
import com.zrytech.framework.app.repository.CarRecordPlaceRepository;
import com.zrytech.framework.app.repository.CarSourceCarRepository;
import com.zrytech.framework.app.repository.CarSourceRepository;
import com.github.pagehelper.PageHelper;
import com.zrytech.framework.app.constants.ApproveLogConstants;
import com.zrytech.framework.app.constants.CarSourceConstants;
import com.zrytech.framework.app.dto.CarSourcePageDto;
import com.zrytech.framework.app.dto.CheckDto;
import com.zrytech.framework.app.dto.CommonDto;
import com.zrytech.framework.app.dto.DetailsDto;
import com.zrytech.framework.app.dto.carrecordplace.CarRecordPlaceAddDto;
import com.zrytech.framework.app.dto.carrecordplace.CarRecordPlaceSaveDto;
import com.zrytech.framework.app.dto.carrecordplace.CarRecordPlaceUpdateDto;
import com.zrytech.framework.app.dto.carsource.CarSourceAddDto;
import com.zrytech.framework.app.dto.carsourcecar.CarSourceCarAddDto;
import com.zrytech.framework.app.dto.carsourcecar.CarSourceCarSaveDto;
import com.zrytech.framework.app.dto.carsourcecar.CarSourceCarUpdateDto;
import com.zrytech.framework.app.dto.carsource.CarSourceCheckUpdateDto;
import com.zrytech.framework.app.entity.ApproveLog;
import com.zrytech.framework.app.entity.CarRecordPlace;
import com.zrytech.framework.app.entity.CarSource;
import com.zrytech.framework.app.entity.CarSourceCar;
import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.app.mapper.CarSourceMapper;
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
	 * 车主及车主子账号 - 新增车源
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
	

	/**
	 * 更新路线或新增路线
	 * <p>当 {@link CarRecordPlaceUpdateDto} 的id为空时，表示在新增，id不为空时表示更新</P>
	 * @author cat
	 * 
	 * @param dto
	 * @param customer	当前登录人
	 * @return
	 */
	@Transactional
	@Override
	public ServerResponse saveLine(CarRecordPlaceSaveDto dto, Customer customer) {
		Integer carSourceId = dto.getCarSourceId();
		CarSource carSource = this.assertCarSourceExist(carSourceId);
		// TODO 鉴权
		
		List<CarRecordPlaceUpdateDto> list = dto.getCarRecordPlaces();
		if(list.isEmpty()) {
			throw new BusinessException(112, "修改路线失败：路线不存在");
		}
		
		List<CarRecordPlace> save = new ArrayList<>(); // 待更新和新增的路线
		for (CarRecordPlaceUpdateDto carRecordPlaceDto : list) {
			Integer id = carRecordPlaceDto.getId();
			if(id != null) { // 更新
				CarRecordPlace carRecordPlace = carRecordPlaceRepository.findByIdAndCarSourceId(id, carSourceId);
				if(carRecordPlace == null) {
					throw new BusinessException(112, "修改路线失败：路线不存在");
				}
				BeanUtils.copyProperties(carRecordPlaceDto, carRecordPlace);
				save.add(carRecordPlace);
			}else { // 新增
				CarRecordPlace carRecordPlace = new CarRecordPlace();
				carRecordPlace.setCarSourceId(carSourceId);
				BeanUtils.copyProperties(carRecordPlaceDto, carRecordPlace);
				save.add(carRecordPlace);	
			}
		}
		carRecordPlaceRepository.save(save);
		// TODO 路线更新之后是否需要重新审核，待定。
		return ServerResponse.successWithData("修改路线成功");
	}
	
	
	/**
	 * 车源之新增车辆或更新车辆
	 * <p>当 {@link CarSourceCarUpdateDto} 的id为空时，表示在新增，id不为空时表示更新</P>
	 * @author cat
	 * 
	 * @param dto
	 * @param customer	当前登录人
	 * @return
	 */
	@Transactional
	@Override
	public ServerResponse saveCarSourceCar(CarSourceCarSaveDto dto, Customer customer) {
		Integer carSourceId = dto.getCarSourceId();
		CarSource carSource = this.assertCarSourceExist(carSourceId);
		// TODO 鉴权
		
		List<CarSourceCarUpdateDto> list = dto.getCarSourceCars();
		if(list.isEmpty()) {
			throw new BusinessException(112, "修改车辆失败：车辆不存在");
		}
		
		List<CarSourceCar> save = new ArrayList<>();
		for (CarSourceCarUpdateDto carSourceCarUpdateDto : list) {
			Integer id = carSourceCarUpdateDto.getId();
			if(id != null){ // 更新
				CarSourceCar carSourceCar = carSourceCarRepository.findByIdAndCarSourceId(id, carSourceId);
				if(carSourceCar == null) {
					throw new BusinessException(112, "修改车辆失败：车辆不存在");
				}
				BeanUtils.copyProperties(carSourceCarUpdateDto, carSourceCar);
				save.add(carSourceCar);
			}else { // 新增
				CarSourceCar carSourceCar = new CarSourceCar();
				carSourceCar.setCarSourceId(carSourceId);
				BeanUtils.copyProperties(carSourceCarUpdateDto, carSourceCar);
				save.add(carSourceCar);
			}
		}
		// TODO 是否验证车辆Id,司机Id,压货人Id 的有效性。
		carSourceCarRepository.save(save);
		
		return ServerResponse.successWithData("修改车辆成功");
	}
	
}
