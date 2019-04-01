package com.zrytech.framework.app.dto.waybilldetail;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * 添加运单入参
 * 
 * @author cat
 *
 */
@Setter
@Getter
public class WaybillDetailAddDto {

	/** 运单Id */
	@NotNull(message = "运单Id不能为空")
	private Integer waybillId;

	/** 车辆Id */
	@NotNull(message = "车辆Id不能为空")
	private Integer carId;

	/** 司机Id */
	@NotNull(message = "司机Id不能为空")
	private Integer driverId;

	/** 压货人Id */
	@NotNull(message = "压货人Id不能为空")
	private Integer supercargoId;

}
