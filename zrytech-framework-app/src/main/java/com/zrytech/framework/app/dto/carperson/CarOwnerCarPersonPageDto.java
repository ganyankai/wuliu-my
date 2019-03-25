package com.zrytech.framework.app.dto.carperson;

import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

/**
 * 司机压货人分页的搜索条件（前端车主搜索）
 */
@Setter
@Getter
public class CarOwnerCarPersonPageDto {
	
	/**类型*/
	@Pattern(regexp = "^(driver)|(supercargo)|()$", message = "类型错误")
    private String personType;
	
	/**状态*/
	@Pattern(regexp = "^(uncertified)|(free)|(busy)|()$", message = "状态错误")
    private String status;
    
}
