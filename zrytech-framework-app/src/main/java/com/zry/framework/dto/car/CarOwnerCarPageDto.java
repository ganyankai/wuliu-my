package com.zry.framework.dto.car;

import lombok.Getter;
import lombok.Setter;

/**
 * 车主车辆分页入参
 * @author cat
 *
 */

@Setter
@Getter
public class CarOwnerCarPageDto {
	
	/**
	 * 主键
	 */
	// private Integer id;
	
	/**
	 * 车牌号
	 */
	private String carNo;
	
	/**
	 * 车主子账号Id
	 */
	// private Integer createBy;
	
	/**
	 * 车辆类型
	 */
	private String carType;

	/**
	 * 车主企业名称
	 */
	//private String name;
	
	/**
	 * 删除标识
	 */
	//private Boolean isDelete;
	
	/**
	 * 车辆状态
	 */
	private String status;
	
}
