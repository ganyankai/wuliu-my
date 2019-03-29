package com.zrytech.framework.app.dto.carperson;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * 司机账号的启用禁用入参
 * 
 * @author cat
 */
@Setter
@Getter
public class CarPersonEnabledDto {

	/** 司机Id */
	@NotNull(message = "司机Id不能为空")
	private Integer id;

	/** 状态：启用（true）、禁用（false） */
	@NotNull(message = "状态不能为空")
	private Boolean enabled;

}
