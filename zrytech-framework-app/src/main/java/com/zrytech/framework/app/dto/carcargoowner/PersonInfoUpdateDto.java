package com.zrytech.framework.app.dto.carcargoowner;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.zrytech.framework.app.constants.RegExConstants;

import lombok.Getter;
import lombok.Setter;

/**
 * 车主货主个人用户需要审批的信息
 * 
 * @author cat
 *
 */
@Setter
@Getter
public class PersonInfoUpdateDto {
	
	/** 法人姓名 */
	@NotBlank(message = "姓名不能为空")
	private String legalerName;

	/** 法人身份证号码 */
	@NotBlank(message = "身份证号码不能为空")
	private String legalerIdCardNo;

	/** 法人身份证照片 */
	@NotBlank(message = "身份证照片不能为空")
	private String legalerIdCardFront;

	/** 联系电话 */
	@NotBlank(message = "联系电话不能为空")
	@Pattern(regexp = RegExConstants.CHINA_PHONE_NUMBER, message = "联系电话格式有误")
	private String tel;
	
}
