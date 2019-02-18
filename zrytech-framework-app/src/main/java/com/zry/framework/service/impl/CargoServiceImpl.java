package com.zry.framework.service.impl;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.util.BeanUtil;
import com.zrytech.framework.price.constants.CargoConstant;
import com.zrytech.framework.price.dao.CargoDao;
import com.zrytech.framework.price.dao.LoadingDao;
import com.zrytech.framework.price.dto.CargoDto;
import com.zrytech.framework.price.entity.Cargo;
import com.zrytech.framework.price.entity.Loading;
import com.zrytech.framework.price.service.CargoService;
import com.zrytech.framework.price.util.CheckFieldUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CargoServiceImpl implements CargoService {

    @Autowired
    private CargoDao cargoDao;

    @Autowired
    private LoadingDao loadingDao;


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

    @Override
    public ServerResponse auditSource(CargoDto cargoDto) {
        //TODO:拒绝后需填写拒绝理由;添加审核记录
        Cargo cargo = BeanUtil.copy(cargoDto, Cargo.class);
        //TODO:审核通过,系统通过发标方式推送给相应的车主(考虑是否是免审核用户)

        int num = cargoDao.updateAudit(cargo);
        CheckFieldUtils.assertSuccess(num);
        return ServerResponse.success();
    }

    @Override
    public ServerResponse pushResource(CargoDto cargoDto) {
        //TODO:判断当前用户是否为免审核用户;如果是免审核则系统直接通过招标方式通知相应的车主
        Cargo cargo= BeanUtil.copy(cargoDto,Cargo.class);
        cargo.setCreateDate(new Date());
        int cargoId = cargoDao.pushSave(cargo);
        CheckFieldUtils.assertSuccess(cargoId);
        List<Loading> loadingList = cargoDto.getMulShipmentList();
        if (loadingList != null && loadingList.size() > 0) {
            //批量添加装货地址
            loadingDao.batchSave(loadingList, CargoConstant.LOADING_TYPE,cargoId);
        }
        List<Loading> unLoadingList = cargoDto.getMulUnloadList();
        if (loadingList != null && loadingList.size() > 0) {
            //批量添加卸货地址
            loadingDao.batchSave(unLoadingList, CargoConstant.UNLOADING_TYPE,cargoId);
        }
        return ServerResponse.success();
    }
}
