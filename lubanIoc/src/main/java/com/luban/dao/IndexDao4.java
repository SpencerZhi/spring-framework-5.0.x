package com.luban.dao;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;

@Component
public class IndexDao4 implements ApplicationContextAware {


	private ApplicationContext applicationContext;

	public IndexDao4() {
		System.out.println("构造");
	}

	@PostConstruct
	public void init(){
		System.out.println("init");
	}


	public void query(){
		System.out.println("query");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;

	}
}
