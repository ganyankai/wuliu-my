package com.zry.framework.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CargoMatterPageDto {
	
	/**
	 * 报价单Id
	 */
	private Integer id;
	
	/**
	 * 车主企业名称
	 */
	private String carOwnerName;
	
	/**
	 * 报价单状态
	 */
	private String status;
	
	/**
	 * 车主Id
	 */
	private Integer carOwnerId;
	
	/**
	 * 货源Id
	 */
	private Integer cargoId;
	

}
