package com.zry.framework.mapper;

import com.zrytech.framework.price.entity.Loading;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LoadingMapper {

    List<Loading> selectLoadingList(@Param("id") Integer id, @Param("loadingType") String loadingType);

    void batchSave(@Param("list") List<Loading> loadingList, @Param("loadingType") String loadingType, @Param("cargoId") Integer cargoId);
}
