package com.zrytech.framework.app.dto.billlocation;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import lombok.Getter;
import lombok.Setter;

/**
 * 新增运单装卸地入参
 * 
 * @author cat
 *
 */
@Setter
@Getter
public class BillLocationAddDto {

	/** 装卸数量 */
	@NotNull(message = "装卸数量不能为空")
	@Range(min = 1, message = "装卸数量不能小于1")
	private Integer qty;

	/** 货源装卸地Id */
	@NotNull(message = "货源装卸地Id不能为空")
	private Integer cargoLocationId;

	/** 运单Id */
	@NotNull(message = "运单Id不能为空")
	private Integer waybillId;

	/** 运单项Id */
	@NotNull(message = "运单项Id不能为空")
	private Integer waybillDetailId;

	/** 说明 */
	private String remark;

}
