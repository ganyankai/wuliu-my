package com.zrytech.framework.app.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.zrytech.framework.app.constants.CargoConstant;
import com.zrytech.framework.app.dto.CommonDto;
import com.zrytech.framework.app.dto.WaybillDto;
import com.zrytech.framework.app.entity.*;
import com.zrytech.framework.app.utils.CheckFieldUtils;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.base.util.RequestUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.zrytech.framework.app.dto.WaybillPageDto;
import com.zrytech.framework.app.dto.waybill.WaybillUpdateDto;
import com.zrytech.framework.app.dto.waybilldetail.WaybillDetailAddDto;
import com.zrytech.framework.app.dto.waybilldetail.WaybillDetailUpdateDto;
import com.zrytech.framework.app.mapper.WaybillMapper;
import com.zrytech.framework.app.repository.BillLocationRepository;
import com.zrytech.framework.app.repository.CarCargoOwnnerRepository;
import com.zrytech.framework.app.repository.CargoRepository;
import com.zrytech.framework.app.repository.EvaluateRepository;
import com.zrytech.framework.app.repository.WaybillDetailRepository;
import com.zrytech.framework.app.repository.WaybillRepository;
import com.zrytech.framework.app.service.BillLocationService;
import com.zrytech.framework.app.service.CarPersonService;
import com.zrytech.framework.app.service.CarService;
import com.zrytech.framework.app.service.WaybillDetailService;
import com.zrytech.framework.app.service.WaybillService;
import com.zrytech.framework.base.entity.PageData;
import com.zrytech.framework.base.entity.ServerResponse;

/**
 * 运单
 */
@Service
public class WaybillServiceImpl implements WaybillService {

	@Autowired
	private WaybillMapper waybillMapper;

	@Autowired
	private WaybillRepository waybillRepository;

	@Autowired
	private WaybillDetailRepository waybillDetailRepository;

	@Autowired
	private CarCargoOwnnerRepository carCargoOwnnerRepository;

	@Autowired
	private CargoRepository cargoRepository;

	@Autowired
	private BillLocationRepository billLocationRepository;

	@Autowired
	private EvaluateRepository evaluateRepository;

	@Autowired
	private CarService carService;

	@Autowired
	private CarPersonService carPersonService;

	@Autowired
	private BillLocationService billLocationService;

	@Autowired
	private WaybillDetailService waybillDetailService;

	
    
	@Override
	public PageData<Waybill> waybillPage(WaybillPageDto dto, Integer pageNum, Integer pageSize) {
		com.github.pagehelper.Page<Object> result = PageHelper.startPage(pageNum, pageSize);
		List<Waybill> list = waybillMapper.selectSelective(dto);
		for (Waybill waybill : list) {
			waybill = this.bindingCarOwnerName(waybill);
			waybill = this.bindingCargoOwnerName(waybill);
		}
		return new PageData<Waybill>(result.getPageSize(), result.getPageNum(), result.getTotal(), list);
	}

	@Override
	public ServerResponse adminDetails(CommonDto dto) {
		Waybill waybill = this.assertWaybillExist(dto.getId());
		waybill = this.bindingCargo(waybill);
		waybill = this.bindingCarOwnerName(waybill);
		waybill = this.bindingCargoOwnerName(waybill);
		waybill = this.bindingWaybillDetail(waybill);
		waybill = this.bindingEvaluate(waybill);
		return ServerResponse.successWithData(waybill);
	}
	
	
	
	// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

	public Waybill assertWaybillBelongToCurrentUser(Integer waybillId, Integer carOwnerId) {
		Waybill waybill = this.assertWaybillExist(waybillId);
		if (!waybill.getCarOwnnerId().equals(carOwnerId))
			throw new BusinessException(112, "运单不存在");
		return waybill;
	}
    
	/**
	 * 断言运单存在
	 * @author cat
	 * 
	 * @param waybillId	运单Id
	 * @return
	 */
    private Waybill assertWaybillExist(Integer waybillId) {
		Waybill waybill = waybillRepository.findOne(waybillId);
		if (waybill == null)
			throw new BusinessException(112, "运单不存在");
		return waybill;
	}
    
    /**
     * 断言运单属于当前登录车主
     * @author cat
     *
     * @param billNo    运单号
     * @param carOwnerId 车主Id
     */
	private Waybill assertWaybillBelongToCurrentUser(String billNo, Integer carOwnerId) {
		Waybill waybill = waybillRepository.findByBillNo(billNo);
		if (waybill == null) {
			throw new BusinessException(112, "运单项不存在");
		}
		if (!waybill.getCarOwnnerId().equals(carOwnerId)) {
			throw new BusinessException(112, "运单项不存在");
		}
		return waybill;
	}
	
	/**
     * 为运单绑定运单项数据
     *
     * @param waybill
     * @return
     * @author cat
     */
	private Waybill bindingWaybillDetail(Waybill waybill) {
		String billNo = waybill.getBillNo();
		if (StringUtils.isNoneEmpty(billNo)) {
			List<WaybillDetail> waybillDetails = waybillDetailRepository.findByBillNo(billNo);
			for (WaybillDetail waybillDetail : waybillDetails) {
				waybillDetail = this.bindingBillLocation(waybillDetail);
				CarPerson driver = carPersonService.assertDriverAvailable(waybillDetail.getDriverId());
				CarPerson supercargo = carPersonService.assertSupercargoAvailable(waybillDetail.getSupercargoId());
				Car car = carService.assertCarAvailable(waybillDetail.getCarId());
				waybillDetail.setCarNo(car.getCarNo());
				waybillDetail.setDriverIdCard(driver.getIdCard());
				waybillDetail.setDriverName(driver.getName());
				waybillDetail.setDriverTel(driver.getTel());
				waybillDetail.setSupercargoIdCard(supercargo.getIdCard());
				waybillDetail.setSupercargoName(supercargo.getName());
				waybillDetail.setSupercargoTel(supercargo.getTel());
			}
			waybill.setWaybillDetails(waybillDetails);
		}
		return waybill;
	}
    
    /**
     * 为运单项绑定装卸地数据
     *
     * @param waybillDetail
     * @return
     * @author cat
     */
	private WaybillDetail bindingBillLocation(WaybillDetail waybillDetail) {
		Integer id = waybillDetail.getId();
		if (id != null) {
			List<BillLocation> billLocations = billLocationRepository.findByWaybillDetailId(id);
			waybillDetail.setBillLocations(billLocations);
		}
		return waybillDetail;
	}

    /**
     * 为运单设置货源
     * @author cat
     *
     * @param waybill
     * @return
     */
	private Waybill bindingCargo(Waybill waybill) {
		Integer cargoId = waybill.getCargoId();
		if (cargoId != null) {
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
	private Waybill bindingCarOwnerName(Waybill waybill) {
        Integer carOwnerId = waybill.getCarOwnnerId();
        if (carOwnerId != null) {
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
    private Waybill bindingCargoOwnerName(Waybill waybill) {
        Integer cargoOwnerId = waybill.getCargoOwnnerId();
        if (cargoOwnerId != null) {
            waybill.setCargoOwnerName(carCargoOwnnerRepository.findNameById(cargoOwnerId));
        }
        return waybill;
    }

    /**
     * 为运单绑定评价信息
     *
     * @param waybill
     * @return
     * @author cat
     */
    private Waybill bindingEvaluate(Waybill waybill) {
        String billNo = waybill.getBillNo();
        if (StringUtils.isNoneEmpty(billNo)) {
            List<Evaluate> evaluates = evaluateRepository.findByBillNo(billNo);
            waybill.setEvaluates(evaluates);
        }
        return waybill;
    }
	
    
	
	
	
	// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	
	@Override
	public ServerResponse cargoOwnerDetails(CommonDto dto) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		CarCargoOwnner cargoOwner = customer.getCargoOwner();
		Waybill waybill = this.assertWaybillExist(dto.getId());
		if (!waybill.getCargoOwnnerId().equals(cargoOwner.getId())) {
			throw new BusinessException(112, "运单不存在");
		}
		waybill = this.bindingCargo(waybill);
		waybill = this.bindingCarOwnerName(waybill);
		waybill = this.bindingCargoOwnerName(waybill);
		waybill = this.bindingWaybillDetail(waybill);
		waybill = this.bindingEvaluate(waybill);
		return ServerResponse.successWithData(waybill);
	}
    
	@Transactional
	@Override
	public ServerResponse confirm(CommonDto dto) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		Integer cargoOwnerId = customer.getCargoOwner().getId();
		Integer waybillId = dto.getId();

		Waybill waybill = this.assertWaybillExist(waybillId);
		if (!waybill.getCargoOwnnerId().equals(cargoOwnerId)) {
			throw new BusinessException(112, "运单不存在");
		}
		if (!CargoConstant.WAYBILL_STATUS_WAIT_DETERMINE.equalsIgnoreCase(waybill.getStatus())) {
			throw new BusinessException(112, "失败：运单状态不是待确认");
		}

		waybillMapper.updateStatusById(waybillId, CargoConstant.WAYBILL_STATUS_IN_TRANSIT);
		return ServerResponse.success();
	}

	
	@Override
	public ServerResponse carOwnerDetails(CommonDto dto) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		CarCargoOwnner carOwner = customer.getCarOwner();
		Waybill waybill = this.assertWaybillBelongToCurrentUser(dto.getId(), carOwner.getId());
		waybill = this.bindingCargo(waybill);
		waybill = this.bindingCarOwnerName(waybill);
		waybill = this.bindingCargoOwnerName(waybill);
		waybill = this.bindingWaybillDetail(waybill);
		waybill = this.bindingEvaluate(waybill);
		return ServerResponse.successWithData(waybill);
	}
	
	
	
	@Transactional
	@Override
	public ServerResponse update(WaybillUpdateDto dto) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		Integer carOwnerId = customer.getCarOwner().getId();
		Integer waybillId = dto.getId();

		Waybill waybill = this.assertWaybillBelongToCurrentUser(waybillId, carOwnerId);

		if (!CargoConstant.WAYBILL_STATUS_WAIT_GENERATE.equalsIgnoreCase(waybill.getStatus())) {
			throw new BusinessException(112, "修改失败：仅待生成的运单可以修改");
		}

		BeanUtils.copyProperties(dto, waybill);
		waybillRepository.save(waybill);

		return ServerResponse.success();
	}
	
	@Transactional
	@Override
	public ServerResponse cancel(CommonDto dto) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		Integer carOwnerId = customer.getCarOwner().getId();
		Integer waybillId = dto.getId();

		Waybill waybill = this.assertWaybillBelongToCurrentUser(waybillId, carOwnerId);
		if (CargoConstant.WAYBILL_STATUS_WAIT_GENERATE.equalsIgnoreCase(waybill.getStatus())
				|| CargoConstant.WAYBILL_STATUS_WAIT_DETERMINE.equalsIgnoreCase(waybill.getStatus())) {
			waybillMapper.updateStatusById(waybillId, CargoConstant.WAYBILL_STATUS_CANCELLED);
		} else {
			throw new BusinessException(112, "取消失败：仅待生成，待确认的运单可以取消");
		}

		return ServerResponse.success();
	}

	@Transactional
	@Override
	public ServerResponse addWaybillDetail(WaybillDetailAddDto dto) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		CarCargoOwnner carOwner = customer.getCarOwner();
		Waybill waybill = this.assertWaybillBelongToCurrentUser(dto.getWaybillId(), carOwner.getId());
		this.waybillDetailCheck(dto, carOwner.getId(), waybill.getBillNo());
		this.insertWaybillDetail(dto, waybill);
		return ServerResponse.successWithData("添加成功");
	}
    
    /**
     * 添加运单项参数校验
     * <pre>
     * 1.同一个运单下，车辆唯一，司机唯一，压货人唯一。
     * 2.车辆、司机、压货人均属于当前登录车主
     * 3.车辆、司机、压货人均已认证
     * </pre>
     * @author cat
     * 
     * @param dto	添加运单项入参
     * @param carOwnerId	车主Id
     * @param billNo	运单编号
     */
	private void waybillDetailCheck(WaybillDetailAddDto dto, Integer carOwnerId, String billNo) {
		Integer driverId = dto.getDriverId();
		Integer supercargoId = dto.getSupercargoId();
		Integer carId = dto.getCarId();
		if (driverId != null) {
			CarPerson driver = carPersonService.assertDriverBelongToCurrentUser(driverId, carOwnerId);
			carPersonService.assertDriverCertified(driver);
			WaybillDetail waybillDetail = waybillDetailRepository.findByBillNoAndDriverId(billNo, driverId);
			if (waybillDetail != null) {
				throw new BusinessException(112, "司机已被分配到当前运单");
			}
		}
		if (supercargoId != null) {
			CarPerson supercargo = carPersonService.assertSupercargoBelongToCurrentUser(supercargoId, carOwnerId);
			carPersonService.assertSupercargoCertified(supercargo);
			WaybillDetail waybillDetail = waybillDetailRepository.findByBillNoAndSupercargoId(billNo, supercargoId);
			if (waybillDetail != null) {
				throw new BusinessException(112, "压货人已被分配到当前运单");
			}
		}
		if (carId != null) {
			Car car = carService.assertCarBelongToCurrentUser(carId, carOwnerId);
			carService.assertCarCertified(car);
			WaybillDetail waybillDetail = waybillDetailRepository.findByBillNoAndCarId(billNo, carId);
			if (waybillDetail != null) {
				throw new BusinessException(112, "车辆已被分配到当前运单");
			}
		}
	}
	
	/**
     * 新增运单项
     * @author cat
     *
     * @param dto
     * @param waybill
     * @return
     */
	private WaybillDetail insertWaybillDetail(WaybillDetailAddDto dto, Waybill waybill) {
		WaybillDetail waybillDetail = new WaybillDetail();
		BeanUtils.copyProperties(dto, waybillDetail);
		waybillDetail.setBillNo(waybill.getBillNo());
		waybillDetail.setWeightUnit(waybill.getWeightUnit());
		waybillDetail.setCreateDate(new Date());
		waybillDetail = waybillDetailRepository.save(waybillDetail);
		return waybillDetail;
	}

	@Transactional
	@Override
	public ServerResponse updateWaybillDetail(WaybillDetailUpdateDto dto) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		CarCargoOwnner carOwner = customer.getCarOwner();
		WaybillDetail waybillDetail = waybillDetailService.assertWaybillDetailExist(dto.getWaybillDetailId());
		this.assertWaybillBelongToCurrentUser(waybillDetail.getBillNo(), carOwner.getId());
		this.updateWaybillDetailCheck(dto, carOwner.getId(), waybillDetail.getBillNo());
		BeanUtils.copyProperties(dto, waybillDetail);
		waybillDetailRepository.save(waybillDetail);
		return ServerResponse.successWithData("修改成功");
	}
	
	private void updateWaybillDetailCheck(WaybillDetailUpdateDto dto, Integer carOwnerId, String billNo) {
		Integer driverId = dto.getDriverId();
		Integer supercargoId = dto.getSupercargoId();
		Integer carId = dto.getCarId();
		if (driverId != null) {
			CarPerson driver = carPersonService.assertDriverBelongToCurrentUser(driverId, carOwnerId);
			carPersonService.assertDriverCertified(driver);
			WaybillDetail waybillDetail = waybillDetailRepository.findByBillNoAndDriverId(billNo, driverId);
			if (waybillDetail != null && !waybillDetail.getId().equals(dto.getWaybillDetailId())) {
				throw new BusinessException(112, "司机已被分配到当前运单");
			}
		}
		if (supercargoId != null) {
			CarPerson supercargo = carPersonService.assertSupercargoBelongToCurrentUser(supercargoId, carOwnerId);
			carPersonService.assertSupercargoCertified(supercargo);
			WaybillDetail waybillDetail = waybillDetailRepository.findByBillNoAndSupercargoId(billNo, supercargoId);
			if (waybillDetail != null && !waybillDetail.getId().equals(dto.getWaybillDetailId())) {
				throw new BusinessException(112, "压货人已被分配到当前运单");
			}
		}
		if (carId != null) {
			Car car = carService.assertCarBelongToCurrentUser(carId, carOwnerId);
			carService.assertCarCertified(car);
			WaybillDetail waybillDetail = waybillDetailRepository.findByBillNoAndCarId(billNo, carId);
			if (waybillDetail != null && !waybillDetail.getId().equals(dto.getWaybillDetailId())) {
				throw new BusinessException(112, "车辆已被分配到当前运单");
			}
		}
	}

	@Transactional
	@Override
	public ServerResponse deleteWaybillDetail(CommonDto dto) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		CarCargoOwnner carOwner = customer.getCarOwner();
		Integer waybillDetailId = dto.getId();
		WaybillDetail waybillDetail = waybillDetailService.assertWaybillDetailExist(waybillDetailId);
		this.assertWaybillBelongToCurrentUser(waybillDetail.getBillNo(), carOwner.getId());
		waybillDetailRepository.delete(waybillDetailId);
		billLocationService.deleteByWaybillDetailId(waybillDetailId);
		return ServerResponse.successWithData("删除成功");
	}

	@Transactional
	@Override
	public ServerResponse deleteBillLocation(CommonDto dto) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		CarCargoOwnner carOwner = customer.getCarOwner();
		BillLocation billLocation = billLocationService.assertBillLocationExist(dto.getId());
		this.assertWaybillBelongToCurrentUser(billLocation.getWaybillId(), carOwner.getId());
		billLocationRepository.delete(dto.getId());
		return ServerResponse.successWithData("删除成功");
	}

	@Transactional
	@Override
	public ServerResponse submit(CommonDto dto) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		CarCargoOwnner carOwner = customer.getCarOwner();
		Integer waybillId = dto.getId();
		this.assertWaybillBelongToCurrentUser(waybillId, carOwner.getId());
		int i = waybillMapper.submit(waybillId);
		if (i != 1) {
			throw new BusinessException(112, "提交失败");
		} else {
			return ServerResponse.success();
		}
	}
	
	
    
	
	
	

   
    
    
	
	
	
	
	
	
	
	
	/**
     * 运单统计
     */
    @Override
    @Deprecated
    public ServerResponse coundIndent(WaybillDto waybillDto) {
        CheckFieldUtils.checkObjecField(waybillDto.getCargoOwnnerId());
        List<String> typeCount = waybillMapper.coundIndent(waybillDto.getCargoOwnnerId());
        Map<String, Object> map = countList(typeCount);
        return ServerResponse.successWithData(map);
    }

    @Deprecated
	public Map<String, Object> countList(List<String> typeCount) {
		Map<String, Object> map = new HashMap<>();
		/*if (typeCount != null && typeCount.size() > 0) {
			for (String str : typeCount) {
				String[] arr = str.split("\\$");
				if (arr[0].equalsIgnoreCase(CargoConstant.WAYBILL_STATUS_WAIT_GENERATE)) {
					map.put(CargoConstant.WAYBILL_STATUS_WAIT_GENERATE, arr[1] == null ? 0 : Integer.parseInt(arr[1]));
				} else if (arr[0].equalsIgnoreCase(CargoConstant.AWAIT_DETERMINE)) {
					map.put(CargoConstant.AWAIT_DETERMINE, arr[1] == null ? 0 : Integer.parseInt(arr[1]));
				} else if (arr[0].equalsIgnoreCase(CargoConstant.AWAIT_LOADING)) {
					map.put(CargoConstant.AWAIT_LOADING, arr[1] == null ? 0 : Integer.parseInt(arr[1]));
				} else if (arr[0].equalsIgnoreCase(CargoConstant.AWAIT_ACCEPT)) {
					map.put(CargoConstant.AWAIT_ACCEPT, arr[1] == null ? 0 : Integer.parseInt(arr[1]));
				} else if (arr[0].equalsIgnoreCase(CargoConstant.SIGN_PAY)) {
					map.put(CargoConstant.SIGN_PAY, arr[1] == null ? 0 : Integer.parseInt(arr[1]));
				} else if (arr[0].equalsIgnoreCase(CargoConstant.PAIED_EVALUATION)) {
					map.put(CargoConstant.PAIED_EVALUATION, arr[1] == null ? 0 : Integer.parseInt(arr[1]));
				} else {
					map.put(CargoConstant.COMPLETED, arr[1] == null ? 0 : Integer.parseInt(arr[1]));
				}
			}
			checkMap(map);
		} else {
			map.put(CargoConstant.AWAIT_GENERATE, 0);
			map.put(CargoConstant.AWAIT_DETERMINE, 0);
			map.put(CargoConstant.AWAIT_LOADING, 0);
			map.put(CargoConstant.AWAIT_ACCEPT, 0);
			map.put(CargoConstant.SIGN_PAY, 0);
			map.put(CargoConstant.PAIED_EVALUATION, 0);
			map.put(CargoConstant.COMPLETED, 0);
		}*/
		return map;
	}

	/*public void checkMap(Map<String, Object> map) {
		if (map.get(CargoConstant.AWAIT_GENERATE) == null) {
			map.put(CargoConstant.AWAIT_GENERATE, 0);
		}
		if (map.get(CargoConstant.AWAIT_DETERMINE) == null) {
			map.put(CargoConstant.AWAIT_DETERMINE, 0);
		}
		if (map.get(CargoConstant.AWAIT_LOADING) == null) {
			map.put(CargoConstant.AWAIT_LOADING, 0);
		}
		if (map.get(CargoConstant.AWAIT_ACCEPT) == null) {
			map.put(CargoConstant.AWAIT_ACCEPT, 0);
		}
		if (map.get(CargoConstant.SIGN_PAY) == null) {
			map.put(CargoConstant.SIGN_PAY, 0);
		}
		if (map.get(CargoConstant.PAIED_EVALUATION) == null) {
			map.put(CargoConstant.PAIED_EVALUATION, 0);
		}
		if (map.get(CargoConstant.COMPLETED) == null) {
			map.put(CargoConstant.COMPLETED, 0);
		}
	}*/
}
