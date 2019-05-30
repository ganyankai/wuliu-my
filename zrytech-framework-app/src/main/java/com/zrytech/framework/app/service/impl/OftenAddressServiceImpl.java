package com.zrytech.framework.app.service.impl;

import com.zrytech.framework.app.dto.CommonDto;
import com.zrytech.framework.app.dto.oftenaddress.OftenAddressAddDto;
import com.zrytech.framework.app.dto.oftenaddress.OftenAddressUpdateDto;
import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.app.entity.OftenAddress;
import com.zrytech.framework.app.mapper.OftenAddressMapper;
import com.zrytech.framework.app.repository.OftenAddressRepository;
import com.zrytech.framework.app.service.OftenAddressService;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.base.util.RequestUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OftenAddressServiceImpl implements OftenAddressService {

	@Autowired
	private OftenAddressMapper mapper;
	
    @Autowired
    private OftenAddressRepository repository;
    
    
	@Transactional
	@Override
	public ServerResponse save(OftenAddressAddDto dto) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		OftenAddress address = new OftenAddress();
		BeanUtils.copyProperties(dto, address);
		address.setCustomerId(customer.getId());
		
		Example<OftenAddress> example = Example.of(address);
		List<OftenAddress> list = repository.findAll(example);
		if (!list.isEmpty()) {
			throw new BusinessException(112, "常用路线已存在");
		}
		
		address.setCreateDate(new Date());
		repository.save(address);
		return ServerResponse.successWithData(address);
	}
	
	@Transactional
	@Override
	public ServerResponse delete(CommonDto dto) {
		this.assertBelongToCurrentCustomer(dto.getId());
		repository.delete(dto.getId());
		return ServerResponse.success();
	}
	
	@Transactional
	@Override
	public ServerResponse update(OftenAddressUpdateDto dto) {
		OftenAddress address = this.assertBelongToCurrentCustomer(dto.getId());
		BeanUtils.copyProperties(dto, address);
		repository.save(address);
		return ServerResponse.successWithData(address);
	}
	
	public ServerResponse list() {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		List<OftenAddress> list = mapper.selectByCustomerId(customer.getId());
		Map<String, Object> result = new HashMap<>();
		result.put("total", list.size());
		result.put("list", list);
		return ServerResponse.successWithData(result);
	}
	
	@Override
	public ServerResponse details(CommonDto dto) {
		OftenAddress address = this.assertBelongToCurrentCustomer(dto.getId());
		return ServerResponse.successWithData(address);
	}
	
	@Override
	public OftenAddress assertBelongToCurrentCustomer(Integer addressId) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		OftenAddress address = repository.findOne(addressId);
		if (address == null) {
			throw new BusinessException(112, "常用路线不存在");
		}
		if (!address.getCustomerId().equals(customer.getId())) {
			throw new BusinessException(112, "常用路线不存在");
		}
		return address;
	}
	
}
