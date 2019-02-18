package com.zry.framework.dao.impl;

import com.zry.framework.dao.OftenAddressDao;
import com.zry.framework.entity.OftenAddress;
import com.zry.framework.mapper.OftenAddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
@Transactional(rollbackFor = Exception.class)
public class OftenAddressDaoImpl implements OftenAddressDao {

    @Autowired
    private OftenAddressMapper oftenAddressMapper;

    @Override
    public void batchSave(int cargoId, List<OftenAddress> list, Date date) {
        oftenAddressMapper.batchSave(cargoId,list,date);
    }
}
