package com.zry.framework.dao.impl;

import com.zrytech.framework.price.dao.LoadingDao;
import com.zrytech.framework.price.entity.Loading;
import com.zrytech.framework.price.mapper.LoadingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(rollbackFor = Exception.class)
public class LoadingDaoImpl implements LoadingDao {

    @Autowired
    private LoadingMapper loadingMapper;

    @Override
    public List<Loading> selectLoadingList(Integer id, String loadingType) {
        return loadingMapper.selectLoadingList(id,loadingType);
    }

    @Override
    public void batchSave(List<Loading> loadingList, String loadingType,Integer cargoId) {
        loadingMapper.batchSave(loadingList,loadingType,cargoId);
    }
}
