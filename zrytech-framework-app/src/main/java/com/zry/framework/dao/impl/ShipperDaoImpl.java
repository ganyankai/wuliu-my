package com.zry.framework.dao.impl;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.base.entity.Page;
import com.zrytech.framework.price.dao.ShipperDao;
import com.zrytech.framework.price.entity.Certification;
import com.zrytech.framework.price.mapper.ShipperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(rollbackFor = Exception.class)
public class ShipperDaoImpl implements ShipperDao {

    @Autowired
    private ShipperMapper shipperMapper;

    @Override
    public int save(Certification certification) {
        return shipperMapper.save(certification);
    }

    @Override
    public int updateCertification(Certification certification) {
        return shipperMapper.updateCertification(certification);
    }

    @Override
    public Certification get(Integer id) {
        return shipperMapper.get(id);
    }

    @Override
    public Certification getCustomerId(Integer customerId) {
        return shipperMapper.getCustomerId(customerId);
    }

    @Override
    public PageInfo<Certification> certificationPage(Certification certification, Page page) {
        if (page == null) {
            page = new Page();
        }
        return shipperMapper.certificationPage(certification, page);
    }

    @Override
    public int updateAudit(Certification certification) {
        return shipperMapper.updateAudit(certification);
    }

    @Override
    public int avoidAudit(Certification certification) {
        return shipperMapper.avoidAudit(certification);
    }
}
