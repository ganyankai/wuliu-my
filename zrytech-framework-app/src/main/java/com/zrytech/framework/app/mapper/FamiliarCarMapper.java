package com.zrytech.framework.app.mapper;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.app.entity.FamiliarCar;
import com.zrytech.framework.app.entity.OfenLocation;
import com.zrytech.framework.base.entity.Page;
import org.apache.ibatis.annotations.Param;

public interface FamiliarCarMapper {

    PageInfo<FamiliarCar> familiarCarPage(@Param("familiarCar") FamiliarCar familiarCar, Page page);

    int delete(@Param("id") Integer id);

}
