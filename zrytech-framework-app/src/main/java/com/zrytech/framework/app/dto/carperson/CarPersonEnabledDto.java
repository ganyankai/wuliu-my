package com.zrytech.framework.app.dto.carperson;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


/**
 * 司机启用禁用入参
 * @author cat
 */
@Setter
@Getter
public class CarPersonEnabledDto {

	/**司机Id*/
	@NotNull(message = "Id不能为空")
	private Integer id;
	
	/**启用状态【true：启用/false：禁用】*/
	@NotNull(message = "状态不能为空")
	private Boolean enabled;
	
}
