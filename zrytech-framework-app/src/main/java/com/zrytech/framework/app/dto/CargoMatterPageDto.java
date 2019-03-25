package com.zrytech.framework.app.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CargoMatterPageDto {
	
	/**报价单Id*/
	private Integer id;
	
	/**车主企业名称*/
	private String carOwnerName;
	
	/**货主企业名称*/
	private String cargoOwnerName;
	
	/**报价单状态*/
	private String status;
	
	/**报价单状态*/
	private String approveStatus;
	
	/**车主Id*/
	private Integer carOwnerId;
	
	/**货主Id*/
	private Integer cargoOwnerId;
	
	/**货源Id*/
	private Integer cargoId;
	
	/**创建人Id*/
	private Integer createBy;
}
