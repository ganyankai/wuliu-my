package com.zry.framework.service;/**
 * Created by zry on 2018/9/18.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.api.gateway.demo.util.HttpUtils;
import com.zry.framework.domain.Idcard;
import com.zry.framework.dto.ResponseDto;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: parent
 * @description:
 * @author: lw
 * @create: 2018-09-18 11:35
 **/
public class IdcardCheck {
    public static ResponseDto checkByThirdApi(Idcard idcard){
        String host = "https://simpleinfo.market.alicloudapi.com";
        String path = "/simple";
        String method = "POST";
        String appcode = "33a8bb0ccff449bbad389930971c2f88";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 833xxxxxxxxxxxxxxxxx
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("idCardNum", idcard.getIdcard());
        bodys.put("realName", idcard.getName());


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
//            System.out.println(response.toString());
            //获取response的body
            String json= EntityUtils.toString(response.getEntity());
            System.out.println(json);
//            String json="{\"Code\":1,\"CodeDetail\":\"0103013\",\"Msg\":\"身份证号格式不正确\",\"Data\":null}";
            JSONObject jsonObject= JSON.parseObject(json);
            ResponseDto dto=JSON.toJavaObject(jsonObject,ResponseDto.class);
            dto.setResultMsg(json);
            return  dto;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDto();
        }
    }
}
