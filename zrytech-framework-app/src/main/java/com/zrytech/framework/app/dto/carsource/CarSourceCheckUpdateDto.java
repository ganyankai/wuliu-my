package com.zrytech.framework.app.dto.carsource;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

/**
 * 修改车源基本信息入参（需要审核的内容）
 * @author cat
 *
 */
@Setter
@Getter
public class CarSourceCheckUpdateDto {
	
	/**车源Id*/
	@NotNull(message = "车源Id不能为空")
	private Integer id;

	/**车辆类型*/
	@NotEmpty(message = "车辆类型不能为空")
    private String carType;
	
	/**核载量*/
	@NotNull(message = "核载量不能为空")
    private Integer qty;
    
}
