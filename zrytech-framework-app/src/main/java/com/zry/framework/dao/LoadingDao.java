package com.zry.framework.dao;


import com.zry.framework.entity.Loading;

import java.util.List;

public interface LoadingDao {
    List<Loading> selectLoadingList(Integer id, String loadingType);

    void batchSave(List<Loading> loadingList, String loadingType, Integer cargoId);

}
