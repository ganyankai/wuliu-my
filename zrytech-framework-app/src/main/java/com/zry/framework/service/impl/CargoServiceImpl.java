package com.zry.framework.service.impl;

import com.github.pagehelper.PageInfo;
import com.zry.framework.constants.ApproveLogConstants;
import com.zry.framework.constants.CargoConstant;
import com.zry.framework.dao.CargoCustomerDao;
import com.zry.framework.dao.CargoDao;
import com.zry.framework.dao.LoadingDao;
import com.zry.framework.dto.CargoDto;
import com.zry.framework.entity.*;
import com.zry.framework.enums.LogisticsResult;
import com.zry.framework.enums.LogisticsResultEnum;
import com.zry.framework.repository.ApproveLogRepository;
import com.zry.framework.service.CargoService;
import com.zry.framework.utils.CheckFieldUtils;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.base.util.BeanUtil;
import com.zrytech.framework.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CargoServiceImpl implements CargoService {

    @Autowired
    private CargoDao cargoDao;

    @Autowired
    private LoadingDao loadingDao;

    @Autowired
    private CargoCustomerDao cargoCustomerDao;


    @Override
    public ServerResponse cargoPage(CargoDto cargoDto, Page page) {
        Cargo cargoCustomer = BeanUtil.copy(cargoDto, Cargo.class);
        String orderField = "pickup_date";
        PageInfo<Cargo> pageInfo = cargoDao.cargoPage(cargoCustomer, orderField, page);
        return ServerResponse.successWithData(pageInfo);
    }

    @Override
    public ServerResponse get(CargoDto cargoDto) {
        CheckFieldUtils.checkObjecField(cargoDto.getId());
        Cargo cargo = cargoDao.get(cargoDto.getId());
        //获取多点装货地址
        List<Loading> lodingList = loadingDao.selectLoadingList(cargo.getId(), CargoConstant.LOADING_TYPE);
        if (lodingList != null && lodingList.size() > 0) {
            cargo.setMulShipmentList(lodingList);
        }
        //获取多点卸货地址
        List<Loading> unLodingList = loadingDao.selectLoadingList(cargo.getId(), CargoConstant.UNLOADING_TYPE);
        if (unLodingList != null && unLodingList.size() > 0) {
            cargo.setMulUnloadList(unLodingList);
        }
        return ServerResponse.successWithData(cargo);
    }

    @Autowired private ApproveLogRepository approveLogRepository;

    @Override
    public ServerResponse auditSource(CargoDto cargoDto,User user) {
        CheckFieldUtils.checkObjecField(cargoDto.getStatus());
        //TODO:拒绝后需填写拒绝理由;添加审核记录
        Cargo cargo = BeanUtil.copy(cargoDto, Cargo.class);
        //TODO:审核通过,系统通过发标方式推送给相应的车主(考虑是否是免审核用户)
        Cargo cargoGoods = cargoDao.get(cargoDto.getId());
        //添加审核记录
        ApproveLog entity = new ApproveLog();
        entity.setApproveBy(user.getId());
        entity.setApproveContent(cargoDto.getDescribe());
        entity.setApproveResult(cargoDto.getStatus());
        entity.setApproveTime(new Date());
        entity.setApproveType(ApproveLogConstants.APPROVE_TYPE_GOODS_SOURCE);
        entity.setBusinessId(cargoDto.getId());
        approveLogRepository.save(entity);

        if (CargoConstant.SOURCE_DOWN.equalsIgnoreCase(cargoDto.getStatus())) {//审核被拒绝:未上架
            //TODO:短信通知
            int num = cargoDao.updateAudit(cargo);
            CheckFieldUtils.assertSuccess(num);
            return ServerResponse.success();
        }
        if (cargoGoods != null && CargoConstant.TENDER_MARK.equalsIgnoreCase(cargoGoods.getTenderWay())) {
            //TODO:招标方式

        } else {//抢标方式
            List<Integer> list = cargoCustomerDao.selectCarList(cargoGoods, CargoConstant.CUSTOMER_CAR_OWNER);
            cargoDao.batch(list, cargoGoods.getId(), new Date());//批量添加推送记录
        }
        int num = cargoDao.updateAudit(cargo);
        CheckFieldUtils.assertSuccess(num);
        return ServerResponse.success();
    }

    @Override
    public ServerResponse pushResource(CargoDto cargoDto) {
        //TODO:判断当前用户是否为免审核用户;如果是免审核则系统直接通过招标方式通知相应的车主
        Cargo cargo = BeanUtil.copy(cargoDto, Cargo.class);
        cargo.setCreateDate(new Date());
        int cargoId = cargoDao.pushSave(cargo);
        CheckFieldUtils.assertSuccess(cargoId);
        List<Loading> loadingList = cargoDto.getMulShipmentList();
        if (loadingList != null && loadingList.size() > 0) {
            //批量添加装货地址
            loadingDao.batchSave(loadingList, CargoConstant.LOADING_TYPE, cargoId);
        }
        List<Loading> unLoadingList = cargoDto.getMulUnloadList();
        if (loadingList != null && loadingList.size() > 0) {
            //批量添加卸货地址
            loadingDao.batchSave(unLoadingList, CargoConstant.UNLOADING_TYPE, cargoId);
        }
        return ServerResponse.success();
    }

    @Override
    public ServerResponse updateSource(CargoDto cargoDto) {
        List<Loading> loadingList = cargoDto.getMulShipmentList();//TODO:多点装货地址
        List<Loading> unloadingList = cargoDto.getMulUnloadList();//TODO:多点卸货地址
        updateOrSave(loadingList, unloadingList, cargoDto.getId());
        Cargo cargo = BeanUtil.copy(cargoDto, Cargo.class);
        cargoDao.updateSource(cargo);
        return ServerResponse.success();
    }

    /**
     * @Desinition:多点卸货和多点装货修改
     * @param:loadingList
     * @param:unloadingList
     * @param:cargoId
     */
    public void updateOrSave(List<Loading> loadingList, List<Loading> unloadingList, Integer cargoId) {
        List<Loading> loadings = loadingDao.selectLoadingList(cargoId, CargoConstant.LOADING_TYPE);
        List<Loading> unloadings = loadingDao.selectLoadingList(cargoId, CargoConstant.UNLOADING_TYPE);
        //List<Integer> loadingIds = loadingDao.getListByIds(loadings);//装货id集合
        //List<Integer> unloadingIds = loadingDao.getListByIds(unloadings);//卸货id集合
        //批量操作
        List<Integer> deleteIds = new ArrayList<>();
        List<Loading> batchAdds = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();
        getLoadList(loadingList, batchAdds, ids, CargoConstant.LOADING_TYPE);
        getLoadList(unloadingList, batchAdds, ids, CargoConstant.UNLOADING_TYPE);
        getDeleteList(loadings, ids, deleteIds);
        getDeleteList(unloadings, ids, deleteIds);
        if(deleteIds !=null&& deleteIds.size()>0){ //批量删除
            loadingDao.batchDelete(deleteIds);
        }
        if(batchAdds !=null&& batchAdds.size()>0){ //批量添加
            loadingDao.batchAdds(batchAdds,cargoId);
        }
    }

    public void getLoadList(List<Loading> list, List<Loading> batchAdds, List<Integer> ids, String type) {
        if (list != null && list.size() > 0) {
            for (Loading loading : list) {
                if (loading.getId() == null) {
                    loading.setType(type);
                    batchAdds.add(loading);
                } else {
                    ids.add(loading.getId());
                }
            }
        }
    }

    public void getDeleteList(List<Loading> list, List<Integer> ids, List<Integer> deleteIds) {
        if (list != null && list.size() > 0) {
            for (Loading load : list) {
                if (!ids.contains(load.getId())) {
                    deleteIds.add(load.getId());
                } else {
                    continue;
                }
            }
        }
    }


    @Override
    public ServerResponse deleteSource(CargoDto cargoDto) {
        Cargo cargo = cargoDao.get(cargoDto.getId());
        if (CargoConstant.AUDIT_PASS.equalsIgnoreCase(cargo.getStatus())) {
            throw new BusinessException(new LogisticsResult(LogisticsResultEnum.GOODS_SOURCE_UP));
        }
        int num = cargoDao.deleteSource(cargoDto.getId());
        CheckFieldUtils.assertSuccess(num);
        return ServerResponse.success();
    }

    @Override
    public ServerResponse invitationOffer(CargoDto cargoDto) {
        CheckFieldUtils.checkObjecField(cargoDto.getId());
        CheckFieldUtils.checkObjecField(cargoDto.getCarOwnnerId());
        int num=cargoDao.invitationOffer(cargoDto.getId(),cargoDto.getCarOwnnerId(),new Date());
        CheckFieldUtils.assertSuccess(num);
        return ServerResponse.success();
    }

    @Override
    public ServerResponse mySourcePage(CargoDto cargoDto, Page page) {
        Cargo cargoCustomer = BeanUtil.copy(cargoDto, Cargo.class);
        String orderField = "create_date";
        PageInfo<Cargo> pageInfo = cargoDao.cargoPage(cargoCustomer, orderField, page);
        return ServerResponse.successWithData(pageInfo);
    }
}
