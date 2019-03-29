package com.zrytech.framework.app.dto.carperson;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.zrytech.framework.app.constants.RegExConstants;

import lombok.Getter;
import lombok.Setter;

/**
 * 修改司机压货人需要审批的字段入参
 * 
 * @author cat
 *
 */
@Setter
@Getter
public class CarPersonCheckUpdateDto {

	/** 司机压货人Id */
	@NotNull(message = "Id不能为空")
	private Integer id;

	@NotBlank(message = "手机号不能为空")
	@Pattern(regexp = RegExConstants.CHINA_PHONE_NUMBER, message = RegExConstants.CHINA_PHONE_NUMBER_ERR_MSG)
	private String tel;

	@NotBlank(message = "性别不能为空")
	@Pattern(regexp = RegExConstants.SEX, message = RegExConstants.SEX_ERR_MSG)
	private String sex;

	@NotNull(message = "年龄不能为空")
	@Range(min = 18, max = 105, message = "年龄取值范围[18-105]")
	private Integer age;

	@NotBlank(message = "身份证不能为空")
	private String idCard;

	@NotBlank(message = "驾驶证不能为空")
	private String drivingLicence;

}
