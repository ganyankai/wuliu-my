package com.zrytech.framework.aop;

import java.io.IOException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;


/**
 * <pre>
 * 在 controller 方法之前，判断参数验证结果 BindingResult 中是否存在错误信息，
 * 如果存在错误信息将抛出异常{@link ParameterException}，controller 不会运行。
 * </pre>
 * @author cat
 *
 */
@Aspect
@Component
public class AopParameterValidationHandler {
	
	@Pointcut("@annotation(javax.validation.Valid)")
	
    public void pointCut() {}
	
	@Before("pointCut()")
    public void beforeLog(JoinPoint joinPoint) throws IOException {
		Object[] args = joinPoint.getArgs();
		for (Object object : args) {
			if(object instanceof BindingResult ) {
				BindingResult bindingResult = (BindingResult) object;
				if(bindingResult.getErrorCount() > 0) {
					ObjectError objectError = bindingResult.getAllErrors().get(0);
					throw new RuntimeException(objectError.getDefaultMessage());
				}
				break;
			}
		}
    }
	
}
