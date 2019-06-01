package com.zrytech.framework.app.enums;

/**
 * 0~100
 */
public enum WlResultEnum {

    TO_JSON_STRING_ERROR(002,"对象转json失败"),
    JSON_TO_OBJECT_ERROR(003,"json转对象失败"),
    COPY_OBJECT_ERROR(004,"对象复制失败"),
    SUFFIXES_ERROR(006,"文件后缀缺失"),
    OBTAIN_TOKEN_ERROR(007,"获取Token失败"),
    PAGE_QUERY_ERROR(010,"分页查询失败"),
    OBTAIN_SQLSESSION_ERROR(011,"操作数据库失败"),
    OBTAIN_USER_ERROR(013,"获取用户异常"),
    TOKEN_NOT_FOUND(14,"后台正在审核,请稍后再登!"),
    LNGLAT_IS_NULL(15,"经纬度为空"),
    FILE_OPER_ERROR(16,"文件操作异常"),
    TOKEN_INVALID(17,"Token不能为空"),
    FILE_UPLOAD_ERROR(18,"文件上传失败"),
    FTP_CONNECT_ERROR(19,"连接文件服务器失败"),
    NOT_AUTHORITY(20,"没有权限"),
    NOT_ACCOUNT_AUTHORITY(22,"该账号尚未分配权限，请联系管理员！"),
    SYSYTEM_ERROR(22,"系统繁忙,请稍后访问!"),
    TOKEN_ORERTIME(21,"");

    int code;
    String msg;

    WlResultEnum(int code, String msg) {
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
