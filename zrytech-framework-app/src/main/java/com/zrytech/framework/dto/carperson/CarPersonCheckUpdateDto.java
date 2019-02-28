package com.zrytech.framework.dto.carperson;

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
public class CarPersonCheckUpdateDto {
	
	/**车主司机Id*/
	@NotNull(message = "Id不能为空")
	private Integer id;
	
	@NotEmpty(message = "手机号不能为空")
	private String tel;
	
	@NotEmpty(message = "性别不能为空")
	private String sex;
	
	@NotNull(message = "年龄不能为空")
	private Integer age;
	
	@NotEmpty(message = "身份证不能为空")
	private String idCard;
	
	@NotEmpty(message = "驾驶证不能为空")
	private String drivingLicence;

}
