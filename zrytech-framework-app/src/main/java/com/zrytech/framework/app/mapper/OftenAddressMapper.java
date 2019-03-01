package com.zrytech.framework.app.mapper;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.app.entity.OftenAddress;
import com.zrytech.framework.base.entity.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface OftenAddressMapper {

    void batchSave(@Param("cargoId") int cargoId, @Param("list") List<OftenAddress> list, @Param("date") Date date);

    PageInfo<OftenAddress> addressPage(@Param("oftenAddress") OftenAddress oftenAddress, Page page);

    OftenAddress get(@Param("id") Integer id);

    int update(OftenAddress oftenAddress);

    int delete(@Param("id") Integer id);
}
