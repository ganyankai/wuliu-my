package com.zry.framework.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CheckDto {
	
	@NotNull(message = "业务Id不能为空")
	private Integer businessId; 
	
	@NotEmpty(message = "审核内容不能为空")
	private String content; 
	
	@NotEmpty(message = "审核结果不能为空")
	private String result;
	
}
