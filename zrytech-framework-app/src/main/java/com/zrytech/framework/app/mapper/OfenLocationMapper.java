package com.zrytech.framework.app.mapper;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.app.entity.OfenLocation;
import com.zrytech.framework.app.entity.OftenAddress;
import com.zrytech.framework.base.entity.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface OfenLocationMapper {

//    void batchSave(@Param("cargoId") int cargoId, @Param("list") List<OftenAddress> list, @Param("date") Date date);

    PageInfo<OfenLocation> addressPage(@Param("ofenLocation") OfenLocation ofenLocation, Page page);

    OfenLocation get(@Param("id") Integer id);

    int update(OfenLocation ofenLocation);

    int delete(@Param("id") Integer id);
}
