package com.zrytech.framework.app.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.zrytech.framework.app.repository.BillLocationRepository;
import com.zrytech.framework.app.repository.CargoLocationRepository;
import com.zrytech.framework.app.repository.WaybillDetailRepository;
import com.zrytech.framework.app.constants.BillLocationConstants;
import com.zrytech.framework.app.dto.billlocation.BillLocationAddDto;
import com.zrytech.framework.app.entity.BillLocation;
import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.app.entity.Loading;
import com.zrytech.framework.app.entity.Waybill;
import com.zrytech.framework.app.entity.WaybillDetail;
import com.zrytech.framework.app.service.BillLocationService;
import com.zrytech.framework.app.service.WaybillService;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;

/**
 * 运单装卸地
 * 
 * @author cat
 *
 */
@Service
public class BillLocationServiceImpl implements BillLocationService {

	@Autowired
	private BillLocationRepository billLocationRepository;

	@Autowired
	private CargoLocationRepository cargoLocationRepository;

	@Autowired
	private WaybillDetailRepository waybillDetailRepository;

	@Autowired
	private WaybillService waybillService;
	
	
	
	@Override
	public BillLocation assertBillLocationExist(Integer billLocationId) {
		BillLocation billLocation = billLocationRepository.findOne(billLocationId);
		if (billLocation == null) {
			throw new BusinessException(112, "运单装卸地不存在");
		}
		return billLocation;
	}

	@Override
	public void deleteByWaybillDetailId(Integer waybillDetailId) {
		billLocationRepository.deleteByWaybillDetailId(waybillDetailId);
	}
	
	@Transactional
	@Override
	public ServerResponse addBillLocation(BillLocationAddDto dto, Customer customer) {
		Loading cargoLocation = this.paramsCheck(dto, customer.getCarOwner().getId());
		this.saveBillLocation(dto, cargoLocation);
		return ServerResponse.success();
	}
	
	/**
	 * 新增或更新运单装卸地入参校验
	 * @author cat
	 * 
	 * @param dto	新增或更新运单装卸地入参
	 * @return
	 */
	private Loading paramsCheck(BillLocationAddDto dto, Integer carOwnerId) {
		Integer cargoLocationId = dto.getCargoLocationId();
		Integer waybillId = dto.getWaybillId();
		Integer waybillDetailId = dto.getWaybillDetailId();
		Loading loading = cargoLocationRepository.findOne(cargoLocationId);
		if (loading == null) {
			throw new BusinessException(112, "货源装卸地不存在");
		}
		Waybill waybill = waybillService.assertWaybillBelongToCurrentUser(waybillId, carOwnerId);
		if (!loading.getCargoId().equals(waybill.getCargoId())) {
			throw new BusinessException(112, "货源装卸地与运单不匹配");
		}
		WaybillDetail waybillDetail = waybillDetailRepository.findOne(waybillDetailId);
		if (waybillDetail == null) {
			throw new BusinessException(112, "运单项不存在");
		}
		if (!waybillDetail.getBillNo().equalsIgnoreCase(waybill.getBillNo())) {
			throw new BusinessException(112, "运单项与运单不匹配");
		}
		loading.setWeightUnit(waybill.getWeightUnit());
		return loading;
	}
	
	/**
	 * 添加运单装卸地
	 * @author cat
	 * 
	 * @param dto	运单装卸地新增或更新入参
	 * @param cargoLocation	货源装卸地
	 */
	private void saveBillLocation(BillLocationAddDto dto, Loading cargoLocation) {
		BillLocation billLocation = billLocationRepository.findByWaybillIdAndWaybillDetailIdAndCargoLocationId(
				dto.getWaybillId(), dto.getWaybillDetailId(), dto.getCargoLocationId());
		Integer count = billLocationRepository.countQtyByWaybillIdAndCargoLocationId(dto.getWaybillId(),
				dto.getCargoLocationId());
		if(count == null) {
			count = 0;
		}
		if (billLocation == null) { // 运单装卸地不存在：新加
			if (dto.getQty() + count > cargoLocation.getQty()) {
				throw new BusinessException(112, "运单装卸数量不能大于货源装卸地可分配数量");
			}
			billLocation = new BillLocation();
			BeanUtils.copyProperties(cargoLocation, billLocation);
			BeanUtils.copyProperties(dto, billLocation);
			billLocation.setCreateDate(new Date());
			billLocation.setStatus(BillLocationConstants.STATUS_DEFAULT);
			billLocation.setWeightUnit(cargoLocation.getWeightUnit());
		} else { // 运单装卸地已存在：更新
			if (dto.getQty() + count - billLocation.getQty() > cargoLocation.getQty()) {
				throw new BusinessException(112, "运单装卸数量不能大于货源装卸地可分配数量");
			}
			billLocation.setQty(dto.getQty());
			if (StringUtils.isNoneBlank(dto.getRemark())) {
				billLocation.setRemark(dto.getRemark());
			}
		}
		billLocationRepository.save(billLocation);
	}
	
	@Override
	public List<BillLocation> findByWaybillDetailId(Integer waybillDetailId) {
		BillLocation billLocation = new BillLocation();
		billLocation.setWaybillDetailId(waybillDetailId);
		Sort sort = new Sort(Direction.ASC, "seqNo");
		Example<BillLocation> example = Example.of(billLocation);
		return billLocationRepository.findAll(example, sort);
	}
	
}
