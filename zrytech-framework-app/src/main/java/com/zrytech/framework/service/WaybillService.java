package com.zrytech.framework.service;

import com.zrytech.framework.dto.DeleteDto;
import com.zrytech.framework.dto.DetailsDto;
import com.zrytech.framework.dto.WaybillDto;
import com.zrytech.framework.base.entity.Page;
import org.springframework.stereotype.Service;

import com.zrytech.framework.dto.WaybillPageDto;
import com.zrytech.framework.dto.waybilldetail.WaybillDetailAddDto;
import com.zrytech.framework.entity.Customer;
import com.zrytech.framework.base.entity.ServerResponse;

@Service
public interface WaybillService {
	
	public ServerResponse page(WaybillPageDto dto, Integer pageNum, Integer pageSize);
	
	public ServerResponse details(Integer id);

    ServerResponse createIndent(WaybillDto waybillDto);

    ServerResponse confirmIndent(WaybillDto waybillDto);

    ServerResponse indentPage(WaybillDto waybillDto, Page page);

    ServerResponse coundIndent(WaybillDto waybillDto);

    ServerResponse get(WaybillDto waybillDto);

    ServerResponse changeIndent(WaybillDto waybillDto);

    ServerResponse delete(WaybillDto waybillDto);
    
    
    public ServerResponse details(DetailsDto dto, Customer customer);
    
    public ServerResponse page(WaybillPageDto dto, Integer pageNum, Integer pageSize, Customer customer);
    
    public ServerResponse deleteWaybillDetail(DeleteDto dto, Customer customer);
    
    public ServerResponse deleteBillLocation(DeleteDto dto, Customer customer);
    
    public ServerResponse addWaybillDetail(WaybillDetailAddDto dto, Customer customer);
    
    
}
