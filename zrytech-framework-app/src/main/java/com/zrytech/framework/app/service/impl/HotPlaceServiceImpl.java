package com.zrytech.framework.app.service.impl;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.app.dao.HotPlaceDao;
import com.zrytech.framework.app.dto.hotplace.HotPlaceAddDto;
import com.zrytech.framework.app.dto.hotplace.HotPlaceCommonDto;
import com.zrytech.framework.app.dto.hotplace.HotPlacePageDto;
import com.zrytech.framework.app.dto.hotplace.HotPlaceUpdateDto;

import com.zrytech.framework.app.entity.HotPlace;
import com.zrytech.framework.app.repository.HotPlaceRepository;
import com.zrytech.framework.app.service.HotPlaceService;
import com.zrytech.framework.app.utils.CheckFieldUtils;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.ServerResponse;
import com.zrytech.framework.base.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class HotPlaceServiceImpl implements HotPlaceService {

   @Autowired
    private HotPlaceDao hotPlaceDao;

    @Autowired
    private HotPlaceRepository hotPlaceRepository;

    /**
     * Desintion:热门货源地分页列表信息
     *
     * @author:dante
     * @param:热门货源地dto
     * @return:ServerResponse
     */
//    @Override
    public ServerResponse hotPlacePage(HotPlacePageDto hotPlaceDto, Page page) {
        HotPlace hotPlace= BeanUtil.copy(hotPlaceDto,HotPlace.class);
        PageInfo<HotPlace> pageInfo=hotPlaceDao.hotPlacePage(hotPlace,page);
        return ServerResponse.successWithData(pageInfo);
    }

    /**
     * Desintion:热门货源地添加
     *
     * @author:
     * @param:HotPlace 热门货源地dto
     * @return:ServerResponse
     */

    @Transactional
    public ServerResponse add(HotPlaceAddDto hotPlaceAddDto) {

        HotPlace hotPlace = BeanUtil.copy(hotPlaceAddDto, HotPlace.class);
        hotPlace.setCreateDate(new Date());

        hotPlaceRepository.saveAndFlush(hotPlace);
        return ServerResponse.successWithData("添加成功");
    }

    /**
     * Desintion:热门货源地详情
     *
     * @author:
     * @param:HotPlaceDto热门货源地dto
     * @return:ServerResponse
     */
//    @Override
    public ServerResponse get(HotPlaceCommonDto hotPlaceCommonDto) {
        CheckFieldUtils.checkObjecField(hotPlaceCommonDto.getId());
        HotPlace hotPlace=hotPlaceDao.get(hotPlaceCommonDto.getId());
        return ServerResponse.successWithData(hotPlace);
    }

    /**
     * Desintion:热门货源地修改
     *
     * @author:
     * @param:HotPlaceDto热门货源地dto
     * @return:ServerResponse
     */
    @Transactional
    public ServerResponse update(HotPlaceUpdateDto hotPlaceUpdateDto) {
        HotPlace hotPlace=BeanUtil.copy(hotPlaceUpdateDto,HotPlace.class);
        int num=hotPlaceDao.update(hotPlace);
        CheckFieldUtils.assertSuccess(num);
        return ServerResponse.success();
    }

    /**
     * Desintion:热门货源地删除
     *
     * @author:
     * @param:HotPlaceDto热门货源地dto
     * @return:ServerResponse
     */
//    @Override
    @Transactional
    public ServerResponse delete(HotPlaceCommonDto hotPlaceCommonDto) {
        CheckFieldUtils.checkObjecField(hotPlaceCommonDto.getId());
        int num=hotPlaceDao.delete(hotPlaceCommonDto.getId());
        CheckFieldUtils.assertSuccess(num);
        return ServerResponse.success();
    }


}
