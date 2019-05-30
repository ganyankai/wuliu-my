package com.zrytech.framework.app.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zrytech.framework.app.dto.CommonDto;
import com.zrytech.framework.app.dto.focus.MyFocusLineAddDto;
import com.zrytech.framework.app.dto.focus.MyFocusLineUpdateDto;
import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.app.entity.MyFocusLine;
import com.zrytech.framework.app.repository.MyFocusLineRepository;
import com.zrytech.framework.app.service.MyFocusLineService;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.base.util.RequestUtil;

@Service
public class MyFocusLineServiceImpl implements MyFocusLineService {

	@Autowired
	private MyFocusLineRepository repository;

	@Transactional
	@Override
	public ServerResponse save(MyFocusLineAddDto dto) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		MyFocusLine line = new MyFocusLine();
		BeanUtils.copyProperties(dto, line);
		line.setCreateBy(customer.getId());
		
		Example<MyFocusLine> example = Example.of(line);
		List<MyFocusLine> list = repository.findAll(example);
		if (!list.isEmpty()) {
			throw new BusinessException(112, "关注的路线已存在");
		}
		
		line.setCreateDate(new Date());
		repository.save(line);
		return ServerResponse.successWithData(line);
	}

	@Transactional
	@Override
	public ServerResponse update(MyFocusLineUpdateDto dto) {
		MyFocusLine line = this.assertBelongToCurrentCustomer(dto.getId());
		
		MyFocusLine temp = new MyFocusLine();
		BeanUtils.copyProperties(dto, temp);
		temp.setId(null);
		temp.setCreateBy(line.getCreateBy());
		Example<MyFocusLine> example = Example.of(temp);
		List<MyFocusLine> list = repository.findAll(example);
		if (!list.isEmpty()) {
			throw new BusinessException(112, "关注的路线已存在");
		}
		
		BeanUtils.copyProperties(dto, line);
		repository.save(line);
		return ServerResponse.successWithData(line);
	}

	@Transactional
	@Override
	public ServerResponse delete(CommonDto dto) {
		this.assertBelongToCurrentCustomer(dto.getId());
		repository.delete(dto.getId());
		return ServerResponse.success();
	}

	@Override
	public ServerResponse list() {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		List<MyFocusLine> list = repository.findByCreateBy(customer.getId());
		Map<String, Object> result = new HashMap<>();
		result.put("total", list.size());
		result.put("list", list);
		return ServerResponse.successWithData(result);
	}

	@Override
	public ServerResponse details(CommonDto dto) {
		MyFocusLine line = this.assertBelongToCurrentCustomer(dto.getId());
		return ServerResponse.successWithData(line);
	}

	@Override
	public MyFocusLine assertBelongToCurrentCustomer(Integer lineId) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		MyFocusLine line = repository.findOne(lineId);
		if (line == null) {
			throw new BusinessException(112, "关注的路线不存在");
		}
		if (!line.getCreateBy().equals(customer.getId())) {
			throw new BusinessException(112, "关注的路线不存在");
		}
		return line;
	}

}
