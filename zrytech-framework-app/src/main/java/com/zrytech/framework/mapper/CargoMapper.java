package com.zrytech.framework.mapper;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.entity.Cargo;
import com.zrytech.framework.base.entity.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface CargoMapper {
    PageInfo<Cargo> cargoPage(@Param("cargo") Cargo cargo, @Param("orderField") String orderField, Page page);

    Cargo get(@Param("id") Integer id);

    int updateAudit(Cargo cargo);

    int pushSave(Cargo cargo);

    void batch(@Param("list") List<Integer> list, @Param("id") Integer id,@Param("date") Date date);

    void updateSource(Cargo cargo);

    int deleteSource(Integer id);

    int invitationOffer(@Param("cargoId") Integer id, @Param("carOwnnerId") Integer carOwnnerId,@Param("date") Date date);
}
