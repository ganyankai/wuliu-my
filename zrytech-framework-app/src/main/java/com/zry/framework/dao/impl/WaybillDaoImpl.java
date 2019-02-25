package com.zry.framework.dao.impl;

import com.zry.framework.dao.WaybillDao;
import com.zry.framework.entity.Waybill;
import com.zry.framework.mapper.WaybillMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(rollbackFor = Exception.class)
public class WaybillDaoImpl implements WaybillDao {

    @Autowired
    private WaybillMapper waybillMapper;

    @Override
    public int createIndent(Waybill waybill) {
        return waybillMapper.createIndent(waybill);
    }
}
