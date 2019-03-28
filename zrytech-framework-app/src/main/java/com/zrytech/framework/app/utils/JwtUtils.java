package com.zrytech.framework.app.utils;

import com.zrytech.framework.base.constant.Constant;
import com.zrytech.framework.base.exception.BusinessException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * Jwt工具类
 * 
 * @author cat
 *
 */
public class JwtUtils {

	public static Claims parseJwt(String token) {
		try {
			return Jwts.parser().setSigningKey(Constant.PUBLIC_KEY).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			throw new BusinessException(112, "token 验证失败");
		}
	}

}
