package com.zrytech.framework.app.service.impl;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.app.dao.OfenLocationDao;
import com.zrytech.framework.app.dto.ofenlocation.OfenLocationAddDto;
import com.zrytech.framework.app.dto.ofenlocation.OfenLocationCommonDto;
import com.zrytech.framework.app.dto.ofenlocation.OfenLocationDto;
import com.zrytech.framework.app.dto.ofenlocation.OfenLocationUpdateDto;
import com.zrytech.framework.app.entity.*;
import com.zrytech.framework.app.repository.OfenLocationRepository;
import com.zrytech.framework.app.service.OfenLocationService;
import com.zrytech.framework.app.utils.CheckFieldUtils;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.util.BeanUtil;
import com.zrytech.framework.base.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class OfenLocationServiceImpl implements OfenLocationService {


   @Autowired
    private OfenLocationDao ofenLocationDao;

    @Autowired
    private OfenLocationRepository ofenLocationRepository;

    /**
     * Desintion:常用地址分页列表信息
     *
     * @author:dante
     * @param:OfenLocationDto常用地址dto
     * @return:ServerResponse
     */
//    @Override
    public ServerResponse addressPage(OfenLocationDto ofenLocationDto, Page page) {
        OfenLocation ofenLocation= BeanUtil.copy(ofenLocationDto,OfenLocation.class);
        Customer customer = RequestUtil.getCurrentUser(Customer.class);
        //货主id
        Integer id = customer.getCargoOwner().getId();
        ofenLocation.setCargoOwnerId(id);
        PageInfo<OfenLocation> pageInfo=ofenLocationDao.addressPage(ofenLocation,page);
        return ServerResponse.successWithData(pageInfo);
    }

    /**
     * Desintion:常用地址添加
     *
     * @author:
     * @param:OfenLocation 常用地址dto
     * @return:ServerResponse
     */

//    @Override
    public ServerResponse add(OfenLocationAddDto ofenLocationAddDto) {

        OfenLocation ofenLocation = BeanUtil.copy(ofenLocationAddDto, OfenLocation.class);
        ofenLocation.setCreateDate(new Date());
        Customer customer = RequestUtil.getCurrentUser(Customer.class);
        //货主id
        Integer id = customer.getCargoOwner().getId();
        ofenLocation.setCargoOwnerId(id);
//        ofenLocation.setCargoOwnerId(123456);
        ofenLocationRepository.saveAndFlush(ofenLocation);
        return ServerResponse.successWithData("添加成功");
    }

    /**
     * Desintion:常用地址详情
     *
     * @author:
     * @param:OfenLocationDto常用地址dto
     * @return:ServerResponse
     */
//    @Override
    public ServerResponse get(OfenLocationCommonDto ofenLocationCommonDto) {
        CheckFieldUtils.checkObjecField(ofenLocationCommonDto.getId());
        OfenLocation oftenAddress=ofenLocationDao.get(ofenLocationCommonDto.getId());
        return ServerResponse.successWithData(oftenAddress);
    }

    /**
     * Desintion:常用地址修改
     *
     * @author:
     * @param:OfenLocationDto常用地址dto
     * @return:ServerResponse
     */
    public ServerResponse update(OfenLocationUpdateDto ofenLocationUpdateDto) {
        OfenLocation ofenLocation=BeanUtil.copy(ofenLocationUpdateDto,OfenLocation.class);
        int num=ofenLocationDao.update(ofenLocation);
        CheckFieldUtils.assertSuccess(num);
        return ServerResponse.success();
    }

    /**
     * Desintion:常用地址删除
     *
     * @author:
     * @param:OfenLocationDto常用地址dto
     * @return:ServerResponse
     */
//    @Override
    public ServerResponse delete(OfenLocationCommonDto ofenLocationCommonDto) {
        CheckFieldUtils.checkObjecField(ofenLocationCommonDto.getId());
        int num=ofenLocationDao.delete(ofenLocationCommonDto.getId());
        CheckFieldUtils.assertSuccess(num);
        return ServerResponse.success();
    }

    /**
     * 校验当前用户身份
     * @param id
     * @return
     */
    @Override
    public Boolean checkCustomer(Integer id) {
        Customer customer = RequestUtil.getCurrentUser(Customer.class);
        Integer cargoOwnerId = customer.getCargoOwner().getId();

        OfenLocation ofenLocation = ofenLocationDao.get(id);
        //数据库中的货主id
        if(ofenLocation!=null) {
            Integer dataId = ofenLocation.getCargoOwnerId();
            return cargoOwnerId.equals(dataId);
        }
       return false;
    }

    /**
     * 查询货主id在表中是否有记录
     * @param cargoOwnerId
     * @return
     */
    @Override
    public List<OfenLocation> findByCargoOwnerId(Integer cargoOwnerId) {
        return ofenLocationRepository.findByCargoOwnerId(cargoOwnerId);
    }


}
