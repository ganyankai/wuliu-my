package com.zrytech.framework.app.constants;

/**
 * 运单装卸地常量
 * 
 * @author cat
 *
 */
public class BillLocationConstants {
	
	/** 类型 */
	public static final String TYPE = "Loading_unloading_type";
	/** 装货 */
	public static final String TYPE_LOADING = "loading_type";
	/** 卸货 */
	public static final String TYPE_UNLOAD = "unloading_type";
	/** 类型正则 */
	public static final String REG_EX_TYPE = "^(" + TYPE_LOADING + ")|(" + TYPE_UNLOAD + ")|()$";
	public static final String REG_EX_TYPE_ERR_MSG = "类型有误";
	
	
	/** 默认状态 */
	public static final String STATUS_DEFAULT = "default";
	

}
