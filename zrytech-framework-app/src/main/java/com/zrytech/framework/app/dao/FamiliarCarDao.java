package com.zrytech.framework.app.dao;


import com.github.pagehelper.PageInfo;
import com.zrytech.framework.app.entity.FamiliarCar;
import com.zrytech.framework.base.entity.Page;

public interface FamiliarCarDao {

    PageInfo<FamiliarCar> familiarCarPage(FamiliarCar familiarCar, Page page);

    int delete(Integer id);

}
