package com.zry.framework.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 常规格式检测
 */
public class PatternUtil {

    public static boolean checkEmail(String email) {
        String check = "^[A-Za-z0-9]+([-_.][A-Za-z0-9]+)*@([A-Za-z0-9]+[-.])+[A-Za-z0-9]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(email);
        return matcher.matches();
    }

    public static boolean checkPhone(String phone) {
        String check = "^(1)\\d{10}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(phone);
        return matcher.matches();
    }

    public static boolean checkIdCard(String idCard) {
        String check = "(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}$)";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(idCard);
        return matcher.matches();
    }
}