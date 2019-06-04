package com.zrytech.framework.app.dto.carsource;

import javax.validation.constraints.Pattern;

import com.zrytech.framework.app.constants.ApproveConstants;
import com.zrytech.framework.app.constants.CarConstants;
import com.zrytech.framework.app.constants.CarSourceConstants;

import io.swagger.annotations.ApiModelProperty;
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



	@ApiModelProperty(value = "出发省", required = false)
	private String startProvince;

	@ApiModelProperty(value = "出发市", required = false)
	private String startCity;

	@ApiModelProperty(value = "出发县", required = false)
	private String startCountry;

	@ApiModelProperty(value = "达到省", required = false)
	private String endProvince;

	@ApiModelProperty(value = "达到市", required = false)
	private String endCity;

	@ApiModelProperty(value = "达到县", required = false)
	private String endCountry;

	//空闲运输量
	private Integer freeQty;

	@Pattern(regexp = "^(free_qty)|()$", message = "排序方式有误")
	private String sort;

	@Pattern(regexp = "^(asc)|(desc)|()$", message = "排序方向有误")
	private String direction;
}
