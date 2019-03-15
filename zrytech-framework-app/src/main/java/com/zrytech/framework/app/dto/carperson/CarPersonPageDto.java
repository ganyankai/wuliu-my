package com.zrytech.framework.app.dto.carperson;

import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

/**
 * 管理员 - 司机压货人分页的搜索条件
 */
@Setter
@Getter
public class CarPersonPageDto {
	
	/**主键*/
    private Integer id;
	
	/**司机压货人的姓名*/
    private String name;

	/**手机号*/
    private String tel;
	
	/**类型*/
    @Pattern(regexp = "^()|(driver)|(supercargo)$", message = "类型错误")
    private String personType;
	
	/**状态*/
    private String status;
	
	/**删除标识*/
    private Boolean isDelete;
	
	/**创建人Id*/
    private Integer createBy;
    
    /**车主企业名称*/
    private String carOwnerName;
    
}
