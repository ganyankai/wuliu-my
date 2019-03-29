package com.zrytech.framework.app.constants;

/**
 * 司机、压货人常量
 * 
 * @author cat
 *
 */
public class CarPersonConstants {

	/**
	 * 司机压货人类型
	 */
	public static final String PERSON_TYPE = "car_person_type";
	/** 司机 */
	public static final String PERSON_TYPE_DRIVER = "driver";
	/** 压货人 */
	public static final String PERSON_TYPE_SUPERCARGO = "supercargo";
	/** 司机压货人类型：正则 */
	public static final String REG_EX_PERSON_TYPE = "^(" + PERSON_TYPE_DRIVER + ")|(" + PERSON_TYPE_SUPERCARGO
			+ ")|()$";
	public static final String REG_EX_PERSON_TYPE_ERR_MSG = "类型有误";

	/**
	 * 司机压货人状态
	 */
	public static final String PERSON_STATUS = "car_person_status";
	/** 空闲 */
	public static final String PERSON_STATUS_FREE = "free";
	/** 繁忙 */
	public static final String PERSON_STATUS_BUSY = "busy";
	/** 未认证 */
	public static final String PERSON_STATUS_UNCERTIFIED = "uncertified";
	/** 司机压货人状态：正则 */
	public static final String REG_EX_PERSON_STATUS = "^(" + PERSON_STATUS_FREE + ")|(" + PERSON_STATUS_BUSY + ")|("
			+ PERSON_STATUS_UNCERTIFIED + ")|()$";
	public static final String REG_EX_PERSON_STATUS_ERR_MSG = "状态有误";

}
