package com.zry.framework.mapper;

import com.zry.framework.entity.OftenAddress;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface OftenAddressMapper {

    void batchSave(@Param("cargoId") int cargoId, @Param("list") List<OftenAddress> list, @Param("date") Date date);
}
