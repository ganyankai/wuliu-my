package com.zrytech.framework.app.dao.impl;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.app.dao.OfenLocationDao;
import com.zrytech.framework.app.dao.OftenAddressDao;
import com.zrytech.framework.app.entity.OfenLocation;
import com.zrytech.framework.app.entity.OftenAddress;
import com.zrytech.framework.app.mapper.OfenLocationMapper;
import com.zrytech.framework.app.mapper.OftenAddressMapper;
import com.zrytech.framework.base.entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
@Transactional(rollbackFor = Exception.class)
public class OfenLocationDaoImpl implements OfenLocationDao {

    @Autowired
    private OfenLocationMapper ofenLocationMapper;

//    @Override
    public PageInfo<OfenLocation> addressPage(OfenLocation oftenAddress, Page page) {
        if(page==null){
            page=new Page();
        }
        return ofenLocationMapper.addressPage(oftenAddress,page);
    }

    @Override
    public OfenLocation get(Integer id) {
        return ofenLocationMapper.get(id);
    }

    @Override
    public int update(OfenLocation ofenLocation) {
        return ofenLocationMapper.update(ofenLocation);
    }

    @Override
    public int delete(Integer id) {
        return ofenLocationMapper.delete(id);
    }
}
