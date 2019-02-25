package com.zry.framework.service;

import com.zry.framework.dto.WaybillDto;
import org.springframework.stereotype.Service;

import com.zry.framework.dto.WaybillPageDto;
import com.zrytech.framework.base.entity.ServerResponse;

@Service
public interface WaybillService {
	
	public ServerResponse page(WaybillPageDto dto, Integer pageNum, Integer pageSize);
	
	public ServerResponse details(Integer id);

    ServerResponse createIndent(WaybillDto waybillDto);
}
