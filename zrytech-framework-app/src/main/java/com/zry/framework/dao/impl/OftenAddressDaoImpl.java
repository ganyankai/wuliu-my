package com.zry.framework.dao.impl;

import com.github.pagehelper.PageInfo;
import com.zry.framework.dao.OftenAddressDao;
import com.zry.framework.entity.OftenAddress;
import com.zry.framework.mapper.OftenAddressMapper;
import com.zrytech.framework.base.entity.Page;
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

    @Override
    public PageInfo<OftenAddress> addressPage(OftenAddress oftenAddress, Page page) {
        if(page==null){
            page=new Page();
        }
        return oftenAddressMapper.addressPage(oftenAddress,page);
    }

    @Override
    public OftenAddress get(Integer id) {
        return oftenAddressMapper.get(id);
    }

    @Override
    public int update(OftenAddress oftenAddress) {
        return oftenAddressMapper.update(oftenAddress);
    }

    @Override
    public int delete(Integer id) {
        return oftenAddressMapper.delete(id);
    }
}
