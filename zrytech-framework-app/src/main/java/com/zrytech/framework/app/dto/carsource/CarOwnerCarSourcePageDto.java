package com.zrytech.framework.app.dto.carsource;

import lombok.Getter;
import lombok.Setter;

/**
 * 车主的车源分页搜索入参
 * 
 * @author cat
 *
 */
@Setter
@Getter
public class CarOwnerCarSourcePageDto {
	
	/**车主Id*/
	private Integer carOwnerId;
	
	/**创建人Id*/
	private Integer createBy;
	
	/**车源状态*/
	private String status;
	
	private String approveStatus;
	
}
