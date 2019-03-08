package com.zrytech.framework.app.mapper;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.app.entity.Cargo;
import com.zrytech.framework.app.entity.Offer;
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

    int invitationOffer(@Param("list") List<Offer> offerList, @Param("status") String status);

    Offer getOfferWill(@Param("cargoId") Integer cargoId,@Param("carOwnnerId") Integer carOwnnerId);

    int updateMatter(@Param("cargoId") Integer cargoId,@Param("offerPromissed") String offerPromissed,@Param("cargd") Integer cargd);
}
