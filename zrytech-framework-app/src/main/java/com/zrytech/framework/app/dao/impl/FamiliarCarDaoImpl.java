package com.zrytech.framework.app.dao.impl;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.app.dao.FamiliarCarDao;
import com.zrytech.framework.app.entity.FamiliarCar;
import com.zrytech.framework.app.mapper.FamiliarCarMapper;
import com.zrytech.framework.base.entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FamiliarCarDaoImpl implements FamiliarCarDao {

    @Autowired
    private FamiliarCarMapper familiarCarMapper;

    public PageInfo<FamiliarCar> familiarCarPage(FamiliarCar familiarCar, Page page) {
        if(page==null){
            page=new Page();
        }
        return familiarCarMapper.familiarCarPage(familiarCar,page);
    }

    @Override
    public int delete(Integer id) {
        return familiarCarMapper.delete(id);
    }

}
