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
	 * 车牌号
	 */
	private String carNo;
	
	/**
	 * 车辆类型
	 */
	private String carType;
	
	/**
	 * 车辆状态
	 */
	private String status;
	
	/**
	 * 创建人Id
	 */
	private Integer createBy;
	
}
