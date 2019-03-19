package com.zrytech.framework.app.dto.carperson;

import lombok.Getter;
import lombok.Setter;


/**
 * 管理员 - 压货人分页入参
 * @author cat
 *
 */
@Setter
@Getter
public class AdminSupercargoPageDto {
	
	/**姓名*/
    private String name;

	/**手机号*/
    private String tel;
	
	/**状态*/
    private String status;
	
	/**删除标识*/
    private Boolean isDelete;
	
	/**创建人Id*/
    private Integer createBy;
	
    /**车主Id*/
    private Integer carOwnerId;
    
    /**审批状态*/
    private String approveStatus;
}
