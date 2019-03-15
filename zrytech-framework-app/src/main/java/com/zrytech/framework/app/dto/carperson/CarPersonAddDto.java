package com.zrytech.framework.app.dto.carperson;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.zrytech.framework.app.constants.RegExConstants;

import lombok.Getter;
import lombok.Setter;


/**
 * 车主 - 添加司机，压货人入参
 * @author cat
 *
 */
@Setter
@Getter
public class CarPersonAddDto {
	
	/**司机或压货人的姓名*/
	@NotBlank(message = "姓名不能为空")
    private String name;
	
	/**手机号码*/
	@NotBlank(message = "手机号码不能为空")
	@Pattern(regexp = RegExConstants.CHINA_PHONE_NUMBER, message = RegExConstants.CHINA_PHONE_NUMBER_ERR_MSG)
    private String tel;
	
	/**性别*/
	@NotBlank(message = "性别不能为空")
	@Pattern(regexp = RegExConstants.SEX, message = RegExConstants.SEX_ERR_MSG)
    private String sex;

	/**年龄*/
	@NotNull(message = "年龄不能为空")
	@Range(min = 18, max = 100, message = "年龄取值范围[18-100]")
    private Integer age;
	
	/**身份证*/
	@NotBlank(message = "身份证不能为空")
    private String idCard;

	/**类型*/
	@NotBlank(message = "类型不能为空")
	@Pattern(regexp = "^(driver)|(supercargo)$", message = "类型错误")
    private String personType;
	
	/**驾驶证*/
	@NotBlank(message = "驾驶证不能为空")
    private String drivingLicence;
	
    
	// 以下属于账号信息
	
	/**账号*/
	@Pattern(regexp = RegExConstants.USER_ACCOUNT, message = RegExConstants.USER_ACCOUNT_ERR_MSG)
	private String userAccount;
	
	/**账号手机号*/
	@Pattern(regexp = RegExConstants.CHINA_PHONE_NUMBER, message = RegExConstants.CHINA_PHONE_NUMBER_ERR_MSG)
	private String userTel;
	
	/**密码*/
	private String password;
	

}
