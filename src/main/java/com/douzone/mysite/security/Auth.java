package com.douzone.mysite.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE}) // 어느것만 가능?
@Retention(RetentionPolicy.RUNTIME) // 어느 시점 사용?
public @interface Auth {
	public enum Role {ADMIN,USER}
	Role value() default Role.USER;
		
	/* test*/
	//String value() default "";
	//int method() default 1;
}
