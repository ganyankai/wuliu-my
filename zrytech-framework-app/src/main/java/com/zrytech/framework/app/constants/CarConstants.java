package com.zrytech.framework.app.constants;

/**
 * 车辆相关常量
 * @author cat
 */
public class CarConstants {
	
	/**
	 * 车辆状态
	 */
	public static final String CAR_STATUS = "car_status";
	/**未认证*/
	public static final String CAR_STATUS_UNCERTIFIED = "uncertified";
	/**空闲*/
	public static final String CAR_STATUS_FREE = "free";
	/**繁忙*/
	public static final String CAR_STATUS_BUSY = "busy";
	
	
	/**车辆类型*/
	public static final String CAR_TYPE = "car_type";
	/**牵引车*/
	public static final String CAR_TYPE_TRACTOR = "car_tractor";
	
	public static final String REG_EX_CAR_TYPE = "^(" + CAR_TYPE_TRACTOR + ")|()$";
	public static final String REG_EX_CAR_TYPE_ERR_MSG = "车辆类型有误";
	
	
	
	/**车辆核载量单位*/
	public static final String CAR_UNIT = "car_unit";
	/**车辆核载量单位:吨*/
	public static final String CAR_UNIT_TON = "ton";
	/**车辆核载量单位:升*/
	public static final String CAR_UNIT_LITRE = "litre";
	
	public static final String REG_EX_CAR_UNIT = "^(" + CAR_UNIT_TON + ")|(" + CAR_UNIT_LITRE + ")|()$";
	public static final String REG_EX_CAR_UNIT_ERR_MSG = "车辆核载量单位有误";
}
