package com.zrytech.framework.app.mapper;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.app.entity.HotPlace;
import com.zrytech.framework.base.entity.Page;
import org.apache.ibatis.annotations.Param;

public interface HotPlaceMapper {

    PageInfo<HotPlace> hotPlacePage(@Param("hotPlace") HotPlace hotPlace, Page page);

    HotPlace get(@Param("id") Integer id);

    //加了hotPlace以后需要hotPlace.
    int update(HotPlace hotPlace);

    int delete(@Param("id") Integer id);
}
