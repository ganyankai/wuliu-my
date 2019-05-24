package com.zrytech.framework.app.dto.waybilldetail;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * 更新运单项入参
 */
@Setter
@Getter
public class WaybillDetailUpdateDto {

	/** 运单项Id */
	@NotNull(message = "运单项Id不能为空")
	private Integer waybillDetailId;

	/** 车辆Id */
	@NotNull(message = "车辆Id不能为空")
	private Integer carId;

	/** 司机Id */
	@NotNull(message = "司机Id不能为空")
	private Integer driverId;

	/** 压货人Id */
	@NotNull(message = "压货人Id不能为空")
	private Integer supercargoId;

	/** 数量 */
	@NotNull(message = "数量不能为空")
	private Integer qty;

}
