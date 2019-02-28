package com.zrytech.framework.dao;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.entity.Certification;
import com.zrytech.framework.base.entity.Page;

public interface ShipperDao {

    int save(Certification certification);

    int updateCertification(Certification certification);

    Certification get(Integer id);

    Certification getCustomerId(Integer customerId);

    PageInfo<Certification> certificationPage(Certification certification, Page page);

    int updateAudit(Certification certification);

    int avoidAudit(Certification certification);

}
