package com.zrytech.framework.app.dto;

import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CarCargoOwnnerPageDto {

	private Integer id;
	
	/**法人姓名*/
	private String legalerName;
	
	/**企业名称*/
	private String name;
	
	/**联系电话*/
	private String tel;
	
	/**免审核*/
	private Boolean avoidAudit;
	
	/**类型*/
	@Pattern(regexp = "^(car_owner)|(cargo_owner)|()$", message = "类型错误")
	private String type;
	
	/**审批状态*/
	private String approveStatus;
	
	/**状态*/
	//@Pattern(regexp = "^(certified)|(uncertified)|()$", message = "类型错误")
	private String status;
	
	/**用户类型*/
	//@Pattern(regexp = "^(organize)|(person)|()$", message = "类型错误")
	private String customerType;
	
	
	
	/**账号手机号*/
	private String customerTel;
	
	/**账号用户名*/
	private String userAccount;
	
	
}
