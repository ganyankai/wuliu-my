package com.zry.framework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zry.framework.entity.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Integer> {

}
