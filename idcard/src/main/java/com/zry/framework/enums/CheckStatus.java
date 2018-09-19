package com.zry.framework.enums;/**
 * Created by zry on 2018/9/18.
 */

/**
 * @program: parent
 * @description:
 * @author: lw
 * @create: 2018-09-18 14:39
 **/
public enum  CheckStatus {
    through(0,"通过"),
    failure(1,"不通过"),
    nocheck (3,"未校验"),
    error(4,"请求错误");

    int value;
    String name;

    CheckStatus(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static CheckStatus valueOf(int value) {    //    手写的从int到enum的转换函数
        switch (value) {
            case 0:
                return through;
            case 1:
                return failure;
            case 2:
                return nocheck;
            default:
                return null;
        }
    }

    public int getValue() {
        return value;
    }
    public short getShortValue() {
        return (short) value;
    }
    public String getStringValue() {
        return String.valueOf(value);
    }

    public String getName() {
        return name;
    }
}
