package com.zry.framework.dto.carperson;

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
    private String name;
	
	/**手机号*/
    private String tel;
	
	/**性别*/
    private String sex;

	/**年龄*/
    private Integer age;
	
	/**身份证*/
    private String idCard;

	/**类型*/
    private String personType;
	
	/**驾驶证*/
    private String drivingLicence;
	
    
	// 以下属于账号信息
	
	/**账号*/
	private String userAccount;
	
	/**手机号*/
	private String userTel;
	
	/**密码*/
	private String password;
	

}
