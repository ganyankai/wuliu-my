package com.zry.framework.mapper;

import com.github.pagehelper.PageInfo;
import com.zry.framework.entity.Cargo;
import com.zrytech.framework.base.entity.Page;
import org.apache.ibatis.annotations.Param;

public interface CargoMapper {
    PageInfo<Cargo> cargoPage(@Param("cargo") Cargo cargo, @Param("orderField") String orderField, Page page);

    Cargo get(@Param("id") Integer id);

    int updateAudit(Cargo cargo);

    int pushSave(Cargo cargo);

}
