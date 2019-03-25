package com.zrytech.framework.app.dto.carsource;

import javax.validation.constraints.Pattern;

import com.zrytech.framework.app.constants.ApproveConstants;
import com.zrytech.framework.app.constants.CarConstants;
import com.zrytech.framework.app.constants.CarSourceConstants;

import lombok.Getter;
import lombok.Setter;

/**
 * 车源分页搜索入参
 * 
 * @author cat
 *
 */
@Setter
@Getter
public class CarSourcePageDto {

	/** 车源Id */
	private Integer id;

	/** 创建人Id */
	private Integer createBy;

	/** 车主Id */
	private Integer carOwnerId;

	/** 车源状态 */
	@Pattern(regexp = CarSourceConstants.REG_EX_CAR_SOURCE_STATUS, message = CarSourceConstants.REG_EX_CAR_SOURCE_STATUS_ERR_MSG)
	private String status;

	/** 车源的车辆类型 */
	@Pattern(regexp = CarConstants.REG_EX_CAR_TYPE, message = CarConstants.REG_EX_CAR_TYPE_ERR_MSG)
	private String carType;

	/** 车源的审批类型 */
	@Pattern(regexp = ApproveConstants.REG_EX_APPROVE_STATUS, message = ApproveConstants.REG_EX_APPROVE_STATUS_ERR_MSG)
	private String approveStatus;

	/** 车主企业名称 */
	private String carOwnerName;

}
