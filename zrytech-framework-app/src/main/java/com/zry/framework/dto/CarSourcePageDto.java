package com.zry.framework.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 车源分页搜索
 * 
 * @author cat
 *
 */
@Setter
@Getter
public class CarSourcePageDto {
	
	/**
	 * 车源Id
	 */
	private Integer id;
	
	/**
	 * 车主Id
	 */
	private Integer createBy;
	
	/**
	 * 车源状态
	 */
	private String status;
	
	/**
	 * 车主企业名称
	 */
	private String carOwnerName;
	
	/**
	 * 车源的车辆类型
	 */
	private String carType;
	
}
