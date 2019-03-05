package com.zrytech.framework.app.dto.car;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;


/**
 * 添加车辆入参
 * @author cat
 *
 */
@Setter
@Getter
public class CarAddDto {

	/**车牌号*/
	@NotEmpty(message = "车牌号不能为空")
	private String carNo;
	
	/**核载量*/
	@NotNull(message = "核载量不能为空")
	private Integer carLoad;
	
	/**核载量单位*/
	@NotEmpty(message = "核载量单位不能为空")
	private String carUnit;
	
	/**车辆类型*/
	@NotEmpty(message = "车辆类型不能为空")
	private String carType;
	
	/**是否分仓*/
	@NotNull(message = "是否分仓不能为空")
	private Boolean mulStore;
	
	/**仓位数量*/
	@NotNull(message = "仓位数量不能为空")
	private Integer storeQty;
	
}
