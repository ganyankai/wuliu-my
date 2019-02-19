package com.zry.framework.dao;

import com.github.pagehelper.PageInfo;
import com.zry.framework.entity.Cargo;
import com.zrytech.framework.base.entity.Page;

public interface CargoDao {
    PageInfo<Cargo> cargoPage(Cargo cargo, String orderField, Page page);

    Cargo get(Integer id);

    int updateAudit(Cargo cargo);

    int pushSave(Cargo cargo);
}