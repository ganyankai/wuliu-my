package com.zrytech.framework.app.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zrytech.framework.app.dto.approve.ApproveDto;
import com.zrytech.framework.app.entity.ApproveLog;
import com.zrytech.framework.app.repository.ApproveLogRepository;
import com.zrytech.framework.app.service.ApproveLogService;

@Service
public class ApproveLogServiceImpl implements ApproveLogService {

	@Autowired
	private ApproveLogRepository approveLogRepository;
	
	
	@Override
	public ApproveLog addApproveLog(ApproveDto dto, Integer approveBy, String approveType) {
		ApproveLog entity = new ApproveLog();
		entity.setApproveTime(new Date());
		entity.setApproveBy(approveBy);
		entity.setApproveType(approveType);
		entity.setApproveResult(dto.getResult());
		entity.setBusinessId(dto.getBusinessId());
		entity.setApproveContent(dto.getContent());
		approveLogRepository.save(entity);
		return entity;
	}
}
