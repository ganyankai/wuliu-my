package com.zrytech.framework.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.zrytech.framework.app.mapper.WaybillDetailMapper;
import com.zrytech.framework.base.entity.PageData;
import com.zrytech.framework.base.entity.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zrytech.framework.app.entity.WaybillDetail;
import com.zrytech.framework.app.repository.WaybillDetailRepository;
import com.zrytech.framework.app.service.WaybillDetailService;
import com.zrytech.framework.base.exception.BusinessException;

import java.util.List;

@Service
public class WaybillDetailServiceImpl implements WaybillDetailService {
	@Autowired
	private WaybillDetailRepository waybillDetailRepository;

	@Autowired
	private WaybillDetailMapper waybillDetailMapper;

	/**
	 * 断言运单项存在
	 * @author cat
	 * 
	 * @param waybillDetailId	运单项Id
	 * @return	运单项
	 */
	@Override
	public WaybillDetail assertWaybillDetailExist(Integer waybillDetailId) {
        WaybillDetail waybillDetail = waybillDetailRepository.findOne(waybillDetailId);
        if (waybillDetail == null) {
            throw new BusinessException(112, "运单项不存在");
        }
        return waybillDetail;
	}

	@Override
	public ServerResponse getCarWbdList(Integer pageNum, Integer pageSize,Integer carId) {
		com.github.pagehelper.Page<Object> page = PageHelper.startPage(pageNum, pageSize);

		List<WaybillDetail> list = waybillDetailMapper.getCarWbdList(carId);

		PageData<WaybillDetail> pageData = new PageData<>(page.getPageSize(), page.getPageNum(), page.getTotal(), list);
		return ServerResponse.successWithData(pageData);
	}

	@Override
	public ServerResponse getCargoWbdList(Integer pageNum, Integer pageSize,Integer cargoId) {
		com.github.pagehelper.Page<Object> page = PageHelper.startPage(pageNum, pageSize);
		List<WaybillDetail> list = waybillDetailMapper.getCargoWbdList(cargoId);

		PageData<WaybillDetail> pageData = new PageData<>(page.getPageSize(), page.getPageNum(), page.getTotal(), list);
		return ServerResponse.successWithData(pageData);
	}
}
