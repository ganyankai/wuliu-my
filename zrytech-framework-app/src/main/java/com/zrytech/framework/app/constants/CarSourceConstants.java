package com.zrytech.framework.app.constants;

/**
 * 车源相关常量
 * 
 * @author cat
 */
public class CarSourceConstants {

	/** 车源状态 */
	public static final String CAR_SOURCE_STATUS = "car_source_status";
	/** 车源状态：未认证 */
	public static final String STATUS_UNCERTIFIED = "uncertified";
	/** 车源状态：发布中 */
	public static final String STATUS_RELEASE = "release";
	/** 车源状态：已下架 */
	public static final String STATUS_DOWN = "down";

	public static final String REG_EX_CAR_SOURCE_STATUS = "^(" + STATUS_UNCERTIFIED + ")|(" + STATUS_RELEASE + ")|(" + STATUS_DOWN + ")|()$";
	public static final String REG_EX_CAR_SOURCE_STATUS_ERR_MSG = "车源状态有误";

}
