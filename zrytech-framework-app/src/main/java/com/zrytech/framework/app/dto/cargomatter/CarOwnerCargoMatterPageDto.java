package com.zrytech.framework.app.dto.cargomatter;

import lombok.Getter;
import lombok.Setter;

/**
 * 车主搜索报价单分页入参
 * @author cat
 *
 */
@Setter
@Getter
public class CarOwnerCargoMatterPageDto {
	
	/**
	 * 报价单状态
	 */
	private String status;
	
	/**创建人Id*/
	private Integer createBy;
	
}
