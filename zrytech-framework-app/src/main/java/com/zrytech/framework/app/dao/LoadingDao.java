package com.zrytech.framework.app.dao;


import com.zrytech.framework.app.entity.Loading;

import java.util.List;

public interface LoadingDao {
    List<Loading> selectLoadingList(Integer id, String loadingType);

    void batchSave(List<Loading> loadingList, String loadingType, Integer cargoId);

    List<Integer> getListByIds(List<Loading> list);

    void batchDelete(List<Integer> deleteIds);

    void batchAdds(List<Loading> batchAdds, Integer cargoId);

    void batchUpdate(List<Loading> updateList);

}
