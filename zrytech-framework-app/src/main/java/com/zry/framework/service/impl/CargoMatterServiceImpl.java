package com.zry.framework.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zry.framework.repository.CarCargoOwnnerRepository;
import com.zry.framework.repository.CargoMatterRepository;
import com.zry.framework.repository.CargoRepository;
import com.github.pagehelper.PageHelper;
import com.zry.framework.dto.CargoMatterPageDto;
import com.zry.framework.dto.DetailsDto;
import com.zry.framework.dto.cargomatter.CargoMatterAddDto;
import com.zry.framework.dto.cargomatter.CargoMatterUpdateDto;
import com.zry.framework.entity.Cargo;
import com.zry.framework.entity.CargoMatter;
import com.zry.framework.entity.Customer;
import com.zry.framework.mapper.CargoMatterMapper;
import com.zry.framework.service.CargoMatterService;
import com.zrytech.framework.base.entity.PageData;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;


/**
 * 报价单
 * @author cat
 *
 */
@Service
public class CargoMatterServiceImpl implements CargoMatterService {

	
	@Autowired private CargoMatterRepository cargoMatterRepository;
	
	@Autowired private CargoMatterMapper cargoMatterMapper;
	
	@Autowired private CarCargoOwnnerRepository carCargoOwnnerRepository;
	
	@Autowired private CargoRepository cargoRepository;
	
	
	
	/**
	 * 报价单分页
	 * @author cat
	 * 
	 * @param dto
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Override
	public ServerResponse page(CargoMatterPageDto dto, Integer pageNum, Integer pageSize){
		com.github.pagehelper.Page<Object> result = PageHelper.startPage(pageNum, pageSize);
		List<CargoMatter> list = cargoMatterMapper.selectSelective(dto);
		for (CargoMatter cargoMatter : list) {
			cargoMatter = bindingCarOwnerName(cargoMatter);
		}
		PageData<CargoMatter> pageData = new PageData<CargoMatter>(result.getPageSize(), result.getPageNum(), result.getTotal(), list);
		return ServerResponse.successWithData(pageData);
	}
	
	
	/**
	 * 为报价单设置车主企业名称
	 * @author cat
	 * 
	 * @param cargoMatter
	 * @return
	 */
	public CargoMatter bindingCarOwnerName(CargoMatter cargoMatter) {
		Integer createBy = cargoMatter.getCarOwnnerId();
		if(createBy != null ) {
			cargoMatter.setCarOwnerName(carCargoOwnnerRepository.findNameById(createBy));
		}
		return cargoMatter;
	}
	
	
	/**
	 * 为报价单设置货源
	 * @author cat
	 * 
	 * @param cargoMatter
	 * @return
	 */
	public CargoMatter bindingCargo(CargoMatter cargoMatter) {
		Integer cargoId = cargoMatter.getCargoId();
		if(cargoId != null ) {
			Cargo cargo = cargoRepository.findOne(cargoId);
			cargoMatter.setCargo(cargo);
		}
		return cargoMatter;
	}
	
	
	/**
	 * 报价单详情
	 * @author cat
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ServerResponse details(Integer id) {
		CargoMatter cargoMatter = cargoMatterRepository.findOne(id);
		cargoMatter = bindingCarOwnerName(cargoMatter);
		cargoMatter = bindingCargo(cargoMatter);
		return ServerResponse.successWithData(cargoMatter);
	}
	
	
	////////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * 报价单分页
	 * @author cat
	 * 
	 * @param dto
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Override
	public ServerResponse page(CargoMatterPageDto dto, Integer pageNum, Integer pageSize, Customer customer){
		// TODO 鉴权，搜索条件，展示结果待定
		com.github.pagehelper.Page<Object> result = PageHelper.startPage(pageNum, pageSize);
		List<CargoMatter> list = cargoMatterMapper.selectSelective(dto);
		for (CargoMatter cargoMatter : list) {
			cargoMatter = bindingCarOwnerName(cargoMatter);
		}
		PageData<CargoMatter> pageData = new PageData<CargoMatter>(result.getPageSize(), result.getPageNum(), result.getTotal(), list);
		return ServerResponse.successWithData(pageData);
	}
	
	
	/**
	 * 报价单详情
	 * @author cat
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ServerResponse details(DetailsDto dto, Customer customer) {
		// TODO 鉴权，展示结果待定
		CargoMatter cargoMatter = this.assertCargoMatterExist(dto.getId());
		cargoMatter = bindingCarOwnerName(cargoMatter);
		cargoMatter = bindingCargo(cargoMatter);
		return ServerResponse.successWithData(cargoMatter);
	}
	
	
	/**
	 * 报价（新增报价单）
	 * @author cat
	 * 
	 * @param dto	货源与报价
	 * @param customer	当前登录人
	 * @return
	 */
	@Override
	public ServerResponse add(CargoMatterAddDto dto, Customer customer){
		Integer carOwnerId = 1; // TODO
		// TODO 鉴权
		Cargo cargo = cargoRepository.findOne(dto.getCargoId());
		if(cargo == null) {
			throw new BusinessException(112, "报价失败：货源不存在");
		}
		
		// 一个车主针对一个货源只能有一个报价单
		List<CargoMatter> list = cargoMatterRepository.findByCargoIdAndCarOwnnerId(dto.getCargoId(), carOwnerId);
		if(list == null || !list.isEmpty()) {
			throw new BusinessException(112, "报价失败：请勿重复报价");
		}
		
		CargoMatter cargoMatter = new CargoMatter();
		BeanUtils.copyProperties(dto, cargoMatter);
		cargoMatter.setCarOwnnerId(carOwnerId);
		// cargoMatter.setStatus(status); TODO
		// TODO 创建人，缺字段
		cargoMatter.setCreateDate(new Date());
		cargoMatter.setId(null);
		
		cargoMatterRepository.save(cargoMatter);
		return ServerResponse.successWithData("报价成功");
	}
	
	
	/**
	 * 断言报价单存在
	 * @author cat
	 * 
	 * @param id	报价单Id,这个参数不能为null
	 * @return
	 */
	public CargoMatter assertCargoMatterExist(Integer id) {
		CargoMatter cargoMatter = cargoMatterRepository.findOne(id);
		if(cargoMatter == null) {
			throw new BusinessException(112, "报价单不存在");
		}
		return cargoMatter;
	}
	
	
	/**
	 * 修改报价单
	 * 
	 * @param dto
	 * @param customer	当前登录人
	 * @return
	 */
	@Override
	public ServerResponse update(CargoMatterUpdateDto dto, Customer customer){
		// TODO 鉴权
		CargoMatter cargoMatter = this.assertCargoMatterExist(dto.getId());
		BeanUtils.copyProperties(dto, cargoMatter);
		cargoMatterRepository.save(cargoMatter);
		return ServerResponse.successWithData("报价修改成功");
	}
}
