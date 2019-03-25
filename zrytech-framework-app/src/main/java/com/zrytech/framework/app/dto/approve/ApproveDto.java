package com.zrytech.framework.app.dto.approve;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApproveDto {
	
	@NotNull(message = "业务Id不能为空")
	private Integer businessId; 
	
	@NotBlank(message = "审核内容不能为空")
	private String content; 
	
	@NotBlank(message = "审核结果不能为空")
	private String result;
	
}
