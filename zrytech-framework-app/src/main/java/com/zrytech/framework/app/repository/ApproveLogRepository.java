package com.zrytech.framework.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zrytech.framework.app.entity.ApproveLog;

@Repository
public interface ApproveLogRepository extends JpaRepository<ApproveLog, Integer> {

}
