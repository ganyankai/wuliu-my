package com.zry.framework.dao;

import com.github.pagehelper.PageInfo;
import com.zry.framework.entity.Cargo;
import com.zrytech.framework.base.entity.Page;

import java.util.Date;
import java.util.List;

public interface CargoDao {
    PageInfo<Cargo> cargoPage(Cargo cargo, String orderField, Page page);

    Cargo get(Integer id);

    int updateAudit(Cargo cargo);

    int pushSave(Cargo cargo);

    void batch(List<Integer> list, Integer id, Date date);

    void updateSource(Cargo cargo);

    int deleteSource(Integer id);

    int invitationOffer(Integer id, Integer carOwnnerId, Date date);

}
