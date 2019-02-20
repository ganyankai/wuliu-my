package com.zry.framework.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zry.framework.repository.CarCargoOwnnerRepository;
import com.zry.framework.repository.CargoMatterRepository;
import com.zry.framework.repository.CargoRepository;
import com.github.pagehelper.PageHelper;
import com.zry.framework.dto.CargoMatterPageDto;
import com.zry.framework.entity.Cargo;
import com.zry.framework.entity.CargoMatter;
import com.zry.framework.mapper.CargoMatterMapper;
import com.zry.framework.service.CargoMatterService;
import com.zrytech.framework.base.entity.PageData;
import com.zrytech.framework.base.entity.ServerResponse;


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
	
	
	
}
