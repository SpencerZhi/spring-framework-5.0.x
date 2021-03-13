package com.luban.beanPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

@Component
public class TestBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

		if(beanName.equals("indexDao")){
			System.out.println("BeforeInitialization");
		}
		/**
		 *  这里可以直接一个代理对象出去  -- AOP
		 *  Proxy.newProxyInstance(, , )
		 */

		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

		if(beanName.equals("indexDao")){
			System.out.println("AfterInitialization");
		}
		return bean;
	}
}
