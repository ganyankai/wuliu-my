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

@Service
public class CarLocationServiceImpl implements CarLocationService {

	@Autowired
	private CarLocationRepository carLocationRepository;
	
	/**
	 * 车辆位置分页
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param dto
	 * @return
	 */
	public Page<CarLocation> page(Integer pageNumber, Integer pageSize, CarLocationPageDto dto){
		CarLocation carLocation = new CarLocation();
		BeanUtils.copyProperties(dto, carLocation);
		
		Sort sort = new Sort(Direction.DESC, "createDate");
		Pageable pageable = new PageRequest(pageNumber - 1, pageSize, sort);
		
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("carNo", GenericPropertyMatchers.contains());
		
		Example<CarLocation> example = Example.of(carLocation, matcher);
		
		return carLocationRepository.findAll(example, pageable);
	}
	
}
