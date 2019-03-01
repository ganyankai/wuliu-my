package com.zrytech.framework.app.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;



/**
 * <pre>
 * 配置 {@link Validator}
 * validatorFailFast - true: 出现一个验证错误就结束验证。
 * validatorFailFast - false: 无论是否通过验证都验证全部项，记录所有错误项。
 * </pre>
 * 
 * @author cat
 *
 */
@Configuration
public class ValidationConfig {
	
	// -true 快速失败返回模式  
	// -false 普通模式
	private String validatorFailFast = "true";
	
	@Bean
	public Validator getValidator() {
		ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
		        .configure()
		        .addProperty( "hibernate.validator.fail_fast", validatorFailFast)
		        .buildValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		return validator;
	}
}
