package com.zrytech.framework.app.mapper;

import com.github.pagehelper.PageInfo;
import com.zrytech.framework.app.dto.cargosource.CargoRecDto;
import com.zrytech.framework.app.dto.cargosource.CargoSourceSearchDto;
import com.zrytech.framework.app.entity.Cargo;
import com.zrytech.framework.app.entity.Offer;
import com.zrytech.framework.base.entity.Page;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

public interface CargoMapper {
    PageInfo<Cargo> cargoPage(@Param("cargo") Cargo cargo, @Param("orderField") String orderField, Page page);

    Cargo get(@Param("id") Integer id);

    int updateAudit(Cargo cargo);

    int pushSave(Cargo cargo);

    void batch(@Param("list") List<Integer> list, @Param("id") Integer id, @Param("date") Date date);

    void updateSource(Cargo cargo);

    int deleteSource(Integer id);

    int invitationOffer(@Param("list") List<Offer> offerList, @Param("status") String status);

    Offer getOfferWill(@Param("cargoId") Integer cargoId, @Param("carOwnnerId") Integer carOwnnerId);

    int updateMatter(@Param("cargoId") Integer cargoId, @Param("offerPromissed") String offerPromissed, @Param("cargd") Integer cargd);

    @Update("update `cargo` set `status` = #{status} where `id` = #{id}")
    int updateStatusById(@Param("id") Integer id, @Param("status") String status);
    
    List<Cargo> cargoSearch(@Param("cargo") CargoSourceSearchDto dto);

    List<Cargo> myCargoSearch(@Param("cargo") CargoSourceSearchDto dto);
    //推荐货源
    List<Cargo> recommendCargo(@Param("carOwnnerId") Integer carOwnnerId);

}
