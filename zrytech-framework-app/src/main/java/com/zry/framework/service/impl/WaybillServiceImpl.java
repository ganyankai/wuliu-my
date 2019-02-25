package com.zry.framework.service.impl;

import java.util.Date;
import java.util.List;

import com.zry.framework.constants.CargoConstant;
import com.zry.framework.dao.WaybillDao;
import com.zry.framework.dto.WaybillDto;
import com.zry.framework.utils.CheckFieldUtils;
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

    @Override
    public ServerResponse createIndent(WaybillDto waybillDto) {
        Waybill waybill = BeanUtil.copy(waybillDto, Waybill.class);
        waybill.setBillNo(tradeNoUtil.genTradeNo());
        waybill.setStatus(CargoConstant.AWAIT_GENERATE);
        waybill.setCreateDate(new Date());
        int num = waybillDao.createIndent(waybill);
        CheckFieldUtils.assertSuccess(num);
        return ServerResponse.success();
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
     * @param cargoMatter
     * @return
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
