package com.zrytech.framework.app.dto.carlocation;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

/**
 * 车辆位置分页的搜索条件
 */
@Setter
@Getter
public class CarLocationPageDto {
	
	/**车牌号*/
	@NotBlank(message = "车牌号不能为空")
	private String carNo;
	
}
