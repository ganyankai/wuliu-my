package com.zrytech.framework.app.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;

import lombok.Setter;

@Setter
@Getter
public class DeleteDto {
	
	@NotNull(message = "Id不能为空")
	private Integer id;

}
