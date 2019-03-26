package com.zrytech.framework.app.dto.carsource;

import lombok.Getter;
import lombok.Setter;

/**
 * 车源分页搜索入参
 * @author cat
 *
 */
@Setter
@Getter
public class CarSourcePageDto {
	
	/**车源Id*/
	private Integer id;
	
	/**创建人Id*/
	private Integer createBy;
	
	/**车主Id*/
	private Integer carOwnerId;
	
	/**车源状态*/
	private String status;
	
	/**车源的车辆类型*/
	private String carType;
	
	/**车源的审批类型*/
	private String approveStatus;
	
	/**车主企业名称*/
	private String carOwnerName;
	
	
}