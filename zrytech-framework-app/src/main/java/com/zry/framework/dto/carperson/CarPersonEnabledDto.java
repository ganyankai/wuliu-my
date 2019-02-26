package com.zry.framework.dto.carperson;

import lombok.Getter;
import lombok.Setter;


/**
 * 司机与压货人启用禁用入参
 * @author cat
 */
@Setter
@Getter
public class CarPersonEnabledDto {

	/**司机或压货人Id*/
	private Integer id;
	
	/**启用状态【true：启用/false：禁用】*/
	private Boolean enabled;
	
}
