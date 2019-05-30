package com.zrytech.framework.app.constants;


/**
 * 正则表达式和提示信息常量类<br>
 * 所有正则均包含空字符串，所以需要自行校验字符串是否为字符串
 * @author cat
 *
 */
public class RegExConstants {
	
	/**中国手机号正则*/
	public static final String CHINA_PHONE_NUMBER = "^(1(3|4|5|7|8)\\d{9})|()$";
	
	public static final String CHINA_PHONE_NUMBER_ERR_MSG = "手机号码格式有误";
	
	/**车牌号正则*/
	public static final String CAR_NO = "^([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1})|()$";
	
	public static final String CAR_NO_ERR_MSG = "车牌号格式有误";
	
	/**用户名正则*/
	public static final String USER_ACCOUNT = "^[A-Za-z]{1}[A-Za-z0-9_-]{2,15}|()$";
	public static final String USER_ACCOUNT_ERR_MSG = "用户名格式有误：用户名可包含数字、字母、减号、下划线，需以字母开头，长度（3-16）";
	
	
	/**性别正则*/
	public static final String SEX = "^(男)|(女)|()$";
	public static final String SEX_ERR_MSG = "性别格式有误：可选“男”，“女”。";
	
	public static final String PASSWORD = "^.*(?=.{6,16})(?=.*\\d)(?=.*[A-Za-z])(?=.*[\\p{Punct}]).*|()$";
	public static final String PASSWORDERR_MSG = "密码格式错误：密码必须同时包含字母、数字、特殊符号，长度（6-16）";
	
	
	
	public static void main(String[] args) {
		System.out.println("A123456".matches(RegExConstants.PASSWORD));
	}
}
