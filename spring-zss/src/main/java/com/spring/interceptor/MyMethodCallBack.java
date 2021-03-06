package com.spring.interceptor;


import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyMethodCallBack implements MethodInterceptor {


	/**
	 * aop的cglib 也是用这种方式
	 * @param o
	 * @param method
	 * @param objects
	 * @param methodProxy
	 * @return
	 * @throws Throwable
	 */
	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		System.out.println("MyMethodCallBack");
		return methodProxy.invokeSuper(o, objects);
	}
}
