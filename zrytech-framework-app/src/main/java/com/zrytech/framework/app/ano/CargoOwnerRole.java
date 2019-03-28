package com.zrytech.framework.app.ano;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 用在 controller 方法上，表示方法需要【货主】身份才可以访问
 * 
 * @author cat
 *
 */
@Target({ METHOD })
@Retention(RUNTIME)
public @interface CargoOwnerRole {

}
