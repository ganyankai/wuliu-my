package com.zrytech.framework.app.service.impl;

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

import com.zrytech.framework.app.repository.BillLocationRepository;
import com.zrytech.framework.app.dto.billlocation.BillLocationPageDto;
import com.zrytech.framework.app.entity.BillLocation;
import com.zrytech.framework.app.service.BillLocationService;
import com.zrytech.framework.base.exception.BusinessException;

@Service
public class BillLocationServiceImpl implements BillLocationService {

	@Autowired
	private BillLocationRepository billLocationRepository;
	
	
	/**
	 * 运单装卸地分页
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param dto
	 * @return
	 */
	public Page<BillLocation> page(Integer pageNumber, Integer pageSize, BillLocationPageDto dto){
		BillLocation billLocation = new BillLocation();
		BeanUtils.copyProperties(dto, billLocation);
		
		Sort sort = new Sort(Direction.DESC, "createDate");
		Pageable pageable = new PageRequest(pageNumber - 1, pageSize, sort);
		
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("name", GenericPropertyMatchers.contains());
		
		Example<BillLocation> example = Example.of(billLocation, matcher);
		
		return billLocationRepository.findAll(example, pageable);
	}
	
	
	/**
	 * 运单装卸地详情
	 * 
	 * @param id
	 * @return
	 */
	public BillLocation details(Integer id) {
		return billLocationRepository.findOne(id);
	}
	
	
	
	/**
	 * 断言运单装卸地存在
	 * @author cat
	 * 
	 * @param billLocationId	运单装卸地Id
	 * @return	运单装卸地
	 */
	@Override
	public BillLocation assertBillLocationExist(Integer billLocationId) {
		BillLocation billLocation = billLocationRepository.findOne(billLocationId);
        if (billLocation == null) {
            throw new BusinessException(112, "运单装卸地不存在");
        }
        return billLocation;
	}
	
	
	/**
	 * 删除运单项对应的运单装卸地（物理删除）
	 * @author cat
	 * 
	 * @param waybillDetailId	运单项Id
	 */
	@Override
	public void deleteByWaybillDetailId(Integer waybillDetailId) {
		 BillLocation billLocation = new BillLocation();
	     billLocation.setWaybillDetailId(waybillDetailId);
	     billLocationRepository.delete(billLocation);
	}
}
