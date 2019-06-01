package com.zrytech.framework.app.repository;

import com.zrytech.framework.app.entity.WlFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WlFileRepository extends JpaRepository<WlFile,Integer> {

}
