package com.zrytech.framework.app.constants;

/**
 * 司机与压货人常量
 * @author cat
 *
 */
public class CarPersonConstants {
	
	
	public static final String PERSON_TYPE = "car_person_type";

	/**
	 * 司机
	 */
	public static final String PERSON_TYPE_DRIVER = "driver";
	
	/**
	 * 压货人
	 */
	public static final String PERSON_TYPE_SUPERCARGO = "supercargo";
	
	
	
	
	/**
	 * 待审核
	 */
	public static final String PERSON_STATUS_WAIT_CHECK = "wait_check";
	
	/**
	 * 上架
	 */
	public static final String PERSON_STATUS_UP = "up";
	
	/**
	 * 下架
	 */
	public static final String PERSON_STATUS_DOWN = "down";
	
	
	public static final String PERSON_STATUS = "car_person_status";
	
	/**空闲*/
	public static final String PERSON_STATUS_FREE = "free";
	
	/**繁忙*/
	public static final String PERSON_STATUS_BUSY = "busy";
	
	/**初始状态*/
	public static final String PERSON_STATUS_ORIGINAL = "original";
	
	/**未认证*/
	public static final String PERSON_STATUS_UNCERTIFIED = "uncertified";
	
	
	/**待审批*/
	public static final String APPROVE_STATUS_APPROVAL_PENDING = "approval_pending";
	
	/**已取消*/
	public static final String APPROVE_STATUS_CANCEL = "cancel";
	
	/**审批通过*/
	public static final String APPROVE_STATUS_BE_APPROVED = "be_approved";
	
	/**审批未通过*/
	public static final String APPROVE_STATUS_NOT_APPROVED = "not_approved";
	
	
	
	
}
