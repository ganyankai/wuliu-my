package com.zrytech.framework.app.dto.car;

import javax.validation.constraints.Pattern;

import com.zrytech.framework.app.constants.ApproveConstants;
import com.zrytech.framework.app.constants.CarConstants;

import lombok.Getter;
import lombok.Setter;

/**
 * 车辆分页入参
 * 
 * @author cat
 */
@Setter
@Getter
public class CarPageDto {

	/**
	 * 车牌号（模糊搜索）
	 */
	private String carNo;

	/**
	 * 车主Id
	 */
	private Integer carOwnerId;

	/**
	 * 创建人Id
	 */
	private Integer createBy;

	/**
	 * 车主企业名称（模糊搜索）
	 */
	private String carOwnerName;

	/**
	 * 车辆类型
	 */
	//@Pattern(regexp = CarConstants.REG_EX_CAR_TYPE, message = CarConstants.REG_EX_CAR_TYPE_ERR_MSG)
	private String carType;

	/**
	 * 删除标识
	 */
	private Boolean isDelete;

	/**
	 * 车辆状态
	 */
	@Pattern(regexp = CarConstants.REG_EX_CAR_STATUS, message = CarConstants.REG_EX_CAR_STATUS_ERR_MSG)
	private String status;

	/**
	 * 审批状态
	 */
	@Pattern(regexp = ApproveConstants.REG_EX_APPROVE_STATUS, message = ApproveConstants.REG_EX_APPROVE_STATUS_ERR_MSG)
	private String approveStatus;

}
