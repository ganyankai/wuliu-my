package com.zrytech.framework.dto.carperson;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

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
    private String tel;
	
	/**性别*/
	@NotEmpty(message = "性别不能为空")
    private String sex;

	/**年龄*/
	@NotNull(message = "年龄不能为空")
    private Integer age;
	
	/**身份证*/
	@NotEmpty(message = "身份证不能为空")
    private String idCard;

	/**类型*/
	@NotEmpty(message = "类型不能为空")
    private String personType;
	
	/**驾驶证*/
	@NotEmpty(message = "驾驶证不能为空")
    private String drivingLicence;
	
    
	// 以下属于账号信息
	
	/**账号*/
	private String userAccount;
	
	/**账号手机号*/
	private String userTel;
	
	/**密码*/
	private String password;
	

}
