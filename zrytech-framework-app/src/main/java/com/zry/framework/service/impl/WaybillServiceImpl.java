package com.zry.framework.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.zry.framework.dto.WaybillPageDto;
import com.zry.framework.entity.BillLocation;
import com.zry.framework.entity.Cargo;
import com.zry.framework.entity.Waybill;
import com.zry.framework.entity.WaybillDetail;
import com.zry.framework.mapper.WaybillMapper;
import com.zry.framework.repository.BillLocationRepository;
import com.zry.framework.repository.CarCargoOwnnerRepository;
import com.zry.framework.repository.CargoRepository;
import com.zry.framework.repository.WaybillDetailRepository;
import com.zry.framework.repository.WaybillRepository;
import com.zry.framework.service.WaybillService;
import com.zrytech.framework.base.entity.PageData;
import com.zrytech.framework.base.entity.ServerResponse;

/**
 * 运单
 * @author cat
 *
 */
@Service
public class WaybillServiceImpl implements WaybillService {
	
	@Autowired private WaybillMapper waybillMapper;
	
	@Autowired private WaybillRepository waybillRepository;
	
	@Autowired private WaybillDetailRepository waybillDetailRepository;
	
	@Autowired private CarCargoOwnnerRepository carCargoOwnnerRepository;
	
	@Autowired private CargoRepository cargoRepository;
	
	@Autowired private BillLocationRepository billLocationRepository;
	
	
	/**
	 * 运单分页
	 * @author cat
	 * 
	 * @param dto	查询条件，详见{@link WaybillPageDto}
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Override
	public ServerResponse page(WaybillPageDto dto, Integer pageNum, Integer pageSize){
		com.github.pagehelper.Page<Object> result = PageHelper.startPage(pageNum, pageSize);
		List<Waybill> list = waybillMapper.selectSelective(dto);
		for (Waybill waybill : list) {
			waybill = bindingCarOwnerName(waybill);
			waybill = bindingCargoOwnerName(waybill);
		}
		PageData<Waybill> pageData = new PageData<Waybill>(result.getPageSize(), result.getPageNum(), result.getTotal(), list);
		return ServerResponse.successWithData(pageData);
	}
	
	
	/**
	 * 运单详情
	 * @author cat
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ServerResponse details(Integer id) {
		Waybill waybill = waybillRepository.findOne(id);
		waybill = bindingCargo(waybill);
		waybill = bindingCarOwnerName(waybill);
		waybill = bindingCargoOwnerName(waybill);
		waybill = bindingWaybillDetail(waybill);
		return ServerResponse.successWithData(waybill);
	}
	
	/**
	 * 为运单绑定运单项数据
	 * @author cat
	 * 
	 * @param waybill
	 * @return
	 */
	public Waybill bindingWaybillDetail(Waybill waybill) {
		String billNo = waybill.getBillNo();
		if(StringUtils.isNoneEmpty(billNo)) {
			List<WaybillDetail> waybillDetails = waybillDetailRepository.findByBillNo(billNo);
			for (WaybillDetail waybillDetail : waybillDetails) {
				waybillDetail = bindingBillLocation(waybillDetail);
			}
			waybill.setWaybillDetails(waybillDetails);
		}
		return waybill;
	}
	
	
	/**
	 * 为运单项绑定装卸地数据
	 * @author cat
	 * 
	 * @param waybillDetail
	 * @return
	 */
	public WaybillDetail bindingBillLocation(WaybillDetail waybillDetail) {
		Integer id = waybillDetail.getId();
		if(id != null) {
			List<BillLocation> billLocations = billLocationRepository.findByWaybillDetailId(id);
			waybillDetail.setBillLocations(billLocations);
		}
		return waybillDetail;
	}
	
	
	/**
	 * 为运单设置货源
	 * @author cat
	 * 
	 * @param cargoMatter
	 * @return
	 */
	public Waybill bindingCargo(Waybill waybill) {
		Integer cargoId = waybill.getCargoId();
		if(cargoId != null) {
			Cargo cargo = cargoRepository.findOne(cargoId);
			waybill.setCargo(cargo);
		}
		return waybill;
	}
	
	
	/**
	 * 为运单绑定车主企业名称
	 * @author cat
	 * 
	 * @param waybill
	 * @return
	 */
	public Waybill bindingCarOwnerName(Waybill waybill) {
		Integer carOwnerId = waybill.getCarOwnnerId();
		if(carOwnerId != null) {
			waybill.setCarOwnerName(carCargoOwnnerRepository.findNameById(carOwnerId));
		}
		return waybill;
	}
	
	
	/**
	 * 为运单绑定货主企业名称
	 * @author cat
	 * 
	 * @param waybill
	 * @return
	 */
	public Waybill bindingCargoOwnerName(Waybill waybill) {
		Integer cargoOwnerId = waybill.getCargoOwnnerId();
		if(cargoOwnerId != null) {
			waybill.setCargoOwnerName(carCargoOwnnerRepository.findNameById(cargoOwnerId));
		}
		return waybill;
	}
	
	
	
}
