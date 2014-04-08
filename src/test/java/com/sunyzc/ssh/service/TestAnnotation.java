package com.sunyzc.ssh.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.FIELD })
public @interface TestAnnotation {
	public String hello() default "hello";
}
