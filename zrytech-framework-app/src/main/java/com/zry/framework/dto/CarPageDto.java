package com.zry.framework.dto;

import org.hibernate.validator.constraints.NotEmpty;

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
	private String cartype;

	/**
	 * 车主企业名称
	 */
	private String name;
}
