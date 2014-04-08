package com.sunyzc.ssh.service;

import java.lang.reflect.Method;

public class Test {
	@TestAnnotation
	private int a;

	@TestAnnotation(hello = "你好")
	public void a(String hello) {
	}

	public void test(Object obj) {
		Method[] methods = obj.getClass().getMethods();
		for (Method method : methods) {
			if (method.isAnnotationPresent(TestAnnotation.class)) {
				TestAnnotation ta = method.getAnnotation(TestAnnotation.class);
				System.out.println(ta.hello());
			}
		}
		System.out.println(1);
	}

	public static void main(String[] args) {
		Test test = new Test();
		test.test(test);
	}
}
