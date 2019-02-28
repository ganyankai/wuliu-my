package com.zrytech.framework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zrytech.framework.entity.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Integer> {

}
