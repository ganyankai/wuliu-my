package com.zry.framework.dto.carperson;

import lombok.Getter;
import lombok.Setter;

/**
 * 司机压货人分页的搜索条件（前端车主搜索条件）
 */
@Setter
@Getter
public class CarOwnerCarPersonPageDto {
	
	/**主键*/
    private Integer id;
	
	/**姓名*/
    private String name;

	/**手机号*/
    private String tel;
	
	/**类型*/
    private String personType;
	
	/**状态*/
    private String status;
	
	/**删除标识*/
    private Boolean isDelete;
	
	/**车主Id*/
    private Integer createBy;
    
    /**车主企业名称*/
    private String carOwnerName;
    
}
