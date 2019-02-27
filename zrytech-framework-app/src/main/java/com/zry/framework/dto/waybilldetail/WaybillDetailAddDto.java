package com.zry.framework.dto.waybilldetail;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.zry.framework.dto.billlocation.BillLocationAddDto;

import lombok.Getter;
import lombok.Setter;

/**
 * 添加运单入参
 * @author cat
 *
 */
@Setter
@Getter
public class WaybillDetailAddDto {
	
	/**运单Id*/
	@NotNull(message = "运单Id不能为空")
	private Integer waybillId;

	/**车辆Id*/
	private Integer carId;
	
	/**司机Id*/
	private Integer driverId;
	
	/**压货人Id*/
	private Integer supercargoId;
	
	/**运输数量*/
	private Integer qty;
	
	/**运单装卸地*/
	private List<BillLocationAddDto> billLocations;
	
}
