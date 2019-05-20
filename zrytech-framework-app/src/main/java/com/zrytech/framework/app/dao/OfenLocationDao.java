package com.zrytech.framework.app.dao;


import com.github.pagehelper.PageInfo;
import com.zrytech.framework.app.entity.OfenLocation;
import com.zrytech.framework.app.entity.OftenAddress;
import com.zrytech.framework.base.entity.Page;

import java.util.Date;
import java.util.List;

public interface OfenLocationDao {

//    void batchSave(int cargoId, List<OftenAddress> list, Date date);

    PageInfo<OfenLocation> addressPage(OfenLocation ofenLocation, Page page);

    OfenLocation get(Integer id);

    int update(OfenLocation ofenLocation);

    int delete(Integer id);

}
