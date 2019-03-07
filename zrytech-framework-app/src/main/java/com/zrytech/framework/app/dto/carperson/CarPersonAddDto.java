package com.zrytech.framework.app.dto.carperson;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import lombok.Getter;
import lombok.Setter;


/**
 * 添加司机，压货人入参
 * @author cat
 *
 */
@Setter
@Getter
public class CarPersonAddDto {
	
	/**姓名*/
	@NotEmpty(message = "姓名不能为空")
    private String name;
	
	/**手机号*/
	@NotEmpty(message = "手机号不能为空")
	@Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$", message = "手机号码格式错误")
    private String tel;
	
	/**性别*/
	@NotEmpty(message = "性别不能为空")
	@Pattern(regexp = "^(男)|(女)$", message = "性别错误")
    private String sex;

	/**年龄*/
	@NotNull(message = "年龄不能为空")
	@Range(min = 18, max = 100, message = "年龄取值范围[18-100]")
    private Integer age;
	
	/**身份证*/
	@NotEmpty(message = "身份证不能为空")
    private String idCard;

	/**类型*/
	@NotEmpty(message = "类型不能为空")
	@Pattern(regexp = "^(driver)|(supercargo)$", message = "类型错误")
    private String personType;
	
	/**驾驶证*/
	@NotEmpty(message = "驾驶证不能为空")
    private String drivingLicence;
	
    
	// 以下属于账号信息
	
	/**账号*/
	@Length(min = 6, max = 20, message = "账号长度[6~20]")
	private String userAccount;
	
	/**账号手机号*/
	@Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$", message = "手机号码格式错误")
	private String userTel;
	
	/**密码*/
	private String password;
	

}
