package com.zry.framework.dto;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

/**
 * 车辆分页的搜索条件
 */
@Setter
@Getter
public class CarPageDto {
	
	
	private Integer id;
	
	@NotEmpty(message = "牌照不能为空")
	private String carNo;
	
	

}
