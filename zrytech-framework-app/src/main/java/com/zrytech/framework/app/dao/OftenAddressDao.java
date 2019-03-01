package com.zrytech.framework.app.dao;


import com.github.pagehelper.PageInfo;
import com.zrytech.framework.app.entity.OftenAddress;
import com.zrytech.framework.base.entity.Page;

import java.util.Date;
import java.util.List;

public interface OftenAddressDao {

    void batchSave(int cargoId, List<OftenAddress> list, Date date);

    PageInfo<OftenAddress> addressPage(OftenAddress oftenAddress, Page page);

    OftenAddress get(Integer id);

    int update(OftenAddress oftenAddress);

    int delete(Integer id);

}
