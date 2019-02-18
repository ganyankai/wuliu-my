package com.zry.framework.dao.impl;

import com.zrytech.framework.price.dao.OftenAddressDao;
import com.zrytech.framework.price.entity.OftenAddress;
import com.zrytech.framework.price.mapper.OftenAddressMapper;
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
