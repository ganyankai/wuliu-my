package com.zrytech.framework.app.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zrytech.framework.app.constants.CargoConstant;
import com.zrytech.framework.app.dto.evaluate.EvaluateAddDto;
import com.zrytech.framework.app.dto.evaluate.EvaluateSearchDto;
import com.zrytech.framework.app.entity.CarCargoOwnner;
import com.zrytech.framework.app.entity.Customer;
import com.zrytech.framework.app.entity.Evaluate;
import com.zrytech.framework.app.entity.Waybill;
import com.zrytech.framework.app.mapper.EvaluateMapper;
import com.zrytech.framework.app.repository.CarCargoOwnnerRepository;
import com.zrytech.framework.app.repository.EvaluateRepository;
import com.zrytech.framework.app.repository.WaybillRepository;
import com.zrytech.framework.app.service.EvaluateService;
import com.zrytech.framework.app.service.WaybillService;
import com.zrytech.framework.base.entity.PageData;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.base.util.RequestUtil;

@Service
public class EvaluateServiceImpl implements EvaluateService {
	
	@Autowired
	private EvaluateMapper mapper;
	
	@Autowired 
	private EvaluateRepository repository;
	
	@Autowired 
	private WaybillService waybillService;
	
	@Autowired
	private WaybillRepository waybillRepository;
	
	@Autowired
	private CarCargoOwnnerRepository carCargoOwnnerRepository;
	
	
	
	/**
	 * 确认运单可以被评价
	 * @author cat
	 * 
	 * @param waybill	待评价的运单
	 * @param appraiserId	评价人Id
	 * @param evaluateType	评价类型
	 */
	private void assertWaybillCanBeEvaluated(Waybill waybill, Integer appraiserId, String evaluateType) {
		if (!CargoConstant.WAYBILL_STATUS_WAIT_COMMENT.equalsIgnoreCase(waybill.getStatus())) {
			throw new BusinessException(112, "评价失败：运单状态不是待评价");
		}

		Evaluate evaluate = repository.findByWaybillIdAndAppraiserIdAndEvaluateType(waybill.getId(), appraiserId,
				evaluateType);
		if (evaluate != null) {
			throw new BusinessException(112, "评价失败：已经评价过了");
		}
	}
	
	/**
	 * 保存评价信息
	 * @author cat
	 * 
	 * @param dto	前端传入的评价信息
	 * @param appraiserId	评价人
	 * @param waybill	运单
	 * @param evaluateType	评价类型
	 * @return
	 */
	private Evaluate saveEvaluate(EvaluateAddDto dto, Integer appraiserId, Integer appraiserById, Waybill waybill, String evaluateType) {
		Evaluate evaluate = new Evaluate();
		BeanUtils.copyProperties(dto, evaluate);
		evaluate.setAppraiserId(appraiserId);
		evaluate.setAppraiserById(appraiserById);
		evaluate.setBillNo(waybill.getBillNo());
		evaluate.setCreateDate(new Date());
		evaluate.setEvaluateType(evaluateType);
		repository.save(evaluate);
		return evaluate;
	}
	
	/**
	 * 如果运单已经互评，修改运单状态
	 * @author cat
	 * 
	 * @param waybillId	运单Id
	 * @param appraiserId	评价人Id
	 * @param evaluateType	评价类型
	 */
	private void updateWayBillStatusWhenMutualEvaluation(Integer waybillId, Integer appraiserId, String evaluateType) {
		Evaluate evaluate = repository.findByWaybillIdAndAppraiserIdAndEvaluateType(waybillId, appraiserId,
				evaluateType);
		if (evaluate != null) {
			waybillRepository.updateStatusById(waybillId, CargoConstant.WAYBILL_STATUS_COMPLETED);
		}
	}
	
	
	
	@Transactional
	@Override
	public ServerResponse carEvaluateCargo(EvaluateAddDto dto) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		CarCargoOwnner carOwner = customer.getCarOwner();
		Integer waybillId = dto.getWaybillId();
		Integer appraiserId = carOwner.getId();

		Waybill waybill = waybillService.assertWaybillBelongToCurrentCarOwner(waybillId, appraiserId);

		this.assertWaybillCanBeEvaluated(waybill, appraiserId, CargoConstant.EVALUATE_TYPE_CAR_COMMENTS_CARGO);

		this.saveEvaluate(dto, appraiserId, waybill.getCargoOwnnerId(), waybill,
				CargoConstant.EVALUATE_TYPE_CAR_COMMENTS_CARGO);

		this.updateWayBillStatusWhenMutualEvaluation(waybillId, waybill.getCargoOwnnerId(),
				CargoConstant.EVALUATE_TYPE_CARGO_COMMENTS_CAR);

		return ServerResponse.success();
	}
	
	
	@Transactional
	@Override
	public ServerResponse cargoEvaluateCar(EvaluateAddDto dto) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		CarCargoOwnner cargoOwner = customer.getCargoOwner();
		Integer waybillId = dto.getWaybillId();
		Integer appraiserId = cargoOwner.getId();

		Waybill waybill = waybillService.assertWaybillBelongToCurrentCargoOwner(waybillId, appraiserId);

		this.assertWaybillCanBeEvaluated(waybill, appraiserId, CargoConstant.EVALUATE_TYPE_CARGO_COMMENTS_CAR);

		this.saveEvaluate(dto, appraiserId, waybill.getCarOwnnerId(), waybill,
				CargoConstant.EVALUATE_TYPE_CARGO_COMMENTS_CAR);

		this.updateWayBillStatusWhenMutualEvaluation(waybillId, waybill.getCarOwnnerId(),
				CargoConstant.EVALUATE_TYPE_CAR_COMMENTS_CARGO);

		return ServerResponse.success();
	}
	
	
	/**
	 * 绑定运单信息
	 * @author cat
	 * 
	 * @param evaluate
	 * @return
	 */
	private Evaluate bindWaybillInfo(Evaluate evaluate) {
		Integer waybillId = evaluate.getWaybillId();
		Waybill waybill = waybillService.assertWaybillExist(waybillId);
		evaluate.setName(waybill.getName());
		evaluate.setQty(waybill.getQty());
		evaluate.setTotalMoney(waybill.getTotalMoney());
		evaluate.setWeightUnit(waybill.getWeightUnit());
		evaluate.setPriceUnit(waybill.getPriceUnit());
		return evaluate;
	}
	
	/**
	 * 绑定评价人被评价人名称
	 * @author cat
	 * 
	 * @param evaluate
	 * @return
	 */
	private Evaluate bindName(Evaluate evaluate) {
		Integer appraiserId = evaluate.getAppraiserId();
		Integer appraiserById = evaluate.getAppraiserById();
		String evaluateType = evaluate.getEvaluateType();
		if (CargoConstant.EVALUATE_TYPE_CARGO_COMMENTS_CAR.equalsIgnoreCase(evaluateType)
				|| CargoConstant.EVALUATE_TYPE_CAR_COMMENTS_CARGO.equalsIgnoreCase(evaluateType)) {
			evaluate.setAppraiserName(carCargoOwnnerRepository.findNameById(appraiserId));
			evaluate.setAppraiserByName(carCargoOwnnerRepository.findNameById(appraiserById));
		}
		return evaluate;
	}
	
	
	/**
	 * 搜索分页
	 * @author cat
	 * 
	 * @param evaluate
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	private PageData<Evaluate> pageSearch(Evaluate evaluate, Integer pageNum, Integer pageSize) {
		Page<Object> result = PageHelper.startPage(pageNum, pageSize);
		List<Evaluate> list = mapper.search(evaluate);
		for (Evaluate temp : list) {
			temp = this.bindWaybillInfo(temp);
			temp = this.bindName(temp);
		}
		PageData<Evaluate> pageData = new PageData<Evaluate>(result.getPageSize(), result.getPageNum(),
				result.getTotal(), list);
		return pageData;
	}
	
	@Override
	public ServerResponse fromCargo(EvaluateSearchDto dto, Integer pageNum, Integer pageSize) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		CarCargoOwnner carOwner = customer.getCarOwner();
		Integer carOwnerId = carOwner.getId();

		Evaluate evaluate = new Evaluate();
		BeanUtils.copyProperties(dto, evaluate);
		evaluate.setAppraiserById(carOwnerId);
		evaluate.setEvaluateType(CargoConstant.EVALUATE_TYPE_CARGO_COMMENTS_CAR);

		PageData<Evaluate> pageData = this.pageSearch(evaluate, pageNum, pageSize);
		return ServerResponse.successWithData(pageData);
	}

	@Override
	public ServerResponse toCargo(EvaluateSearchDto dto, Integer pageNum, Integer pageSize) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		CarCargoOwnner carOwner = customer.getCarOwner();
		Integer carOwnerId = carOwner.getId();

		Evaluate evaluate = new Evaluate();
		BeanUtils.copyProperties(dto, evaluate);
		evaluate.setAppraiserId(carOwnerId);
		evaluate.setEvaluateType(CargoConstant.EVALUATE_TYPE_CAR_COMMENTS_CARGO);

		PageData<Evaluate> pageData = this.pageSearch(evaluate, pageNum, pageSize);
		return ServerResponse.successWithData(pageData);
	}

	@Override
	public ServerResponse fromCar(EvaluateSearchDto dto, Integer pageNum, Integer pageSize) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		CarCargoOwnner cargoOwner = customer.getCargoOwner();
		Integer cargoOwnerId = cargoOwner.getId();

		Evaluate evaluate = new Evaluate();
		BeanUtils.copyProperties(dto, evaluate);
		evaluate.setAppraiserById(cargoOwnerId);
		evaluate.setEvaluateType(CargoConstant.EVALUATE_TYPE_CAR_COMMENTS_CARGO);

		PageData<Evaluate> pageData = this.pageSearch(evaluate, pageNum, pageSize);
		return ServerResponse.successWithData(pageData);
	}

	@Override
	public ServerResponse toCar(EvaluateSearchDto dto, Integer pageNum, Integer pageSize) {
		Customer customer = RequestUtil.getCurrentUser(Customer.class);
		CarCargoOwnner cargoOwner = customer.getCargoOwner();
		Integer cargoOwnerId = cargoOwner.getId();

		Evaluate evaluate = new Evaluate();
		BeanUtils.copyProperties(dto, evaluate);
		evaluate.setAppraiserId(cargoOwnerId);
		evaluate.setEvaluateType(CargoConstant.EVALUATE_TYPE_CARGO_COMMENTS_CAR);

		PageData<Evaluate> pageData = this.pageSearch(evaluate, pageNum, pageSize);
		return ServerResponse.successWithData(pageData);
	}
	
	@Override
	public ServerResponse openPage(EvaluateSearchDto dto, Integer pageNum, Integer pageSize) {
		Integer appraiserById = dto.getAppraiserById();
		if (appraiserById == null) {
			throw new BusinessException(112, "被评价人Id不能为空");
		}
		Evaluate evaluate = new Evaluate();
		evaluate.setAppraiserById(appraiserById);
		PageData<Evaluate> pageData = this.pageSearch(evaluate, pageNum, pageSize);
		return ServerResponse.successWithData(pageData);
	}

}
