package com.zrytech.framework.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 车辆分页的搜索条件
 */
@Setter
@Getter
public class CarPageDto {
	
	/**
	 * 主键
	 */
	private Integer id;
	
	/**
	 * 车牌号
	 */
	private String carNo;
	
	/**
	 * 车主Id
	 */
	private Integer createBy;
	
	/**
	 * 车辆类型
	 */
	private String carType;

	/**
	 * 车主企业名称
	 */
	private String name;
	
	/**
	 * 删除标识
	 */
	private Boolean isDelete;
	
	/**
	 * 车辆状态
	 */
	private String status;
}
