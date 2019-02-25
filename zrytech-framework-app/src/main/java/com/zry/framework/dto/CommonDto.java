package com.zry.framework.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;

import lombok.Setter;

@Setter
@Getter
public class CommonDto {
	
	@NotNull(message = "Id不能为空")
	private Integer id;

}
