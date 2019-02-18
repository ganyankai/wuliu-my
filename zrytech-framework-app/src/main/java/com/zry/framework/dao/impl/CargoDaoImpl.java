package com.zry.framework.dao.impl;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.price.dao.CargoDao;
import com.zrytech.framework.price.entity.Cargo;
import com.zrytech.framework.price.mapper.CargoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(rollbackFor = Exception.class)
public class CargoDaoImpl implements CargoDao {

    @Autowired
    private CargoMapper cargoMapper;

    @Override
    public PageInfo<Cargo> cargoPage(Cargo cargo, String orderField, Page page) {
        if (page == null) {
            page = new Page();
        }
        return cargoMapper.cargoPage(cargo, orderField, page);
    }

    @Override
    public Cargo get(Integer id) {
        return cargoMapper.get(id);
    }

    @Override
    public int updateAudit(Cargo cargo) {
        return cargoMapper.updateAudit(cargo);
    }
}
