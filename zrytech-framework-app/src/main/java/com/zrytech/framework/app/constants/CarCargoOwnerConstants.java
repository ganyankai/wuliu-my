package com.zrytech.framework.app.constants;


/**
 * 车主货主常量
 * @author cat
 *
 */
public class CarCargoOwnerConstants {
	
	/**车主货主状态*/
	public static final String STATUS = "car_cargo_owner_status";
	/**已认证*/
	public static final String STATUS_CERTIFIED = "certified";
	/**未认证*/
	public static final String STATUS_UNCERTIFIED = "uncertified";
	/**认证中*/
	public static final String STATUS_INCERTIFIED = "incertified";
	
	public static final String REG_EX_STATUS = "^(" + STATUS_CERTIFIED + ")|(" + STATUS_UNCERTIFIED + ")|(" + STATUS_INCERTIFIED +")|()$";
	public static final String REG_EX_STATUS_ERR_MSG = "状态有误";
	
	
	/**车主货主类型*/
	public static final String TYPE = "customer_type";
	/**车主*/
	public static final String TYPE_CAR_OWNER = "car_owner";
	/**货主*/
	public static final String TYPE_CARGO_OWNER = "cargo_owner";
	
	public static final String REG_EX_TYPE = "^(" + TYPE_CAR_OWNER + ")|(" + TYPE_CARGO_OWNER + ")|()$";
	public static final String REG_EX_TYPE_ERR_MSG = "客户类型有误";
	
	
	/**车主货主账号类型*/
	public static final String CUSTOMER_TYPE = "owner_type";
	/**个人*/
	public static final String CUSTOMER_TYPE_PERSON = "person";
	/**企业*/
	public static final String CUSTOMER_TYPE_ORGANIZE = "organize";
	
	public static final String REG_EX_CUSTOMER_TYPE = "^(" + CUSTOMER_TYPE_PERSON + ")|(" + CUSTOMER_TYPE_ORGANIZE + ")|()$";
	public static final String REG_EX_CUSTOMER_TYPE_ERR_MSG = "身份类型有误";
	
	
}
