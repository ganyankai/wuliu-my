package com.zrytech.framework.app.dto.car;

import lombok.Getter;
import lombok.Setter;

/**
 * 管理员 - 车辆分入参
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
	private String carType;

	/**
	 * 删除标识
	 */
	private Boolean isDelete;

	/**
	 * 车辆状态
	 */
	private String status;
	
	/**
	 * 审批状态
	 */
	private String approveStatus;

}
