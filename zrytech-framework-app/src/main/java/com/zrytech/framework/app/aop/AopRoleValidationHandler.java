package com.zrytech.framework.app.aop;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zrytech.framework.app.utils.JwtUtils;
import com.zrytech.framework.base.cache.ICache;
import com.zrytech.framework.base.exception.BusinessException;
import com.zrytech.framework.base.util.RequestUtil;

import io.jsonwebtoken.Claims;

/**
 * 角色权限拦截
 */
@Aspect
@Component
public class AopRoleValidationHandler {

	@Autowired
	private ICache cache;

	private String getToken() {
		String token = RequestUtil.getToken(RequestUtil.currentRequest());
		if (StringUtils.isBlank(token)) {
			throw new BusinessException(112, "token 不能为空");
		}
		String userInfo = cache.get(token);
		if (StringUtils.isBlank(userInfo)) {
			throw new BusinessException(112, "token 已过期");
		}
		return token;
	}

	@Before("@annotation(com.zrytech.framework.app.ano.AdminRole)")
	public void adminRole(JoinPoint joinPoint) {
		String token = this.getToken();
		Claims claims = JwtUtils.parseJwt(token);
		if (!"user".equalsIgnoreCase(claims.get("roles").toString())) {
			throw new BusinessException(112, "无访问权限");
		}
	}

	@Before("@annotation(com.zrytech.framework.app.ano.CarOwnerRole)")
	public void carOwnerRole(JoinPoint joinPoint) {
		String token = this.getToken();
		Claims claims = JwtUtils.parseJwt(token);
		if (!"carOwner".equalsIgnoreCase(claims.get("roles").toString())) {
			throw new BusinessException(112, "无访问权限");
		}
	}

	@Before("@annotation(com.zrytech.framework.app.ano.CargoOwnerRole)")
	public void cargoOwnerRole(JoinPoint joinPoint) {
		String token = this.getToken();
		Claims claims = JwtUtils.parseJwt(token);
		if (!"cargoOwner".equalsIgnoreCase(claims.get("roles").toString())) {
			throw new BusinessException(112, "无访问权限");
		}
	}

	@Before("@annotation(com.zrytech.framework.app.ano.DriverRole)")
	public void driverRole(JoinPoint joinPoint) {
		String token = this.getToken();
		Claims claims = JwtUtils.parseJwt(token);
		if (!"driver".equalsIgnoreCase(claims.get("roles").toString())) {
			throw new BusinessException(112, "无访问权限");
		}
	}

}
