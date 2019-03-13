package com.zrytech.framework.app.dto.car;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;


/**
 * 车主 - 添加车辆入参
 * @author cat
 *
 */
@Setter
@Getter
public class CarAddDto {

	/**车牌号*/
	@NotBlank(message = "车牌号不能为空")
	@Pattern(regexp = "^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$", message = "车牌号格式错误")
	private String carNo;
	
	/**核载量*/
	@NotNull(message = "核载量不能为空")
	private Integer carLoad;
	
	/**核载量单位*/
	@NotBlank(message = "核载量单位不能为空")
	private String carUnit;
	
	/**车辆类型*/
	@NotBlank(message = "车辆类型不能为空")
	private String carType;
	
	/**是否分仓*/
	@NotNull(message = "是否分仓不能为空")
	private Boolean mulStore;
	
	/**仓位数量*/
	@NotNull(message = "仓位数量不能为空")
	private Integer storeQty;
	
}
