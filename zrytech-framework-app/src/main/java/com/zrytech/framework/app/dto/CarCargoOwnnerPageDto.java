package com.zrytech.framework.app.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CarCargoOwnnerPageDto {

	private Integer id;
	
	/**状态*/
	private String status;
	
	/**法人姓名*/
	private String legalerName;
	
	/**用户类型*/
	private String customerType;
	
	/**企业名称*/
	private String name;
	
	/**联系电话*/
	private String tel;
	
	/**免审核*/
	private Boolean avoidAudit;
	
	/**类型*/
	private String type;
	
	/**审批状态*/
	private String approveStatus;
	
	
	/**账号手机号*/
	private String customerTel;
	
	/**账号用户名*/
	private String userAccount;
	
	
}
