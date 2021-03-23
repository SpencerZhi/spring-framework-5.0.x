package com.spring.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * @ClassName : MyBeanFactoryProcessor
 * @Description : BeanFactoryPostProcessor 对bean 扩展的五点之一
 * 		这里修改bean的作用域，若把@Component注释掉，就不会扩展了。
 *
 */
//@Component
public class MyBeanFactoryProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		BeanDefinition definition = beanFactory.getBeanDefinition("indexService");
		definition.setScope("prototype");

		//在bean被实例化之前，改变注入模型 可实现不用@autowired ,实现set自动装配.
		//mybatis 是通过3. 构造方法注入。 同时使用ImportBeanDefinitionRegistrar技术取消使用@component
/*		GenericBeanDefinition genericBeanDefinition = (GenericBeanDefinition) beanFactory.getBeanDefinition("indexService");
		genericBeanDefinition.setAutowireMode(1);*/
	}
}
