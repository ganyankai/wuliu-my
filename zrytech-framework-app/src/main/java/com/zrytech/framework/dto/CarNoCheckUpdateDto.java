package com.zrytech.framework.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * 修改车辆信息入参（不需要审核的内容）
 * @author cat
 *
 */
@Setter
@Getter
public class CarNoCheckUpdateDto {
	
	/**车辆主键*/
	@NotNull(message = "车辆Id不能为空")
	private Integer id;
	
	/**司机*/
	private Integer driverId;
	
	/**压货人*/
	private Integer supercargoId;

}
