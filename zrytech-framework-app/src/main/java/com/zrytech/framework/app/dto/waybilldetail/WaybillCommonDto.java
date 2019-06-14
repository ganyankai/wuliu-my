package com.zrytech.framework.app.dto.waybilldetail;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 *	云单项列表
 */
@Setter
@Getter
public class WaybillCommonDto {

	/** 车主或货主Id */
//	@NotNull(message = "车主或货主Id不能为空")
	private Integer id;

}
