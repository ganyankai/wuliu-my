package com.zrytech.framework.app.repository;

import org.springframework.stereotype.Repository;

import com.zrytech.framework.app.entity.Cargo;
import com.zrytech.framework.base.repository.BaseRepository;

@Repository
public interface CargoRepository extends BaseRepository<Cargo, Integer> {

}
