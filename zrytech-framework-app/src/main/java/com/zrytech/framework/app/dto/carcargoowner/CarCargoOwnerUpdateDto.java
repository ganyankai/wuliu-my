package com.zrytech.framework.app.dto.carcargoowner;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * 修改车主货主不需要审批字段入参
 * 
 * @author cat
 *
 */
@Setter
@Getter
public class CarCargoOwnerUpdateDto {

	/** 经度 */
	private BigDecimal longitude;

	/** 纬度 */
	private BigDecimal latitude;

	/** 省份 */
	private String province;

	/** 城市 */
	private String city;

	/** 县 */
	private String county;

	/** 地址详情 */
	private String addressDetail;

	/** 企业简介 */
	private String intro;
	
	/** 头像 */
	private String headImg;
	
}
