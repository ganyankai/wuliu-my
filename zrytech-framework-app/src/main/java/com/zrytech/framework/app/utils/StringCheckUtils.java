package com.zrytech.framework.app.utils;

import com.zrytech.framework.app.constants.RegExConstants;

public class StringCheckUtils {
	
	/**
	 * 密码格式校验<br>
	 * 包含数字、字母、半角标点符号中至少两种，长度(6-20).
	 * 
	 * @param input
	 * @return
	 */
	public static boolean rexCheckPassword(String input) {
		int a = input.matches("^.*[\\d]+.*$") ? 1 : 0; // 包含数字
		int b = input.matches("^.*[A-Za-z]+.*$") ? 1 : 0; // 包含字母
		int c = input.matches("^.*[\\p{Punct}]+.*$") ? 1 : 0; // 包含半角标点符号
		String regStr = "^[A-Za-z0-9\\p{Punct}]{6,20}$"; // 取值范围：可包含数字、字母、半角标点符号，长度(6-20)
		boolean flag = input.matches(regStr);
		if (a + b + c < 2 || !flag) {
			return false;
		} else {
			return true;
		}
	}
	
}
