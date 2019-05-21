package com.zrytech.framework.app.repository;

import com.zrytech.framework.app.entity.FamiliarCar;
import com.zrytech.framework.app.entity.OfenLocation;
import com.zrytech.framework.base.repository.BaseRepository;

import java.util.List;

public interface FamiliarCarRepository extends BaseRepository<FamiliarCar, Integer> {
    /**
     * 关注列表
     * @param cargoOwnnerId
     * @return
     */
    List<FamiliarCar> findByCargoOwnnerId(Integer cargoOwnnerId);

    /**
     * 查询关注人与被关注人的唯一记录
     * @param cargoOwnnerId,carOwnnerId
     * @return
     */
    List<FamiliarCar> findByCargoOwnnerIdAndCarOwnnerId(Integer cargoOwnnerId,Integer carOwnnerId);

}
