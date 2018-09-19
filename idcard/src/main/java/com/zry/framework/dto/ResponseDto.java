package com.zry.framework.dto;/**
 * Created by zry on 2018/9/18.
 */

import lombok.Data;

/**
 * @program: parent
 * @description:
 * @author: lw
 * @create: 2018-09-18 14:28
 **/
@Data
public class ResponseDto {
    private String code;
    private String codeDetail;
    private String msg;
    private String data;
    private String resultMsg;
//    {"Code":1,"CodeDetail":"0103013","Msg":"身份证号格式不正确","Data":null}
}
