package com.zry.framework.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 车辆司机压货人分页的搜索条件
 */
@Setter
@Getter
public class CarPersonPageDto {
	
	/**主键*/
    private Integer id;
	
	/**姓名*/
    private String name;

	/**手机号*/
    private String tel;
	
	/**性别*/
    private String sex;

	/**身份证*/
    private String idCard;

	/**类型*/
    private String personType;

	/**客户Id*/
    private Integer customerId;
	
	/**驾驶证*/
    private String drivingLicence;
	
	/**状态*/
    private String status;
	
	/**删除标识*/
    private Boolean isDelete;
	
	/**车主Id*/
    private Integer createBy;
    
}
