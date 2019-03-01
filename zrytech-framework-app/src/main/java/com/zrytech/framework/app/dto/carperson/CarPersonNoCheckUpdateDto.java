package com.zrytech.framework.app.dto.carperson;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

/**
 * 司机压货人不需要检测字段更新入参
 * @author cat
 *
 */
@Setter
@Getter
public class CarPersonNoCheckUpdateDto {
	
	/**司机或压货人Id*/
	@NotNull(message = "Id不能为空")
	private Integer id;
	
	@NotEmpty(message = "姓名不能为空")
	private String name;

}
