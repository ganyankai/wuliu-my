package com.zry.framework.constants;

/**
 * @description: 货主相关常量
 * @author: jxx
 * @create: 2019/02/14 10:02
 */
public class CargoConstant {

    /**
     * cargo_status:资料认证状态;
     */
    public static final String CARGO_STATUS = "cargo_status";

    /**
     * audit_process:认证中
     */
    public static final String AUDIT_PROCESS = "audit_process";

    /**
     * audit_refuse:认证被拒绝
     */
    public static final String AUDIT_REFUSE = "audit_refuse";

    /**
     * audit_pass:认证通过
     */
    public static final String AUDIT_PASS = "audit_pass";


    /**
     * cargo_certification_type:客户认证资料类型;
     */
    public static final String CARGO_CUSTOMER_TYPE = "cargo_certification_type";


    /**
     * certification_organize:企业认证
     */
    public static final String CERTIFICATION_ORGANIZE = "certification_organize";

    /**
     * certification_person:个人认证
     */
    public static final String CERTIFICATION_PERSON = "certification_person";


    /**
     * customer_type:客户类型;
     */
    public static final String CUSTOMER_TYPE = "customer_type";


    /**
     * customer_cargo:货主
     */
    public static final String CUSTOMER_CARGO = "customer_cargo";

    /**
     * customer_car_owner:车主
     */
    public static final String CUSTOMER_CAR_OWNER = "customer_car_owner";

    /**
     * cargo_goods_status:货源状态;
     */
    public static final String CARGO_GOODS_STATUS = "cargo_goods_status";

    /**
     * wait_audit:待审核;
     */
    public static final String WAIT_AUDIT = "wait_audit";

    /**
     * source_up:已上架;
     */
    public static final String SOURCE_UP = "source_up";

    /**
     * source_down:拒绝;
     */
    public static final String SOURCE_DOWN = "source_down";

    /**
     * source_expired:已过期;
     */
    public static final String SOURCE_EXPIRED = "source_expired";

    /**
     * source_down:报价中;
     */
    public static final String SOURCE_ONGOING = "source_ongoing";

    /**
     * source_down:已中标;(已中标)
     */
    public static final String SOURCE_COMPLETE = "source_complete";

    /**
     * Loading_unloading_type:装卸类型;
     */
    public static final String loading_unloading_type = "Loading_unloading_type";


    /**
     * loading_type:装货;
     */
    public static final String LOADING_TYPE = "loading_type";

    /**
     * unloading_type:卸货;
     */
    public static final String UNLOADING_TYPE = "unloading_type";


    /**
     * push_mark_way:发标方式;
     */
    public static final String PUSH_MARK_WAY = "push_mark_way";


    /**
     * tender_mark:招标;
     */
    public static final String TENDER_MARK = "tender_mark";

    /**
     * tender_mark:抢标;
     */
    public static final String BID_MARK = "bid_mark";

    /**
     * waybill_status:运单状态
     *
     * */
    public static final String WAYBILL_STATUS="waybill_status";

    /**
     * await_generate:待生成运单
     *
     * */
    public static final String AWAIT_GENERATE="await_generate";

    /**
     * await_determine:待确定运单
     *
     * */
    public static final String AWAIT_DETERMINE="await_determine";

    /**
     * await_loading:待装货运单
     *
     * */
    public static final String AWAIT_LOADING="await_loading";

    /**
     * await_accept:待收货运单
     *
     * */
    public static final String AWAIT_ACCEPT="await_accept";
    /**
     * sign_paied:已签收待支付运单
     *
     * */
    public static final String SIGN_PAIED="sign_paied";

    /**
     * is_evaluation:已支付待评价运单
     *
     * */
    public static final String IS_EVALUATION="is_evaluation";

    /**
     * completed:已完成运单
     *
     * */
    public static final String COMPLETED="completed";


    /**
     * message_type：消息类型
     */
    public static final String MESSAGE_TYPE="message_type";

    /**消息类型 - 系统消息*/
    public static final String SYSTEM_MESSAGE = "system_message";
    /**消息类型 - 竞价消息*/
    public static final String BIDDING_MESSAGE = "bidding_message";
    /**消息类型 - 运单消息*/
    public static final String WAYBILL_MESSAGE = "waybill_message";
}

