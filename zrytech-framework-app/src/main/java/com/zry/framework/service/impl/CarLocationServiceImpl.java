package com.zry.framework.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.zry.framework.repository.CarLocationRepository;
import com.zry.framework.dto.CarLocationPageDto;
import com.zry.framework.entity.CarLocation;
import com.zry.framework.service.CarLocationService;
import com.zry.framework.utils.PageDataUtils;
import com.zrytech.framework.base.entity.PageData;
import com.zrytech.framework.base.entity.ServerResponse;

@Service
public class CarLocationServiceImpl implements CarLocationService {

	@Autowired private CarLocationRepository carLocationRepository;
	
	@Autowired private PageDataUtils<CarLocation> pageDataUtils;
	
	
	
	/**
	 * 车辆位置分页
	 * @author cat
	 * 
	 * @param dto
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Override
	public ServerResponse page(CarLocationPageDto dto, Integer pageNum, Integer pageSize){
		CarLocation carLocation = new CarLocation();
		BeanUtils.copyProperties(dto, carLocation);
		
		Sort sort = new Sort(Direction.DESC, "createDate");
		Pageable pageable = new PageRequest(pageNum - 1, pageSize, sort);
		
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("carNo", GenericPropertyMatchers.contains());
		
		Example<CarLocation> example = Example.of(carLocation, matcher);
		
		Page<CarLocation> page = carLocationRepository.findAll(example, pageable);
		
		PageData<CarLocation> pageData = pageDataUtils.bindingData(page);
		
		return ServerResponse.successWithData(pageData);
	}
	
}
