package com.zrytech.framework.app.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zrytech.framework.app.repository.CarCargoOwnnerRepository;
import com.zrytech.framework.app.repository.CargoMatterRepository;
import com.zrytech.framework.app.repository.CargoRepository;
import com.zrytech.framework.app.repository.WaybillRepository;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.zrytech.framework.app.constants.ApproveConstants;
import com.zrytech.framework.app.constants.ApproveLogConstants;
import com.zrytech.framework.app.constants.CargoConstant;
import com.zrytech.framework.app.constants.CargoMatterConstants;
import com.zrytech.framework.app.dto.CargoMatterPageDto;
import com.zrytech.framework.app.dto.CommonDto;
import com.zrytech.framework.app.dto.DetailsDto;
import com.zrytech.framework.app.dto.approve.ApproveDto;
import com.zrytech.framework.app.dto.cargomatter.CargoMatterAddDto;
import com.zrytech.framework.app.dto.cargomatter.CargoMatterNeedApproveUpdateDto;
import com.zrytech.framework.app.dto.cargomatter.CargoMatterUpdateDto;
import com.zrytech.framework.app.entity.CarCargoOwnner;
import com.zrytech.framework.app.entity.Cargo;
import com.zrytech.framework.app.entity.CargoMatter;
import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.app.entity.Waybill;
import com.zrytech.framework.app.mapper.CargoMapper;
import com.zrytech.framework.app.mapper.CargoMatterMapper;
import com.zrytech.framework.app.service.ApproveLogService;
import com.zrytech.framework.app.service.CargoMatterService;
import com.zrytech.framework.app.service.CargoService;
import com.zrytech.framework.base.entity.PageData;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.entity.User;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.base.util.RequestUtil;
import com.zrytech.framework.base.util.TradeNoUtil;


/**
 * 报价单
 * @author cat
 *
 */
@Service
public class CargoMatterServiceImpl implements CargoMatterService {

	@Autowired
	private CargoMatterRepository cargoMatterRepository;

	@Autowired
	private CargoMatterMapper cargoMatterMapper;

	@Autowired
	private CarCargoOwnnerRepository carCargoOwnnerRepository;

	@Autowired
	private CargoRepository cargoRepository;

	@Autowired
	private CargoService cargoService;
	
	@Autowired
	private ApproveLogService approveLogService;
	
	@Autowired
	private TradeNoUtil tradeNoUtil;
	
	@Autowired
	private WaybillRepository waybillRepository;
	
	@Autowired
	private CargoMapper cargoMapper;
	 
	 
	
	@Override
	public PageData<CargoMatter> cargoMatterPage(CargoMatterPageDto dto, Integer pageNum, Integer pageSize) {
		com.github.pagehelper.Page<Object> result = PageHelper.startPage(pageNum, pageSize);
		List<CargoMatter> list = cargoMatterMapper.selectSelective(dto);
		for (CargoMatter cargoMatter : list) {
			cargoMatter = this.bindingCarCargoOwnerName(cargoMatter);
		}
		return new PageData<CargoMatter>(result.getPageSize(), result.getPageNum(), result.getTotal(), list);
	}
	
	
	@Override
	public ServerResponse adminDetails(DetailsDto dto) {
		CargoMatter cargoMatter = this.assertCargoMatterExist(dto.getId());
		cargoMatter = this.bindingCarCargoOwnerName(cargoMatter);
		cargoMatter = this.bindingCargo(cargoMatter);
		CargoMatterNeedApproveUpdateDto temp = JSON.parseObject(cargoMatter.getApproveContent(),
				CargoMatterNeedApproveUpdateDto.class);
		cargoMatter.setApproveContentCN(temp);
		return ServerResponse.successWithData(cargoMatter);
	}
	
	@Override
	public ServerResponse adminApprove(ApproveDto dto, User user) {
		CargoMatter cargoMatter = this.assertCargoMatterExist(dto.getBusinessId());
		if(!ApproveConstants.STATUS_APPROVAL_PENDING.equalsIgnoreCase(cargoMatter.getApproveStatus())) {
			throw new BusinessException(112, "审批失败：报价单的状态不是待审批");
		}
		this.approve(cargoMatter, ApproveConstants.RESULT_AGREE.equals(dto.getResult()));
		approveLogService.addApproveLog(dto, user.getId(), ApproveLogConstants.APPROVE_TYPE_CARGO_MATTER);
		return ServerResponse.successWithData("审批成功");
	}
	
	
	/**
	 * 报价单的审批
	 * @author cat
	 * 
	 * @param cargoMatter
	 * @param result
	 */
	private void approve(CargoMatter cargoMatter, Boolean result) {
		Integer id = cargoMatter.getId();
		if (result) {
			CargoMatterNeedApproveUpdateDto temp = JSON.parseObject(cargoMatter.getApproveContent(),
					CargoMatterNeedApproveUpdateDto.class);
			BeanUtils.copyProperties(temp, cargoMatter);
			cargoMatter.setApproveStatus(ApproveConstants.STATUS_BE_APPROVED);
			cargoMatter.setId(id);
			if (cargoMatter.getStatus().equalsIgnoreCase(CargoMatterConstants.CARGO_MATTER_STATUS_UNCERTIFIED)) {
				cargoMatter.setStatus(CargoMatterConstants.CARGO_MATTER_STATUS_RELEASE);
			}
		} else {
			cargoMatter.setApproveStatus(ApproveConstants.STATUS_NOT_APPROVED);
		}
		cargoMatterRepository.save(cargoMatter);
	}
	
	
	/**
	 * 为报价单设置车主、货主企业名称
	 * @author cat
	 * 
	 * @param cargoMatter
	 * @return
	 */
	CargoMatter bindingCarCargoOwnerName(CargoMatter cargoMatter) {
		Integer carOwnerId = cargoMatter.getCarOwnnerId();
		if (carOwnerId != null) {
			cargoMatter.setCarOwnerName(carCargoOwnnerRepository.findNameById(carOwnerId));
		}
		Integer cargoOwnerId = cargoMatter.getCargoOwnerId();
		if (cargoOwnerId != null) {
			cargoMatter.setCargoOwnerName(carCargoOwnnerRepository.findNameById(cargoOwnerId));
		}
		return cargoMatter;
	}
	
	/**
	 * 为报价单设置货源
	 * @author cat
	 * 
	 * @param cargoMatter
	 * @return
	 */
	CargoMatter bindingCargo(CargoMatter cargoMatter) {
		Integer cargoId = cargoMatter.getCargoId();
		if (cargoId != null) {
			Cargo cargo = cargoRepository.findOne(cargoId);
			cargoMatter.setCargo(cargo);
		}
		return cargoMatter;
	}
	
	/**
	 * 断言报价单存在
	 * @author cat
	 * 
	 * @param id	报价单Id
	 * @return
	 */
	private CargoMatter assertCargoMatterExist(Integer id) {
		CargoMatter cargoMatter = cargoMatterRepository.findOne(id);
		if (cargoMatter == null) {
			throw new BusinessException(112, "报价单不存在");
		}
		return cargoMatter;
	}
	
	/**
	 * 断言报价单属于当前登录车主
	 * 
	 * @param cargoMatter	报价单
	 * @param carOwnerId	车主Id
	 */
	private void assertCargoMatterBelongToCurrentUser(CargoMatter cargoMatter, Integer carOwnerId) {
		if (!cargoMatter.getCarOwnnerId().equals(carOwnerId)) {
			throw new BusinessException(112, "报价单不存在");
		}
	}

	
	
	// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	// 前台车主

	@Transactional
	@Override
	public ServerResponse delete(CommonDto dto) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		Integer carOwnerId = customer.getCarOwner().getId();
		CargoMatter cargoMatter = this.assertCargoMatterExist(dto.getId());
		this.assertCargoMatterBelongToCurrentUser(cargoMatter, carOwnerId);

		String status = cargoMatter.getStatus();
		if (CargoMatterConstants.CARGO_MATTER_STATUS_TENDER.equalsIgnoreCase(status)) {
			throw new BusinessException(112, "删除失败：不能删除已中标的报价单");
		}
		cargoMatterRepository.delete(dto.getId());

		return ServerResponse.successWithData("报价单删除成功");
	}

	@Transactional
	@Override
	public ServerResponse update(CargoMatterUpdateDto dto) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		CarCargoOwnner carOwner = customer.getCarOwner();

		CargoMatter cargoMatter = this.assertCargoMatterExist(dto.getId());
		this.assertCargoMatterBelongToCurrentUser(cargoMatter, carOwner.getId());

		BeanUtils.copyProperties(dto, cargoMatter);
		cargoMatterRepository.save(cargoMatter);

		return ServerResponse.successWithData("报价单修改成功");
	}

	@Transactional
	@Override
	public ServerResponse add(CargoMatterAddDto dto) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		CarCargoOwnner carOwner = customer.getCarOwner();
		Integer carOwnerId = carOwner.getId();

		Cargo cargo = cargoService.assertCargoAvailable(dto.getCargoId());

		// 一个车主针对一个货源只能有一个报价单
		List<CargoMatter> list = cargoMatterRepository.findByCargoIdAndCarOwnnerId(cargo.getId(), carOwnerId);
		if (!list.isEmpty()) {
			throw new BusinessException(112, "报价失败：请勿重复报价");
		}

		// 仅发布中的货源可以报价
		String status = cargo.getStatus();
		if (!CargoConstant.CARGO_SOURCE_STATUS_RELEASE.equalsIgnoreCase(status)) {
			throw new BusinessException(112, "报价失败：货源状态不是发布中");
		}

		// 新建报价单
		CargoMatter cargoMatter = new CargoMatter();
		BeanUtils.copyProperties(dto, cargoMatter);
		cargoMatter.setCarOwnnerId(carOwnerId);
		cargoMatter.setCargoOwnerId(cargo.getCreateBy());
		cargoMatter.setStatus(CargoMatterConstants.CARGO_MATTER_STATUS_RELEASE);
		cargoMatter.setCreateBy(customer.getId());
		cargoMatter.setCreateDate(new Date());

		cargoMatterRepository.save(cargoMatter);

		return ServerResponse.successWithData("报价成功");
	}

	@Override
	public ServerResponse carOwnerCargoMatterPage(CargoMatterPageDto dto, Integer pageNum, Integer pageSize) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		CarCargoOwnner carOwner = customer.getCarOwner();
		Integer carOwnerId = carOwner.getId();
		dto.setCarOwnerId(carOwnerId);
		com.github.pagehelper.Page<Object> result = PageHelper.startPage(pageNum, pageSize);
		List<CargoMatter> list = cargoMatterMapper.selectSelective(dto);
		for (CargoMatter cargoMatter : list) {
			cargoMatter = this.bindingCarCargoOwnerName(cargoMatter);
		}
		PageData<CargoMatter> pageData = new PageData<CargoMatter>(result.getPageSize(), result.getPageNum(),
				result.getTotal(), list);
		return ServerResponse.successWithData(pageData);
	}

	@Override
	public ServerResponse carOwnerCargoMatterDetails(CommonDto dto) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		CarCargoOwnner carOwner = customer.getCarOwner();

		CargoMatter cargoMatter = this.assertCargoMatterExist(dto.getId());
		this.assertCargoMatterBelongToCurrentUser(cargoMatter, carOwner.getId());

		cargoMatter = this.bindingCarCargoOwnerName(cargoMatter);
		cargoMatter = this.bindingCargo(cargoMatter);

		return ServerResponse.successWithData(cargoMatter);
	}

	
	
	
	
	// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	// 前台货主
	
	@Override
	public ServerResponse cargoOwnerGetCargoMatterByCargoId(CargoMatterPageDto dto) {
		Integer cargoId = dto.getCargoId();
		if (cargoId == null) {
			throw new BusinessException(112, "货源Id不能为空");
		}

		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		Integer cargoOwnerId = customer.getCargoOwner().getId();

		dto.setCargoOwnerId(cargoOwnerId);
		List<CargoMatter> list = cargoMatterMapper.selectSelective(dto);

		Map<String, Object> result = new HashMap<>();
		result.put("total", list.size());
		result.put("list", list);
		return ServerResponse.successWithData(result);
	}
	
	
	@Transactional
	@Override
	public ServerResponse tender(CommonDto dto) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		Integer cargoOwnerId = customer.getCargoOwner().getId();

		CargoMatter cargoMatter = this.assertCargoMatterExist(dto.getId());

		if (!cargoMatter.getCargoOwnerId().equals(cargoOwnerId)) {
			throw new BusinessException(112, "中标失败：参数有误");
		}

		if (!CargoMatterConstants.CARGO_MATTER_STATUS_RELEASE.equalsIgnoreCase(cargoMatter.getStatus())) {
			throw new BusinessException(112, "中标失败：报价单的状态不是应标中");
		}

		Integer cargoId = cargoMatter.getCargoId();
		Cargo cargo = cargoRepository.findOne(cargoId);
		if (!CargoConstant.CARGO_SOURCE_STATUS_RELEASE.equalsIgnoreCase(cargo.getStatus())) {
			throw new BusinessException(112, "中标失败：货源的状态不是发布中");
		}

		// 将当前报价单状态修改为：已中标，将货源的其他运单状态修改为：未中标
		cargoMatter.setStatus(CargoMatterConstants.CARGO_MATTER_STATUS_TENDER);
		cargoMatter.setLoadDate(new Date());
		cargoMatterRepository.save(cargoMatter);
		cargoMatterMapper.updateStatusByIdAndCargoId(cargoMatter.getId(), cargoId);

		// 生成运单
		Waybill waybill = new Waybill();
		waybill.setBillNo(tradeNoUtil.genTradeNo());
		waybill.setCargoId(cargoId);
		waybill.setCargoOwnnerId(cargoOwnerId);
		waybill.setCarOwnnerId(cargoMatter.getCarOwnnerId());
		if (cargo.getTenderWay().equalsIgnoreCase(CargoConstant.BID_MARK)) {
			waybill.setTotalMoney(cargo.getMatterPrice());
		} else {
			waybill.setTotalMoney(cargoMatter.getMatterPrice());
		}
		waybill.setPayType(cargo.getPayType());
		waybill.setQty(cargo.getQty());
		waybill.setWeightUnit(cargo.getWeightUnit());
		waybill.setStatus(CargoConstant.AWAIT_GENERATE);
		waybill.setCreateDate(new Date());
		waybill.setCreateBy(customer.getId());
		waybillRepository.save(waybill);

		// 修改货源的状态
		cargoMapper.updateStatusById(cargoId, CargoConstant.CARGO_SOURCE_STATUS_COMPLETED);

		return ServerResponse.success();
	}
	
	
}
