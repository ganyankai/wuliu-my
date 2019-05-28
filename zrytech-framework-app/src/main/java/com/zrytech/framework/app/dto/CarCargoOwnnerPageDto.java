package com.zrytech.framework.app.dto;

import javax.validation.constraints.Pattern;

import com.zrytech.framework.app.constants.ApproveConstants;
import com.zrytech.framework.app.constants.CarCargoOwnerConstants;

import lombok.Getter;
import lombok.Setter;

/**
 * 车主、货主分页搜索入参
 * 
 * @author cat
 *
 */
@Setter
@Getter
public class CarCargoOwnnerPageDto {

	private Integer id;

	/** 法人姓名 */
	private String legalerName;

	/** 企业名称 */
	private String name;

	/** 联系电话 */
	private String tel;

	/** 免审核 */
	private Boolean avoidAudit;

	/** 类型 */
	@Pattern(regexp = CarCargoOwnerConstants.REG_EX_TYPE, message = CarCargoOwnerConstants.REG_EX_TYPE_ERR_MSG)
	private String type;

	/** 审批状态 */
	@Pattern(regexp = ApproveConstants.REG_EX_APPROVE_STATUS, message = ApproveConstants.REG_EX_APPROVE_STATUS_ERR_MSG)
	private String approveStatus;

	/** 状态 */
	@Pattern(regexp = CarCargoOwnerConstants.REG_EX_STATUS, message = CarCargoOwnerConstants.REG_EX_STATUS_ERR_MSG)
	private String status;
	
	/** 状态 */
	@Pattern(regexp = CarCargoOwnerConstants.REG_EX_STATUS, message = CarCargoOwnerConstants.REG_EX_STATUS_ERR_MSG)
	private String unstatus;

	/** 用户类型 */
	@Pattern(regexp = CarCargoOwnerConstants.REG_EX_CUSTOMER_TYPE, message = CarCargoOwnerConstants.REG_EX_CUSTOMER_TYPE_ERR_MSG)
	private String customerType;

	/** 账号手机号 */
	private String customerTel;

	/** 账号用户名 */
	private String userAccount;

}
