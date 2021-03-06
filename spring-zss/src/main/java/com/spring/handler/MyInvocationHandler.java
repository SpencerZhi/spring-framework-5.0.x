package com.spring.handler;


import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


public class MyInvocationHandler implements InvocationHandler {

	Object target;

	public MyInvocationHandler(Object target){
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("代理了噢:" + proxy.getClass());
		return method.invoke(target, args);
	}
}
