package com.zrytech.framework.app.dao.impl;

import com.zrytech.framework.app.dao.LoadingDao;
import com.zrytech.framework.app.entity.Loading;
import com.zrytech.framework.app.mapper.LoadingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(rollbackFor = Exception.class)
public class LoadingDaoImpl implements LoadingDao {

    @Autowired
    private LoadingMapper loadingMapper;

    @Override
    public List<Loading> selectLoadingList(Integer id, String loadingType) {
        return loadingMapper.selectLoadingList(id, loadingType);
    }

    @Override
    public void batchSave(List<Loading> loadingList, String loadingType, Integer cargoId) {
        loadingMapper.batchSave(loadingList, loadingType, cargoId);
    }

    @Override
    public List<Integer> getListByIds(List<Loading> list) {
        List<Integer> ids = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (Loading loading : list) {
                if (!ids.contains(loading.getId())) {
                    ids.add(loading.getId());
                }
            }
        }
        return ids;
    }

    @Override
    public void batchDelete(List<Integer> deleteIds) {
        loadingMapper.batchDelete(deleteIds);
    }

    @Override
    public void batchAdds(List<Loading> batchAdds, Integer cargoId) {
        loadingMapper.batchAdds(batchAdds,cargoId);
    }

    @Override
    public void batchUpdate(List<Loading> updateList) {
        loadingMapper.batchUpdate(updateList);
    }
}
