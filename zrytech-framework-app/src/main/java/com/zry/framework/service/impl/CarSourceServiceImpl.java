package com.zry.framework.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.zry.framework.repository.CarRecordPlaceRepository;
import com.zry.framework.repository.CarSourceRepository;
import com.zry.framework.dto.CarSourcePageDto;
import com.zry.framework.dto.CheckDto;
import com.zry.framework.entity.BillLocation;
import com.zry.framework.entity.CarRecordPlace;
import com.zry.framework.entity.CarSource;
import com.zry.framework.service.CarSourceService;

/**
 * 车源
 * @author cat
 *
 */
@Service
public class CarSourceServiceImpl implements CarSourceService {
	
	@Autowired
	private CarSourceRepository carSourceRepository;
	
	@Autowired
	private CarRecordPlaceRepository carRecordPlaceRepository;
	
	
	public Page<CarSource> page(Integer pageNumber, Integer pageSize, CarSourcePageDto dto){
		
		CarSource carSource = new CarSource();
		BeanUtils.copyProperties(dto, carSource);
		
		Sort sort = new Sort(Direction.DESC, "createDate");
		Pageable pageable = new PageRequest(pageNumber - 1, pageSize, sort);
		
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("carType", GenericPropertyMatchers.contains());
		
		Example<CarSource> example = Example.of(carSource, matcher);
		
		return carSourceRepository.findAll(example, pageable);
	}
	
	
	
	/**
	 * 车源详情
	 * 
	 * @param id
	 * @return
	 */
	public CarSource details(Integer id) {
		CarSource carSource = carSourceRepository.findOne(id);
		List<CarRecordPlace> carRecordPlaces = carRecordPlaceRepository.findByCarSourceId(carSource.getId());
		carSource.setCarRecordPlaces(carRecordPlaces);
		return carSource;
	}
	
	
	/**
	 * 车源审核
	 * 
	 * @param checkDto
	 */
	public void check(CheckDto checkDto) {
		
		
	}
	
}
