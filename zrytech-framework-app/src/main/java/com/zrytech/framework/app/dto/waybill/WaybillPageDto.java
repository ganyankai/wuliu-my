package com.zrytech.framework.app.dto.waybill;

import lombok.Getter;
import lombok.Setter;

/**
 * 运单分页搜索条件
 * @author cat
 *
 */
@Setter
@Getter
public class WaybillPageDto {

	private Integer id;

	private Integer createBy;

	private String carOwnerName;

	private Integer carOwnerId;
	
	private String cargoOwnerName;

	private Integer cargoOwnerId;

	private Integer cargoId;

	private String billNo;

	private String payType;

	private String payWay;

	private String billType;

	private String status;

}
