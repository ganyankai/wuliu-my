package com.zrytech.framework.app.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.app.constants.BillLocationConstants;
import com.zrytech.framework.app.constants.CargoConstant;
import com.zrytech.framework.app.constants.IndentConstants;
import com.zrytech.framework.app.dao.CargoDao;
import com.zrytech.framework.app.dto.DeleteDto;
import com.zrytech.framework.app.dto.DetailsDto;
import com.zrytech.framework.app.dto.WaybillDto;
import com.zrytech.framework.app.entity.*;
import com.zrytech.framework.app.enums.LogisticsResult;
import com.zrytech.framework.app.enums.LogisticsResultEnum;
import com.zrytech.framework.app.utils.CheckFieldUtils;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.base.service.impl.ServiceImpl;
import com.zrytech.framework.base.util.BeanUtil;
import com.zrytech.framework.base.util.RequestUtil;
import com.zrytech.framework.base.util.TradeNoUtil;
import com.zrytech.framework.common.entity.SysCustomer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.zrytech.framework.app.dto.WaybillPageDto;
import com.zrytech.framework.app.dto.billlocation.BillLocationAddDto;
import com.zrytech.framework.app.dto.waybill.CarOwnerWaybillPageDto;
import com.zrytech.framework.app.dto.waybilldetail.WaybillDetailAddDto;
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
import org.springframework.transaction.annotation.Propagation;

/**
 * 运单
 *
 * @author cat
 */
@Service
@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class WaybillServiceImpl extends ServiceImpl<WaybillRepository,Waybill,Integer> implements WaybillService {

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

   /* @Autowired
    private WaybillDao waybillDao;*/

    @Autowired
    private CargoDao cargoDao;

    @Autowired
    private CarService carService;

    @Autowired
    private CarPersonService carPersonService;

    @Autowired
    private BillLocationService billLocationService;

    @Autowired
    private WaybillDetailService waybillDetailService;


    /**
     * 运单分页
     *
     * @param dto      查询条件，详见{@link WaybillPageDto}
     * @param pageNum
     * @param pageSize
     * @return
     * @author cat
     */
    @Override
    public ServerResponse page(WaybillPageDto dto, Integer pageNum, Integer pageSize) {
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
     *
     * @param id
     * @return
     * @author cat
     */
    @Override
    public ServerResponse details(Integer id) {
        Waybill waybill = waybillRepository.findOne(id);
        waybill = bindingCargo(waybill);
        waybill = bindingCarOwnerName(waybill);
        waybill = bindingCargoOwnerName(waybill);
        waybill = bindingWaybillDetail(waybill);
        waybill = bindingEvaluate(waybill);
        return ServerResponse.successWithData(waybill);
    }

    @Autowired
    private TradeNoUtil tradeNoUtil;

    /**
     * @return
     * @Desinition:创建运单
     * @param:requestParams
     * @param:WaybillDto运单dto
     */
    @Override
    public ServerResponse createIndent(WaybillDto waybillDto) {
        CheckFieldUtils.checkObjecField(waybillDto.getCargoId());
        CheckFieldUtils.checkObjecField(waybillDto.getCarOwnnerId());
        SysCustomer sysCustomer = RequestUtil.getCurrentUser(SysCustomer.class);
        Waybill waybill = BeanUtil.copy(waybillDto, Waybill.class);
        Cargo cargo = cargoDao.get(waybill.getCargoId());
        waybill.setBillNo(tradeNoUtil.genTradeNo());
        waybill.setStatus(CargoConstant.AWAIT_GENERATE);
        waybill.setCreateDate(new Date());
        waybill.setCreateBy(sysCustomer.getId());//货主ID
        waybill.setCargoOwnnerId(sysCustomer.getId());//TODO:认证材料ID
        waybill.setPayType(cargo.getPayType());
        supplementWabill(cargo, waybill);
        int num = waybillMapper.insert(waybill);
        CheckFieldUtils.assertSuccess(num);
        //TODO:修改货源状态以及报价单状态
        updateCargoAndMatter(cargo, waybillDto.getCarOwnnerId());
        return ServerResponse.success();
    }

    public void updateCargoAndMatter(Cargo cargo, Integer carId) {
        Offer offer = cargoDao.getOfferWill(cargo.getId(), carId);//报价单价格:应标价格
        cargo.setStatus(CargoConstant.SOURCE_WINNING);
        cargo.setRealPrice(offer.getMatterPrice());
        cargoDao.updateAudit(cargo);//修改货源状态以及中标时的价格;
        int num = cargoDao.updateMatter(cargo.getId(), CargoConstant.OFFER_DRAFT, null);//修改报价单状态;将其余没有中标变为草稿状态;
        if (num > 0) {
            cargoDao.updateMatter(cargo.getId(), CargoConstant.OFFER_PROMISSED, carId);//将车主报价单变为中标状态;
        }
    }

    public void supplementWabill(Cargo cargo, Waybill waybill) {
        //Offer offer = cargoDao.getOfferWill(waybill.getCargoId(), waybill.getCarOwnnerId());
        waybill.setQty(cargo.getQty());
        waybill.setWeightUnit(cargo.getWeightUnit());
    }


    /**
     * @return
     * @Desinition:待确认运单
     * @param:requestParams
     * @param:WaybillDto运单dto
     */
    @Override
    public ServerResponse confirmIndent(WaybillDto waybillDto) {
        Waybill bill = waybillMapper.get(waybillDto.getId());
        if (bill == null || bill.getStatus() == null || !bill.getStatus().equalsIgnoreCase(CargoConstant.AWAIT_DETERMINE)) {
            throw new BusinessException(new LogisticsResult(LogisticsResultEnum.INDENT_STATUS_ERROR));
        }
        Waybill waybill = BeanUtil.copy(waybillDto, Waybill.class);
        waybill.setStatus(CargoConstant.AWAIT_LOADING);
        int num = waybillMapper.updateIndentStatus(waybill);
        CheckFieldUtils.assertSuccess(num);
        return ServerResponse.success();
    }

    /**
     * @return
     * @Desinition:运单分页列表展示
     * @param:requestParams
     * @param:WaybillDto运单dto
     */
    @Override
    public ServerResponse indentPage(WaybillDto waybillDto, Page page) {
        CheckFieldUtils.checkObjecField(waybillDto.getCargoOwnnerId());
        Waybill waybill = BeanUtil.copy(waybillDto, Waybill.class);
        if(page==null){
            page=new Page();
        }
        PageInfo<Waybill> pageInfo = waybillMapper.indentPage(waybill, page);
        return ServerResponse.successWithData(pageInfo);
    }

    /**
     * @return
     * @Desinition:运单统计
     * @param:requestParams
     * @param:WaybillDto运单dto
     */
    @Override
    public ServerResponse coundIndent(WaybillDto waybillDto) {
        CheckFieldUtils.checkObjecField(waybillDto.getCargoOwnnerId());
        List<String> typeCount = waybillMapper.coundIndent(waybillDto.getCargoOwnnerId());
        Map<String, Object> map = countList(typeCount);
        return ServerResponse.successWithData(map);
    }

    /**
     * @return
     * @Desinition:运单详情
     * @param:requestParams
     * @param:WaybillDto运单dto
     */
    @Override
    public ServerResponse get(WaybillDto waybillDto) {
        CheckFieldUtils.checkObjecField(waybillDto.getId());
        Waybill waybill = waybillMapper.get(waybillDto.getId());
        if (waybill != null && waybill.getCargoId() != null) {
            Cargo cargo = cargoDao.get(waybill.getCargoId());
            waybill.setCargo(cargo);
        }
        return ServerResponse.successWithData(waybill);
    }

    /**
     * @return
     * @Desinition:更改运单
     * @param:requestParams
     * @param:WaybillDto运单dto
     */
    @Override
    public ServerResponse changeIndent(WaybillDto waybillDto) {
        CheckFieldUtils.checkObjecField(waybillDto.getTotalMoney());
        Waybill bill=waybillMapper.get(waybillDto.getId());
        if(bill.getStatus().equalsIgnoreCase(CargoConstant.AWAIT_LOADING)|| bill.getStatus().equalsIgnoreCase(CargoConstant.AWAIT_ACCEPT)|| bill.getStatus().equalsIgnoreCase(CargoConstant.SIGN_PAY)){
            Waybill waybill = BeanUtil.copy(waybillDto, Waybill.class);
            int num = waybillMapper.changeIndent(waybill);
            CheckFieldUtils.assertSuccess(num);
        }else{
            throw new BusinessException(new LogisticsResult(LogisticsResultEnum.INDENT_CHANGE_ERROR));
        }
        return ServerResponse.success();
    }

    /**
     * @return
     * @Desinition:删除运单
     * @param:requestParams
     * @param:WaybillDto运单dto
     */
    @Override
    public ServerResponse delete(WaybillDto waybillDto) {
        Waybill waybill = waybillMapper.get(waybillDto.getId());
        if (CargoConstant.AWAIT_GENERATE.equalsIgnoreCase(waybill.getStatus())) {
            int num = waybillMapper.delete(waybillDto.getId());
            CheckFieldUtils.assertSuccess(num);
            return ServerResponse.success();
        } else {
            throw new BusinessException(new LogisticsResult(LogisticsResultEnum.DELETE_WAYBILL_FAIL));
        }
    }

    public Map<String, Object> countList(List<String> typeCount) {
        Map<String, Object> map = new HashMap<>();
        if (typeCount != null && typeCount.size() > 0) {
            for (String str : typeCount) {
                String[] arr = str.split("\\$");
                if (arr[0].equalsIgnoreCase(CargoConstant.AWAIT_GENERATE)) {
                    map.put(CargoConstant.AWAIT_GENERATE, arr[1] == null ? 0 : Integer.parseInt(arr[1]));
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
        }
        return map;
    }

    /**
     * 为运单绑定运单项数据
     *
     * @param waybill
     * @return
     * @author cat
     */
    public Waybill bindingWaybillDetail(Waybill waybill) {
        String billNo = waybill.getBillNo();
        if (StringUtils.isNoneEmpty(billNo)) {
            List<WaybillDetail> waybillDetails = waybillDetailRepository.findByBillNo(billNo);
            for (WaybillDetail waybillDetail : waybillDetails) {
                waybillDetail = bindingBillLocation(waybillDetail);
            }
            waybill.setWaybillDetails(waybillDetails);
        }
        return waybill;
    }

    /**
     * @Desintion:Map数据校验
     * @param:map
     * @Author:Jxx
     */
    public void checkMap(Map<String, Object> map) {
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
    }

    /**
     * 为运单项绑定装卸地数据
     *
     * @param waybillDetail
     * @return
     * @author cat
     */
    public WaybillDetail bindingBillLocation(WaybillDetail waybillDetail) {
        Integer id = waybillDetail.getId();
        if (id != null) {
            List<BillLocation> billLocations = billLocationRepository.findByWaybillDetailId(id);
            waybillDetail.setBillLocations(billLocations);
        }
        return waybillDetail;
    }


    /**
     * 为运单设置货源
     *
     * @return
     * @param:cargoMatter
     * @author cat
     */
    public Waybill bindingCargo(Waybill waybill) {
        Integer cargoId = waybill.getCargoId();
        if (cargoId != null) {
            Cargo cargo = cargoRepository.findOne(cargoId);
            waybill.setCargo(cargo);
        }
        return waybill;
    }


    /**
     * 为运单绑定车主企业名称
     *
     * @param waybill
     * @return
     * @author cat
     */
    public Waybill bindingCarOwnerName(Waybill waybill) {
        Integer carOwnerId = waybill.getCarOwnnerId();
        if (carOwnerId != null) {
            waybill.setCarOwnerName(carCargoOwnnerRepository.findNameById(carOwnerId));
        }
        return waybill;
    }


    /**
     * 为运单绑定货主企业名称
     *
     * @param waybill
     * @return
     * @author cat
     */
    public Waybill bindingCargoOwnerName(Waybill waybill) {
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
    public Waybill bindingEvaluate(Waybill waybill) {
        String billNo = waybill.getBillNo();
        if (StringUtils.isNoneEmpty(billNo)) {
            List<Evaluate> evaluates = evaluateRepository.findByBillNo(billNo);
            waybill.setEvaluates(evaluates);
        }
        return waybill;
    }


    /**
     * 断言运单存在
     *
     * @param id 运单Id
     * @return
     * @author cat
     */
    public Waybill assertWaybillExist(Integer id) {
        Waybill waybill = waybillRepository.findOne(id);
        if (waybill == null) {
            throw new BusinessException(112, "运单不存在");
        }
        return waybill;
    }


    /**
     * 断言运单属于当前登录车主
     *
     * @param watbill    运单
     * @param carOwnerId 车主Id
     * @author cat
     */
    private void assertWaybillBelongToCurrentUser(Waybill waybill, Integer carOwnerId) {
        if (!waybill.getCarOwnnerId().equals(carOwnerId)) {
            throw new BusinessException(112, "参数有误");
        }
    }


    /**
     * 新增运单项
     *
     * @param dto
     * @param customer
     * @return
     * @author cat
     */
    @Override
    public ServerResponse addWaybillDetail(WaybillDetailAddDto dto, Customer customer) {
        // 验证运单项数量与装卸地总数量是否相等
        this.checkQty(dto);
        CarCargoOwnner carOwner = customer.getCarOwner();
        if (customer.getCreateBy() != 0) { // 子账号
            // TODO 鉴权

        }
        Integer carOwnerId = carOwner.getId();
        Waybill waybill = this.assertWaybillExist(dto.getWaybillId());
        this.assertWaybillBelongToCurrentUser(waybill, carOwnerId);
        this.waybillDetailCheck(dto, carOwnerId);
        // 新增运单项
        WaybillDetail waybillDetail = this.insertWaybillDetail(dto, waybill);
        // 新增装卸地
        this.insertBillLocation(dto, waybill, waybillDetail.getId());
        return ServerResponse.successWithData("添加成功");
    }


    /**
     * 验证运单项数量与装卸地总数量是否相等
     *
     * @param dto
     */
    private void checkQty(WaybillDetailAddDto dto) {
        Integer qty = dto.getQty(); // 运单项数量
        List<BillLocationAddDto> billLocations = dto.getBillLocations();
        int count = 0;
        for (BillLocationAddDto billLocationAddDto : billLocations) {
            count = count + billLocationAddDto.getQty();// 装卸地总数量
        }
        if (qty - count != 0) {
            throw new BusinessException(112, "参数有误：运单项数量与装卸地总数量不相等");
        }
    }


    /**
     * 新增运单项
     *
     * @param dto
     * @param waybill
     * @return
     * @author cat
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


    /**
     * 新增装卸地
     *
     * @param dto
     * @param waybill
     * @param waybillDetailId
     * @author cat
     */
    private void insertBillLocation(WaybillDetailAddDto dto, Waybill waybill, Integer waybillDetailId) {
        List<BillLocation> list = new ArrayList<>();
        List<BillLocationAddDto> billLocations = dto.getBillLocations();
        for (BillLocationAddDto billLocationAddDto : billLocations) {
            BillLocation billLocation = new BillLocation();
            BeanUtils.copyProperties(billLocationAddDto, billLocation);
            billLocation.setCreateDate(new Date());
            billLocation.setStatus(BillLocationConstants.STATUS_DEFAULT);
            billLocation.setWaybillDetailId(waybillDetailId);
            billLocation.setWaybillId(waybill.getId());
            billLocation.setWeightUnit(waybill.getWeightUnit());
            list.add(billLocation);
        }
        billLocationRepository.save(list);
    }


    /**
     * 判断入参 车辆、司机、压货人 是否属于当前登录车主
     *
     * @param dto        车辆、司机、压货人Id
     * @param carOwnerId 车主Id
     * @author cat
     */
    private void waybillDetailCheck(WaybillDetailAddDto dto, Integer carOwnerId) {
        Integer driverId = dto.getDriverId();
        Integer supercargoId = dto.getSupercargoId();
        Integer carId = dto.getCarId();
        if (driverId != null) {
            carPersonService.assertDriverBelongToCurrentUser(driverId, carOwnerId);
        }
        if (supercargoId != null) {
            carPersonService.assertSupercargoBelongToCurrentUser(supercargoId, carOwnerId);
        }
        if (carId != null) {
            carService.assertCarBelongToCurrentUser(carId, carOwnerId);
        }
    }


    /**
     * @return
     * @Desinition:取消运单
     * @param:requestParams
     * @param:WaybillDto运单dto
     */
    @Override
    public ServerResponse cancelIndent(WaybillDto waybillDto) {
        Waybill waybill = waybillMapper.get(waybillDto.getId());
        if (!CargoConstant.AWAIT_GENERATE.equalsIgnoreCase(waybill.getStatus())) {
            throw new BusinessException(new LogisticsResult(LogisticsResultEnum.CANCEL_NOT_FAIL));
        }
        waybill.setStatus(IndentConstants.CANCEL_STATUS_CANCELLED);
        waybillMapper.updateIndentStatus(waybill);
        //TODO:取消订单是否需要后台管理员同意;订单取消成功后,货源状态改为什么
        return ServerResponse.success();
    }

    /**
     * @return
     * @Desinition:签收订单(已收货待支付状态)
     * @param:requestParams
     * @param:WaybillDto运单dto
     */
    @Override
    public ServerResponse signAccpet(WaybillDto waybillDto) {
         Waybill waybill=waybillMapper.get(waybillDto.getId());
         if(waybill==null|| waybill.getStatus()==null|| !waybill.getStatus().equalsIgnoreCase(CargoConstant.AWAIT_ACCEPT)){
             throw new BusinessException(new LogisticsResult(LogisticsResultEnum.INDENT_SIGN_ERROR));
         }
        waybill.setStatus(CargoConstant.SIGN_PAY);
        waybillMapper.updateIndentStatus(waybill);
        return ServerResponse.success();
    }


    /**
     * 车主及车主子账号 - 删除运单装卸地（物理删除）
     *
     * @param dto      运单装卸地Id
     * @param customer 当前登录人
     * @return
     * @author cat
     */
    @Transactional
    @Override
    public ServerResponse deleteBillLocation(DeleteDto dto, Customer customer) {
        CarCargoOwnner carOwner = customer.getCarOwner();
        if (customer.getCreateBy() != 0) { // 子账号
            // TODO 鉴权

        }
        BillLocation billLocation = billLocationService.assertBillLocationExist(dto.getId());
        Waybill waybill = this.assertWaybillExist(billLocation.getWaybillId());
        this.assertWaybillBelongToCurrentUser(waybill, carOwner.getId());

        billLocationRepository.delete(dto.getId());
        return ServerResponse.successWithData("删除成功");
    }


    /**
     * 车主及车主子账号 - 删除运单项及运单项下的装卸地（物理删除）
     *
     * @param dto      运单项Id
     * @param customer 当前登录人
     * @return
     * @author cat
     */
    @Override
    public ServerResponse deleteWaybillDetail(DeleteDto dto, Customer customer) {
        CarCargoOwnner carOwner = customer.getCarOwner();
        if (customer.getCreateBy() != 0) { // 子账号
            // TODO 鉴权

        }

        Integer waybillDetailId = dto.getId();
        WaybillDetail waybillDetail = waybillDetailService.assertWaybillDetailExist(dto.getId());
        Waybill waybill = waybillRepository.findByBillNo(waybillDetail.getBillNo());
        if (waybill == null) {
            throw new BusinessException(112, "运单项不存在");
        }
        this.assertWaybillBelongToCurrentUser(waybill, carOwner.getId());

        // 删除运单项
        waybillDetailRepository.delete(waybillDetailId);
        // 删除运单项对应的装卸地
        billLocationService.deleteByWaybillDetailId(waybillDetailId);

        return ServerResponse.successWithData("删除成功");
    }


    /**
     * 运单分页
     *
     * @param dto      查询条件，详见{@link CarOwnerWaybillPageDto}
     * @param pageNum
     * @param pageSize
     * @return
     * @author cat
     */
    @Override
    public ServerResponse page(CarOwnerWaybillPageDto dto, Integer pageNum, Integer pageSize, Customer customer) {
        CarCargoOwnner carOwner = customer.getCarOwner();
        if (customer.getCreateBy() != 0) { // 子账号
            dto.setCreateBy(customer.getCreateBy());
        }
        com.github.pagehelper.Page<Object> result = PageHelper.startPage(pageNum, pageSize);
        List<Waybill> list = waybillMapper.carOwnerSelectSelective(dto, carOwner.getId());
        for (Waybill waybill : list) {
            waybill = bindingCarOwnerName(waybill);
            waybill = bindingCargoOwnerName(waybill);
        }
        PageData<Waybill> pageData = new PageData<Waybill>(result.getPageSize(), result.getPageNum(), result.getTotal(), list);
        return ServerResponse.successWithData(pageData);
    }


    /**
     * 运单详情
     *
     * @param id 运单Id
     * @return
     * @author cat
     */
    @Override
    public ServerResponse details(DetailsDto dto, Customer customer) {
        CarCargoOwnner carOwner = customer.getCarOwner();
        Waybill waybill = this.assertWaybillExist(dto.getId());
        this.assertWaybillBelongToCurrentUser(waybill, carOwner.getId());
        if (customer.getCreateBy() != 0) { // 子账号
            if (customer.getCreateBy() - waybill.getCreateBy() != 0) {
                throw new BusinessException(112, "参数有误");
            }
        }
        waybill = bindingCargo(waybill);
        waybill = bindingCarOwnerName(waybill);
        waybill = bindingCargoOwnerName(waybill);
        waybill = bindingWaybillDetail(waybill);
        waybill = bindingEvaluate(waybill);
        return ServerResponse.successWithData(waybill);
    }

}
