package com.zrytech.framework.app.service;

import com.zrytech.framework.app.dto.hotplace.HotPlaceAddDto;
import com.zrytech.framework.app.dto.hotplace.HotPlaceCommonDto;
import com.zrytech.framework.app.dto.hotplace.HotPlacePageDto;
import com.zrytech.framework.app.dto.hotplace.HotPlaceUpdateDto;
import com.zrytech.framework.app.entity.HotPlace;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.ServerResponse;

import java.util.List;

public interface HotPlaceService {

    /**
     * Desintion:热门货源地分页列表信息
     *
     * @author:dante
     * @param:热门货源地dto
     * @return:ServerResponse
     */
    ServerResponse hotPlacePage(HotPlacePageDto ofenLocationDto, Page page);

    /**
     * Desintion:热门货源地添加
     *
     * @param:热门货源地dto
     * @return:ServerResponse
     */
    ServerResponse add(HotPlaceAddDto ofenLocationAddDto);

    /**
     * Desintion:热门货源地详情
     *
     * @author:dante
     * @param:热门货源地dto
     * @return:ServerResponse
     */
    ServerResponse get(HotPlaceCommonDto ofenLocationCommonDto);

    /**
     * Desintion:热门货源地修改
     *
     * @author:dante
     * @param:热门货源地dto
     * @return:ServerResponse
     */
    ServerResponse update(HotPlaceUpdateDto ofenLocationUpdateDto);

    /**
     * Desintion:热门货源地删除
     *
     * @author:dante
     * @param:热门货源地dto
     * @return:ServerResponse
     */
    ServerResponse delete(HotPlaceCommonDto ofenLocationCommonDto);

    
}
