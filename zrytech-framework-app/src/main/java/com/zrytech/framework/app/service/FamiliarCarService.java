package com.zrytech.framework.app.service;

import com.zrytech.framework.app.dto.familiarcar.FamiliarCarDto;
import com.zrytech.framework.app.dto.ofenlocation.OfenLocationAddDto;
import com.zrytech.framework.app.dto.ofenlocation.OfenLocationCommonDto;
import com.zrytech.framework.app.dto.ofenlocation.OfenLocationDto;
import com.zrytech.framework.app.dto.ofenlocation.OfenLocationUpdateDto;
import com.zrytech.framework.app.entity.OfenLocation;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.base.entity.ServerResponse;

import java.util.List;

public interface FamiliarCarService {

    /**
     * Desintion:familiarCar分页列表信息
     *
     * @author:dante
     * @param:familiarCar  dto
     * @return:ServerResponse
     */
    ServerResponse familiarCarPage(FamiliarCarDto familiarCarDto, Page page);

    /**
     * Desintion:familiarCar添加
     *
     * @param:familiarCar dto
     * @return:ServerResponse
     */
    ServerResponse add(FamiliarCarDto familiarCarDto);


    /**
     * Desintion:货主常用地址删除
     *
     * @author:dante
     * @param:货主常用地址dto
     * @return:ServerResponse
     */
    ServerResponse delete(FamiliarCarDto familiarCarDto);


}
