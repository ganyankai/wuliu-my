package com.zrytech.framework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zrytech.framework.entity.ApproveLog;

@Repository
public interface ApproveLogRepository extends JpaRepository<ApproveLog, Integer> {

}
