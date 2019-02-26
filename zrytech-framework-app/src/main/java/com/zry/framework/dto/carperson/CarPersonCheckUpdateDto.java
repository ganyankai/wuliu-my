package com.zry.framework.dto.carperson;

import lombok.Getter;
import lombok.Setter;

/**
 * 司机压货人不需要检测字段更新入参
 * @author cat
 *
 */
@Setter
@Getter
public class CarPersonCheckUpdateDto {
	
	/**车主司机Id*/
	private Integer id;
	
	private String name;

}
