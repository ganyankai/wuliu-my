package com.zry.framework.dao.impl;

import com.github.pagehelper.PageInfo;
import com.zry.framework.dao.WaybillDao;
import com.zry.framework.entity.Waybill;
import com.zry.framework.mapper.WaybillMapper;
import com.zrytech.framework.base.entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(rollbackFor = Exception.class)
public class WaybillDaoImpl implements WaybillDao {

    @Autowired
    private WaybillMapper waybillMapper;

    @Override
    public int createIndent(Waybill waybill) {
        return waybillMapper.insert(waybill);
    }

    @Override
    public int updateIndentStatus(Waybill waybill) {
        return waybillMapper.updateIndentStatus(waybill);
    }

    @Override
    public PageInfo<Waybill> indentPage(Waybill waybill, Page page) {
        if (page == null) {
            page = new Page();
        }
        return waybillMapper.indentPage(waybill, page);
    }

    @Override
    public List<String> coundIndent(Integer cargoOwnnerId) {
        return waybillMapper.coundIndent(cargoOwnnerId);
    }

    @Override
    public Waybill get(Integer id) {
        return waybillMapper.get(id);
    }

    @Override
    public int changeIndent(Waybill waybill) {
        return waybillMapper.changeIndent(waybill);
    }
}
