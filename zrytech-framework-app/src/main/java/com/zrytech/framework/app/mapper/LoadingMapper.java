package com.zrytech.framework.app.mapper;

import com.zrytech.framework.app.entity.Loading;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LoadingMapper {

    List<Loading> selectLoadingList(@Param("id") Integer id, @Param("loadingType") String loadingType);

    void batchSave(@Param("list") List<Loading> loadingList, @Param("loadingType") String loadingType, @Param("cargoId") Integer cargoId);

    int batchDelete(@Param("list") List<Integer> list);

    void batchAdds(@Param("list") List<Loading> batchAdds,@Param("cargoId") Integer cargoId);

    void batchUpdate(@Param("list") List<Loading> updateList);
}
