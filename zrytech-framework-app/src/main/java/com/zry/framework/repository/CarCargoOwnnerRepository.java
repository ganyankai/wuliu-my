package com.zry.framework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zry.framework.entity.CarCargoOwnner;

@Repository
public interface CarCargoOwnnerRepository extends JpaRepository<CarCargoOwnner, Integer> {

}
