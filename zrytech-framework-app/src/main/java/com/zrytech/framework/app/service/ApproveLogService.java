package com.zrytech.framework.app.service;

import org.springframework.stereotype.Service;

import com.zrytech.framework.app.dto.approve.ApproveDto;
import com.zrytech.framework.app.entity.ApproveLog;

@Service
public interface ApproveLogService {
	
	
	/**
	 * 添加审批记录
	 * @author cat
	 * 
	 * @param dto	审批结果
	 * @param approveBy	审批人Id
	 * @param approveType	审批类型
	 * @return
	 */
	public ApproveLog addApproveLog(ApproveDto dto, Integer approveBy, String approveType);

}
