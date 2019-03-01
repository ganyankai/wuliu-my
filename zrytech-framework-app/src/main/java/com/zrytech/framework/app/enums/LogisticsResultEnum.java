package com.zrytech.framework.app.enums;

public enum LogisticsResultEnum {

    FAIL(500, "系统繁忙"),
    ERROR(501, "参数错误"),
    TRADEUPDATE(600,"提交成功，请耐心等待平台审核!"),
    CUSTOMER_UPDATEPERSON(131,"提交成功，请耐心等待平台审核!"),
    PRODUCT_ERROR(502, "产品名称不能相同!"),
    PHONE_EXISTED(148,"手机号码已存在"),
    LOGIN_COUNTER_EXISTED(149,"用户名已存在"),
    GOODS_SOURCE_UP(150,"货源上架中,不能删除"),
    NAME_IS_EXIST(591, "名称已存在"),
    DELETE_WAYBILL_FAIL(110, "订单运输中,删除订单失败!"),
    PERMISSED_NOT_FAIL(111, "权限不足!"),
    NUMBER_GREATER_THAN_ZERO(592, "数量必须大于零"),
    CATEGORY_EXIST_NAME(593, "分类已绑定产品名称"),
    AT_LEAST_ONE(594, "贴水至少设置一个"),
    BIND_BANK(601, "请绑定银行卡"),
    BIND_SEAL(602, "请添加企业印章"),
    GREATER_THAN_ZERO(595, "价格必须大于零"),
    PRODUCT_NOT_EXIST(596, "商品不存在"),
    AGIO_ONLY_TWELVE(597, "贴水只能设置最近12个月"),
    NAME_IS_EMPTY(598, "名称为空"),
    RELATION_CATEGORY_PRODUCT(599, "已关联商品"),
    PRODUCT_STOCK_ERROR(589,"库存错误"),
    PRODUCT_PERMISSIONS_ERROR(590,"你没有添加该类别的商品权限!");

    int code;
    String msg;

    LogisticsResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
