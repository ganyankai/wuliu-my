package com.zrytech.framework.app.dao;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.app.entity.HotPlace;
import com.zrytech.framework.base.entity.Page;

public interface HotPlaceDao {

    PageInfo<HotPlace> hotPlacePage(HotPlace hotPlace, Page page);

    HotPlace get(Integer id);

    int update(HotPlace hotPlace);
    
    int delete(Integer id);

}
