package com.zry.framework.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * 车辆位置分页的搜索条件
 */
@Setter
@Getter
public class CarLocationPageDto {
	
	/**车牌号*/
	@NotNull(message = "车牌号不能为空")
	private String carNo;
	
}
