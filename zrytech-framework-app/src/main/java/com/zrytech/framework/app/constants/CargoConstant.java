package com.zrytech.framework.app.constants;

/**
 * @description: 货主相关常量
 * @author: jxx
 * @create: 2019/02/14 10:02
 */
public class CargoConstant {
	
	
	/**
	 * 介质
	 */
	public static final String MEDIUM = "medium";
	/** 介质:天然气 */
	public static final String MEDIUM_LNG = "medium_lng";
	
	
	
	
	

    /**
     * cargo_status:资料认证状态;
     * */
    //public static final String CARGO_STATUS = "approve_status";

    /**audit_process:认证中*/
    //public static final String AUDIT_PROCESS = "audit_process";
    /**audit_refuse:认证被拒绝*/
    //public static final String AUDIT_REFUSE = "audit_refuse";
    /**audit_pass:认证通过*/
    //public static final String AUDIT_PASS = "audit_pass";

    /**
     * cargo_certification_type:客户认证资料类型;
     * */
    public static final String CARGO_CUSTOMER_TYPE = "owner_type";

    /**certification_organize:企业认证*/
    public static final String CERTIFICATION_ORGANIZE = "organize";
    /**certification_person:个人认证*/
    public static final String CERTIFICATION_PERSON = "person";


    /**
     * customer_type:客户类型;
     * */
    public static final String CUSTOMER_TYPE = "customer_type";

    /**customer_cargo:货主*/
    public static final String CUSTOMER_CARGO = "cargo_owner";
    /**customer_car_owner:车主;*/
    public static final String CUSTOMER_CAR_OWNER = "car_owner";


    /**
     * cargo_goods_status:货源状态;
     * */
//    public static final String CARGO_GOODS_STATUS = "cargo_source_status";
    /**source_draft:草稿;*/
//    public static final String SOURCE_DRAFT = "source_draft";
    /**wait_audit:审核中;*/
//    public static final String WAIT_AUDIT = "wait_audit";
    /**source_refuse:审核拒绝 */
//    public static final String SOURCE_REFUSE = "source_refuse";
    /**source_up:竞标中(上架);*/
//    public static final String SOURCE_UP = "source_up";
    /**source_expired:已过期;*/
//    public static final String SOURCE_EXPIRED = "source_expired";
    /**source_winning:中标;(中标)*/
//    public static final String SOURCE_WINNING = "source_winning";
    /**source_winning:已取消;*/
//    public static final String SOURCE_CANCEL = "source_cancel";


    
    // cat
    
	/** 货源状态; */
	public static final String CARGO_SOURCE_STATUS = "cargo_source_status";
	/** 货源状态 :下架; */
	public static final String CARGO_SOURCE_STATUS_DOWN = "cargo_source_status_down";
	/** 货源状态 :待审核; */
	public static final String CARGO_SOURCE_STATUS_WAIT_CHECK = "cargo_source_status_wait_check";
	/** 货源状态 :已取消; */
	public static final String CARGO_SOURCE_STATUS_CANCELED = "cargo_source_status_canceled";
	/** 货源状态 :发布中（上架）; */
	public static final String CARGO_SOURCE_STATUS_RELEASE = "cargo_source_status_release";
	/** 货源状态 :已过期; */
	public static final String CARGO_SOURCE_STATUS_EXPIRED = "cargo_source_status_expired";
	/** 货源状态 :已完成; */
	public static final String CARGO_SOURCE_STATUS_COMPLETED = "cargo_source_status_completed";
    
    
    
    
    
    /**
     * Loading_unloading_type:装卸类型;
     * */
    public static final String LOAD_TYPE = "Loading_unloading_type";

    /**loading_type:装货;*/
    public static final String LOADING_TYPE = "loading_type";
    /**unloading_type:卸货;*/
    public static final String UNLOADING_TYPE = "unloading_type";
    
    public static final String REG_EX_LOAD_TYPE = "^(" + LOADING_TYPE + ")|(" + UNLOADING_TYPE + ")|()$";
	public static final String REG_EX_LOAD_TYPE_ERR_MSG = "装卸类型有误";
    


	/**
	 * 发标方式
	 */
	public static final String PUSH_MARK_WAY = "push_mark_way";
	/** 发标方式:招标 */
	public static final String TENDER_MARK = "tender_mark";
	/** 发标方式:抢标 */
	public static final String BID_MARK = "bid_mark";


	/**
	 * 运单状态
	 */
	public static final String WAYBILL_STATUS = "waybill_status";
	/** 运单状态:待生成 */
	public static final String WAYBILL_STATUS_WAIT_GENERATE = "waybill_status_wait_generate";
	/** 运单状态:待确定 */
	public static final String WAYBILL_STATUS_WAIT_DETERMINE = "waybill_status_wait_determine";
	/** 运单状态:已取消 */
	public static final String WAYBILL_STATUS_CANCELLED = "waybill_status_cancelled";
	/** 运单状态:待支付首付款 */
	public static final String WAYBILL_STATUS_WAIT_PAY_ADVANCE_MONEY = "waybill_status_wait_pay_advance_moeny";
	/** 运单状态:运输中 */
	public static final String WAYBILL_STATUS_IN_TRANSIT = "waybill_status_in_transit";
	/** 运单状态:待支付尾款 */
	public static final String WAYBILL_STATUS_WAIT_PAY_FINAL_MONEY = "waybill_status_wait_pay_final_money";
	/** 运单状态:待评价 */
	public static final String WAYBILL_STATUS_WAIT_COMMENT = "waybill_status_wait_comment";
	/** 运单状态:已完成 */
	public static final String WAYBILL_STATUS_COMPLETED = "waybill_status_completed";

    
    
    
    

    /**
     * message_type：消息类型
     * */
    public static final String MESSAGE_TYPE="message_type";

    /**消息类型 - 系统消息*/
    public static final String SYSTEM_MESSAGE = "system_message";
    /**消息类型 - 竞价消息*/
    public static final String BIDDING_MESSAGE = "bidding_message";
    /**消息类型 - 运单消息*/
    public static final String WAYBILL_MESSAGE = "waybill_message";

    /**
     *offer_status:报价状态
     */
    public static final String OFFER_STATUS="offer_status";

    /**offer_process - 应标中(报价中)*/
    public static final String OFFER_PROCESS = "offer_process";
    /**offer_promissed - 中标*/
    public static final String OFFER_PROMISSED = "offer_promissed";
    /**offer_draft - 淘汰(草稿)*/
    public static final String OFFER_DRAFT = "offer_draft";
    /**offer_refuse - 拒绝(拒绝)*/
    public static final String OFFER_REFUSE = "offer_refuse";
    /**offer_canceled - 已取消(取消)*/
    public static final String OFFER_CANCELED = "offer_canceled";

    /**
     *loading_status:装货/卸货状态
     */
    public static final String LOADING_STATUS="loading_status";
    /**noting_loading - 未装货*/
    public static final String NOTING_LOADING = "noting_loading";
    /**haved_loading -已装货*/
    public static final String HAVED_LOADING = "haved_loading";
    /**noting_unloading - 未卸货*/
    public static final String NOTING_UNLOADING = "noting_unloading";
    /**haved_unloading -已卸货*/
    public static final String HAVED_UNLOADING = "haved_unloading";



	/**
	 * 付款方式
	 */
	public static final String PAY_TYPE = "pay_type";
	/** 付款方式:线下支付 */
	public static final String OFFLINE_PAY = "offline_pay";
	/** 付款方式:微信支付 */
	public static final String WECHAT = "wechat";
	/** 付款方式:支付宝支付 */
	public static final String ALIPAY = "alipay";
	/** 付款方式:银行卡支付 */
	public static final String BANKPAY = "bankpay";


    /**
     *weight_unit:重量单位
     */
    //public static final String WEIGHT_UNIT="weight_unit";
    /**ton - 吨*/
    //public static final String TON = "ton";
    /**rise -升*/
   // public static final String RISE = "rise";


	/** 价格单位 */
	public static final String PRICE_UNIT = "price_unit";
	/** 价格单位:人民币 */
	public static final String PRICE_YUAN = "price_yuan";
	/** 价格单位:美元 */
	public static final String PRICE_DOLLAR = "price_dollar";


    /**
     *price_type:价格类型
     */
    public static final String PRICE_TYPE="price_type";
    /**fixed_price -固定价*/
    public static final String FIXED_PRICE = "fixed_price";
    /**perton_price -每吨运费单价*/
    public static final String PERTON_PRICE = "perton_price";

    /**
     *pay_way:付款类型
     */
    public static final String PAY_WAY="pay_way";
    /**arrival_pay -货到付款*/
    public static final String ARRIVAL_PAY = "arrival_pay";
    /**tail_pay -尾款*/
    public static final String TAIL_PAY = "tail_pay";
    /**first_pay -首付*/
    public static final String FIRST_PAY = "first_pay";

    /**
     *flow_status:运单项状态(运单详情:flow_name)
     */
    public static final String FLOW_STATUS="flow_status";
    /**flow_await -待装货*/
    public static final String FLOW_AWAIT = "flow_await";
    /**flow_loaded -装货完成*/
    public static final String FLOW_LOADED = "flow_loaded";
    /**flow_ongoing -运输中*/
    public static final String FLOW_ONGOING = "flow_ongoing";
    /**flow_completed -已完成*/
    public static final String FLOW_COMPLETED = "flow_completed";


    /**
     *car_type:车辆类型
     */
   // public static final String CAR_TYPE="car_type";


    /**
     *focus_type:关注类型
     */
    public static final String FOCUS_TYPE="focus_type";
    /**focus_customer_cargo -关注货主*/
    public static final String FOCUS_CUSTOMER_CARGO = "focus_customer_cargo";
    /**focus_car_owner -关注车主*/
    public static final String FOCUS_CAR_OWNER = "focus_car_owner";


	/**
	 * 车主货主关注类型
	 */
	public static final String MY_FOCUS_PERSON_TYPE = "my_focus_person_type";
	/** 车主货主关注类型：关注车主 */
	public static final String FOCUS_CAR = "focus_car";
	/** 车主货主关注类型：关注货主 */
	public static final String FOCUS_CARGO = "focus_cargo";
    
    
    
	/**
	 * 评价类型
	 */
	public static final String EVALUATE_TYPE = "evaluate_type";
	/** 评价类型:货主评价车主 */
	public static final String EVALUATE_TYPE_CARGO_COMMENTS_CAR = "evaluate_type_cargo_comments_car";
	/** 评价类型:车主评价货主 */
	public static final String EVALUATE_TYPE_CAR_COMMENTS_CARGO = "evaluate_type_car_comments_cargo";
	/** 评价类型:货主评价司机 */
	public static final String EVALUATE_TYPE_CARGO_COMMENTS_DRIVER = "evaluate_type_cargo_comments_driver";
	/** 评价类型:司机评价货主 */
	public static final String EVALUATE_TYPE_DRIVER_COMMENTS_CARGO = "evaluate_type_driver_comments_cargo";
	
	

}

