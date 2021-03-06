package com.spring.aop;

import com.zss.dao.IndexDao;
import com.zss.service.IndexService;
import com.spring.handler.MyInvocationHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.stereotype.Component;


public class AopDemo implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("AopDemo => postProcessBeforeInitialization :"+beanName);
		if (beanName.equals("indexService")){
			bean = Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class<?>[]{IndexService.class}, new MyInvocationHandler(bean));
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return null;
	}
}
