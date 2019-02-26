package com.zry.framework.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zry.framework.constants.CargoConstant;
import com.zry.framework.dao.CargoDao;
import com.zry.framework.dao.WaybillDao;
import com.zry.framework.dto.WaybillDto;
import com.zry.framework.enums.LogisticsResult;
import com.zry.framework.enums.LogisticsResultEnum;
import com.zry.framework.utils.CheckFieldUtils;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.base.util.BeanUtil;
import com.zrytech.framework.base.util.TradeNoUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.zry.framework.dto.WaybillPageDto;
import com.zry.framework.entity.BillLocation;
import com.zry.framework.entity.Cargo;
import com.zry.framework.entity.Evaluate;
import com.zry.framework.entity.Waybill;
import com.zry.framework.entity.WaybillDetail;
import com.zry.framework.mapper.WaybillMapper;
import com.zry.framework.repository.BillLocationRepository;
import com.zry.framework.repository.CarCargoOwnnerRepository;
import com.zry.framework.repository.CargoRepository;
import com.zry.framework.repository.EvaluateRepository;
import com.zry.framework.repository.WaybillDetailRepository;
import com.zry.framework.repository.WaybillRepository;
import com.zry.framework.service.WaybillService;
import com.zrytech.framework.base.entity.PageData;
import com.zrytech.framework.base.entity.ServerResponse;

/**
 * 运单
 *
 * @author cat
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
    private WaybillDao waybillDao;

    @Autowired
    private CargoDao cargoDao;

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

   /* @Autowired
    private TradeNoUtil tradeNoUtil;*/

    @Override
    public ServerResponse createIndent(WaybillDto waybillDto) {
        Waybill waybill = BeanUtil.copy(waybillDto, Waybill.class);
        //TODO:waybill.setBillNo(tradeNoUtil.genTradeNo());
        waybill.setStatus(CargoConstant.AWAIT_GENERATE);
        waybill.setCreateDate(new Date());
        int num = waybillDao.createIndent(waybill);
        CheckFieldUtils.assertSuccess(num);
        return ServerResponse.success();
    }

    @Override
    public ServerResponse confirmIndent(WaybillDto waybillDto) {
        CheckFieldUtils.checkObjecField(waybillDto.getStatus());
        Waybill waybill = BeanUtil.copy(waybillDto, Waybill.class);
        int num = waybillDao.updateIndentStatus(waybill);
        CheckFieldUtils.assertSuccess(num);
        return ServerResponse.success();
    }

    @Override
    public ServerResponse indentPage(WaybillDto waybillDto, Page page) {
        CheckFieldUtils.checkObjecField(waybillDto.getCargoOwnnerId());
        Waybill waybill = BeanUtil.copy(waybillDto, Waybill.class);
        PageInfo<Waybill> pageInfo = waybillDao.indentPage(waybill, page);
        return ServerResponse.successWithData(pageInfo);
    }

    @Override
    public ServerResponse coundIndent(WaybillDto waybillDto) {
        CheckFieldUtils.checkObjecField(waybillDto.getCargoOwnnerId());
        List<String> typeCount = waybillDao.coundIndent(waybillDto.getCargoOwnnerId());
        Map<String, Object> map = countList(typeCount);
        return ServerResponse.successWithData(map);
    }

    @Override
    public ServerResponse get(WaybillDto waybillDto) {
        CheckFieldUtils.checkObjecField(waybillDto.getId());
        Waybill waybill = waybillDao.get(waybillDto.getId());
        if(waybill !=null&& waybill.getCargoId() !=null){
            Cargo cargo=cargoDao.get(waybill.getCargoId());
            waybill.setCargo(cargo);
        }
        return ServerResponse.successWithData(waybill);
    }

    @Override
    public ServerResponse changeIndent(WaybillDto waybillDto) {
        CheckFieldUtils.checkObjecField(waybillDto.getTotalMoney());
        Waybill waybill = BeanUtil.copy(waybillDto, Waybill.class);
        int num = waybillDao.changeIndent(waybill);
        CheckFieldUtils.assertSuccess(num);
        return ServerResponse.success();
    }

    @Override
    public ServerResponse delete(WaybillDto waybillDto) {
        Waybill waybill = waybillDao.get(waybillDto.getId());
        if(CargoConstant.AWAIT_GENERATE.equalsIgnoreCase(waybill.getStatus())){
            int num = waybillDao.delete(waybillDto.getId());
            CheckFieldUtils.assertSuccess(num);
            return ServerResponse.success();
        }else{
            throw new BusinessException(new LogisticsResult(LogisticsResultEnum.DELETE_WAYBILL_FAIL));
        }
    }

    public Map<String, Object> countList(List<String> typeCount) {
        Map<String, Object> map = new HashMap<>();
        if (typeCount != null || typeCount.size() > 0) {
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
                } else if (arr[0].equalsIgnoreCase(CargoConstant.SIGN_PAIED)) {
                    map.put(CargoConstant.SIGN_PAIED, arr[1] == null ? 0 : Integer.parseInt(arr[1]));
                } else if (arr[0].equalsIgnoreCase(CargoConstant.IS_EVALUATION)) {
                    map.put(CargoConstant.IS_EVALUATION, arr[1] == null ? 0 : Integer.parseInt(arr[1]));
                } else {
                    map.put(CargoConstant.COMPLETED, arr[1] == null ? 0 : Integer.parseInt(arr[1]));
                }
            }
        } else {
            map.put(CargoConstant.AWAIT_GENERATE, 0);
            map.put(CargoConstant.AWAIT_DETERMINE, 0);
            map.put(CargoConstant.AWAIT_LOADING, 0);
            map.put(CargoConstant.AWAIT_ACCEPT, 0);
            map.put(CargoConstant.SIGN_PAIED, 0);
            map.put(CargoConstant.IS_EVALUATION, 0);
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


}
