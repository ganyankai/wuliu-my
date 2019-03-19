package com.zrytech.framework.app.constants;

/**
 * 审批常量
 * @author cat
 *
 */
public class ApproveConstants {
	
	/**审批状态*/
	public static final String STATUS = "approve_status";

	/**待审批*/
	public static final String STATUS_APPROVAL_PENDING = "approval_pending";
	
	/**已取消*/
	public static final String STATUS_CANCEL = "cancel";
	
	/**审批通过*/
	public static final String STATUS_BE_APPROVED = "be_approved";
	
	/**审批未通过*/
	public static final String STATUS_NOT_APPROVED = "not_approved";
	
	
	
	/**审批结果*/
	public static final String RESULT = "approve_result";
	
	/**审批结果*/
	public static final String RESULT_REFUSE = "refuse";
	
	public static final String RESULT_AGREE = "agree";
	
}
