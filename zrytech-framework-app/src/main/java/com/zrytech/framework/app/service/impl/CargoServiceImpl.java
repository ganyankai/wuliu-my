package com.zrytech.framework.app.service.impl;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.app.constants.ApproveLogConstants;
import com.zrytech.framework.app.constants.CargoConstant;
import com.zrytech.framework.app.dao.CargoCustomerDao;
import com.zrytech.framework.app.dao.CargoDao;
import com.zrytech.framework.app.dao.LoadingDao;
import com.zrytech.framework.app.dao.ShipperDao;
import com.zrytech.framework.app.dto.CargoDto;
import com.zrytech.framework.app.entity.*;
import com.zrytech.framework.app.enums.LogisticsResult;
import com.zrytech.framework.app.enums.LogisticsResultEnum;
import com.zrytech.framework.app.repository.ApproveLogRepository;
import com.zrytech.framework.app.repository.CargoRepository;
import com.zrytech.framework.app.service.CargoService;
import com.zrytech.framework.app.utils.CheckFieldUtils;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.entity.User;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.base.util.BeanUtil;
import com.zrytech.framework.base.util.RequestUtil;
import com.zrytech.framework.common.entity.SysCustomer;
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

    @Autowired
    private ShipperDao shipperDao;

    @Autowired
    private CargoRepository cargoRepository;


    /**
     * Desintion:货源分页列表信息
     *
     * @author:jiangxiaoxiang
     * @param:CargoDto货源dto
     * @return:ServerResponse
     */
    @Override
    public ServerResponse cargoPage(CargoDto cargoDto, Page page) {
        Cargo cargoCustomer = BeanUtil.copy(cargoDto, Cargo.class);
        String orderField = "pickup_date";
        PageInfo<Cargo> pageInfo = cargoDao.cargoPage(cargoCustomer, orderField, page);
        return ServerResponse.successWithData(pageInfo);
    }

    /**
     * Desintion:货源详情
     *
     * @author:jiangxiaoxiang
     * @param:CargoDto货源dto
     * @return:ServerResponse
     */
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

    @Autowired
    private ApproveLogRepository approveLogRepository;

    /**
     * Desintion:货源审核
     *
     * @author:jiangxiaoxiang
     * @param:CargoDto货源dto
     * @return:ServerResponse
     */
    @Override
    public ServerResponse auditSource(CargoDto cargoDto, User user) {
        CheckFieldUtils.checkObjecField(cargoDto.getStatus());
        CheckFieldUtils.checkObjecField(cargoDto.getDescribe());
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
        if (CargoConstant.SOURCE_REFUSE.equalsIgnoreCase(cargoDto.getStatus())) {//审核被拒绝:下架
            //TODO:短信通知
            cargo.setStatus(CargoConstant.SOURCE_DRAFT);//变为草稿状态
            int num = cargoDao.updateAudit(cargo);
            CheckFieldUtils.assertSuccess(num);
            return ServerResponse.success();
        }
        pushGoodSource(cargoGoods);
        int num = cargoDao.updateAudit(cargo);
        CheckFieldUtils.assertSuccess(num);
        return ServerResponse.success();
    }

    public void pushGoodSource(Cargo cargoGoods) {
        if (cargoGoods != null && CargoConstant.TENDER_MARK.equalsIgnoreCase(cargoGoods.getTenderWay())) {
            //TODO:招标方式;
            List<Integer> list = cargoCustomerDao.selectCarList(cargoGoods, CargoConstant.CUSTOMER_CAR_OWNER);
            cargoDao.batch(list, cargoGoods.getId(), new Date());//批量添加推送记录
        } else {//抢标方式
            List<Integer> list = cargoCustomerDao.selectCarList(cargoGoods, CargoConstant.CUSTOMER_CAR_OWNER);
            cargoDao.batch(list, cargoGoods.getId(), new Date());//批量添加推送记录
        }
    }

    /**
     * Desintion:发布货源(前端)
     *
     * @author:jiangxiaoxiang
     * @param:CargoDto货源dto
     * @return:ServerResponse
     */
    @Override
    public ServerResponse pushResource(CargoDto cargoDto) {
        //验证
        CheckFieldUtils.checkObjecField(cargoDto.getName());
        CheckFieldUtils.checkObjecField(cargoDto.getQty());
        CheckFieldUtils.checkObjecField(cargoDto.getMatterPrice());
        CheckFieldUtils.checkObjecField(cargoDto.getStartCity());
        CheckFieldUtils.checkObjecField(cargoDto.getEndCity());
        //TODO:判断当前用户是否为免审核用户;如果是免审核则系统直接通过招标方式通知相应的车主
        Certification certification = shipperDao.getCustomerId(cargoDto.getCreateBy());
        if (certification == null || certification.getStatus() == null || !certification.getStatus().equalsIgnoreCase(CargoConstant.AUDIT_PASS)) {
            throw new BusinessException(new LogisticsResult(LogisticsResultEnum.PERMISSED_NOT_FAIL));
        }
        Cargo cargo = BeanUtil.copy(cargoDto, Cargo.class);
        cargo.setCreateDate(new Date());
        cargo.setStatus(CargoConstant.SOURCE_DRAFT);
        int num = cargoDao.pushSave(cargo);
        CheckFieldUtils.assertSuccess(num);
        List<Loading> loadingList = cargoDto.getMulShipmentList();
        if (loadingList != null && loadingList.size() > 0) {
            //批量添加装货地址
            loadingDao.batchSave(loadingList, CargoConstant.LOADING_TYPE, cargo.getId());
        }
        List<Loading> unLoadingList = cargoDto.getMulUnloadList();
        if (loadingList != null && loadingList.size() > 0) {
            //批量添加卸货地址
            loadingDao.batchSave(unLoadingList, CargoConstant.UNLOADING_TYPE, cargo.getId());
        }
        if (certification != null && certification.getAvoidAudit() != null && certification.getAvoidAudit()) {//ture表示是免审核用户;则货源无需经过后台审核直接推送
            cargo.setId(cargo.getId());
            pushGoodSource(cargo);
        }
        return ServerResponse.success();
    }

    /**
     * Desintion:修改货源(前端)
     *
     * @author:jiangxiaoxiang
     * @param:CargoDto货源dto
     * @return:ServerResponse
     */
    @Override
    public ServerResponse updateSource(CargoDto cargoDto) {
        List<Loading> loadingList = cargoDto.getMulShipmentList();//多点装货地址
        List<Loading> unloadingList = cargoDto.getMulUnloadList();//多点卸货地址
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
        //批量操作
        List<Loading> updateList = new ArrayList<>();
        List<Integer> deleteIds = new ArrayList<>();
        List<Loading> batchAdds = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();
        getLoadList(loadingList, batchAdds, ids, updateList, CargoConstant.LOADING_TYPE);
        getLoadList(unloadingList, batchAdds, ids, updateList, CargoConstant.UNLOADING_TYPE);
        getDeleteList(loadings, ids, deleteIds);
        getDeleteList(unloadings, ids, deleteIds);
        if (deleteIds != null && deleteIds.size() > 0) { //批量删除
            loadingDao.batchDelete(deleteIds);
        }
        if (batchAdds != null && batchAdds.size() > 0) { //批量添加
            loadingDao.batchAdds(batchAdds, cargoId);
        }
        if (updateList != null && updateList.size() > 0) {//批量修改
            loadingDao.batchUpdate(updateList);
        }
    }

    public void getLoadList(List<Loading> list, List<Loading> batchAdds, List<Integer> ids, List<Loading> updateList, String type) {
        if (list != null && list.size() > 0) {
            for (Loading loading : list) {
                if (loading.getId() == null) {
                    loading.setType(type);
                    batchAdds.add(loading);
                } else {
                    updateList.add(loading);
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

    /**
     * Desintion:删除货源(前端)
     *
     * @author:jiangxiaoxiang
     * @param:CargoDto货源dto
     * @return:ServerResponse
     */
    @Override
    public ServerResponse deleteSource(CargoDto cargoDto) {
        Cargo cargo = cargoDao.get(cargoDto.getId());
        if (CargoConstant.AUDIT_PASS.equalsIgnoreCase(cargo.getStatus())) {
            throw new BusinessException(new LogisticsResult(LogisticsResultEnum.GOODS_SOURCE_UP));
        }
        SysCustomer sysCustomer = RequestUtil.getCurrentUser(SysCustomer.class);
        if (cargo.getCreateBy() != sysCustomer.getId()) {
            throw new BusinessException(new LogisticsResult(LogisticsResultEnum.USER_DELETE_FAIL));
        }
        int num = cargoDao.deleteSource(cargoDto.getId());
        CheckFieldUtils.assertSuccess(num);
        return ServerResponse.success();
    }

    /**
     * Desintion:邀请报价(前端)
     *
     * @author:jiangxiaoxiang
     * @param:CargoDto货源dto
     * @return:ServerResponse
     */
    @Override
    public ServerResponse invitationOffer(CargoDto cargoDto) {
        CheckFieldUtils.checkObjecField(cargoDto.getCargoIds());
        CheckFieldUtils.checkObjecField(cargoDto.getCarIds());
        SysCustomer sysCustomer = RequestUtil.getCurrentUser(SysCustomer.class);
        List<Offer> offerList = getOfferList(cargoDto.getCargoIds(), cargoDto.getCarIds(), sysCustomer.getId());
        if(offerList !=null&& offerList.size()>0) {
            int num = cargoDao.invitationOffer(offerList, CargoConstant.OFFER_PROCESS);
            CheckFieldUtils.assertSuccess(num);
        }
        return ServerResponse.success();
    }

    public List<Offer> getOfferList(List<Integer> cargoIds, List<Integer> carIds, Integer createBy) {
        List<Offer> offerList = new ArrayList<>();
        for (Integer cargoId : cargoIds) {
            for (Integer carId : carIds) {
                Offer offer = new Offer();
                offer.setCreateBy(createBy);
                offer.setCargoId(cargoId);
                offer.setCarOwnnerId(carId);
                if(!offerList.contains(offer)){
                    offerList.add(offer);
                }
            }
        }
        return offerList;
    }

    /**
     * Desintion:我的货源分页列表信息
     *
     * @author:jiangxiaoxiang
     * @param:CargoDto货源dto
     * @return:ServerResponse
     */
    @Override
    public ServerResponse mySourcePage(CargoDto cargoDto, Page page) {
        Cargo cargoCustomer = BeanUtil.copy(cargoDto, Cargo.class);
        String orderField = "create_date";
        PageInfo<Cargo> pageInfo = cargoDao.cargoPage(cargoCustomer, orderField, page);
        return ServerResponse.successWithData(pageInfo);
    }

    /**
     * Desintion:取消发布货源(前端)
     *
     * @author:jiangxiaoxiang
     * @param:CargoDto货源dto
     * @return:ServerResponse
     */
    @Override
    public ServerResponse cancelResource(CargoDto cargoDto) {
        Cargo cargo = cargoDao.get(cargoDto.getId());
        if (CargoConstant.SOURCE_UP.equalsIgnoreCase(cargo.getStatus())) {//针对报价中的货源,取消发布
            cargo.setStatus(CargoConstant.SOURCE_CANCEL);//状态改为:已取消状态
            cargoDao.updateAudit(cargo);
            //TODO:是否删除推送记录和报价信息
        }
        return ServerResponse.success();
    }


    /**
     * 断言货源可用
     *
     * @param cargoId 货源Id
     * @return
     * @author cat
     */
    @Override
    public Cargo assertCargoAvailable(Integer cargoId) {
        Cargo cargo = cargoRepository.findOne(cargoId);
        if (cargo == null) {
            throw new BusinessException(112, "货源不存在");
        }
        return cargo;
    }
}