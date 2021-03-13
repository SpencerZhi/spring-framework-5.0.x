package com.luban.test;

import com.luban.app.Appconfig;
import com.luban.dao.IndexDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

	public static void main(String[] args) {

		//spring前提环境准备
		//1.准备工厂 = DefaultListableBeanFactory, 实例化dbReader和scanner
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		//把一个class转为bd，最后put到map。 map的位置：DefaultListableBeanFactory的属性beanDefinitionMap
		annotationConfigApplicationContext.register(Appconfig.class);

		//初始化spring的环境
		annotationConfigApplicationContext.refresh();

		IndexDao dao = annotationConfigApplicationContext.getBean(IndexDao.class);
		dao.query();


	}

}
