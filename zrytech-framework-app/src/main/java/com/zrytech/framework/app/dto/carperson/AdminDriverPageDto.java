package com.zrytech.framework.app.dto.carperson;

import javax.validation.constraints.Pattern;

import com.zrytech.framework.app.constants.ApproveConstants;
import com.zrytech.framework.app.constants.CarPersonConstants;

import lombok.Getter;
import lombok.Setter;

/**
 * 管理员 - 司机分页入参
 * 
 * @author cat
 *
 */
@Setter
@Getter
public class AdminDriverPageDto {

	/** 姓名 */
	private String name;

	/** 手机号 */
	private String tel;

	/** 删除标识 */
	private Boolean isDelete;

	/** 创建人Id */
	private Integer createBy;

	/** 车主Id */
	private Integer carOwnerId;

	/** 状态 */
	@Pattern(regexp = CarPersonConstants.REG_EX_PERSON_STATUS, message = CarPersonConstants.REG_EX_PERSON_STATUS_ERR_MSG)
	private String status;

	/** 审批状态 */
	@Pattern(regexp = ApproveConstants.REG_EX_APPROVE_STATUS, message = ApproveConstants.REG_EX_APPROVE_STATUS_ERR_MSG)
	private String approveStatus;
}
