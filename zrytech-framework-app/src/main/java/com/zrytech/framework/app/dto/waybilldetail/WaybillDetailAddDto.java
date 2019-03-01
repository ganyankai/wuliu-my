package com.zrytech.framework.app.dto.waybilldetail;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.zrytech.framework.app.dto.billlocation.BillLocationAddDto;

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
	@NotNull(message = "车辆不能为空")
	private Integer carId;
	
	/**司机Id*/
	@NotNull(message = "司机不能为空")
	private Integer driverId;
	
	/**压货人Id*/
	@NotNull(message = "压货人不能为空")
	private Integer supercargoId;
	
	/**运输数量*/
	@NotNull(message = "运输数量不能为空")
	private Integer qty;
	
	/**运单装卸地*/
	@NotNull(message = "运单装卸地不能为空")
	private List<BillLocationAddDto> billLocations;
	
}
