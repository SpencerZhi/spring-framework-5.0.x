### Spring 代码流程分析：
```
1.构造方法中初始化工厂  DefaultListableBeanFactory

2.实例化AnnotatedBeanDefinitionReader
	1.往工厂中添加AnnotationAwareOrderComparator类的对象，主要去排序
	2.往工厂中添加ContextAnnotationAutowireCandidateResolver
		(往BeanDefinitionMap注册6个bd。5个是beanPostProcessor,1个是beanFactoryProcessor(ConfigurationClassPostProcessor))
		（map中一共有7个，还有一个是自己register进去的配置类）
		往BeanDefinitionMap注册ConfigurationClassPostProcessor
		往BeanDefinitionMap注册RequiredAnnotationBeanPostProcessor
	3.往工厂添加BeanDefinitionMap
	4.往工厂添加BeanDefinitionNames
	
3.实例化ClassPathBeanDefinitionScanner




准备好bean工厂，实例化对象

1.prepareRefresh准备工作，包括设置启动时间，是否激活标识位，初始化属性源(property source)配置。

2.得到工厂 obtainFreshBeanFactory()

3.prepareBeanFactory  准备工厂，对工厂进行设置
	1.beanFactory.setBeanExpressionResolver 添加一个类加载器
	2.beanFactory.setBeanExpressionResolver 添加bean表达式解释器，为了能够让我们的beanFactory去解析bean表达式
	3.beanFactory.addBeanPostProcessor 添加ApplicationContextAwareProcessor() 后置处理器,能够插手bean实例化过程 。
		springFactory 现在只是仅仅维护了一个list存放后置处理器。后面在bean的实例化过程中会循环处理这个list.
	4.beanFactory.ignoreDependencyInterface 添加了自动注入要忽略的列表,替代依赖。
	5.beanFactory.addBeanPostProcessor 添加ApplicationListenerDetector后置处理器
4.postProcessBeanFactory  空壳方法

5.invokeBeanFactoryPostProcessors. 用于执行自己定义的BeanFactoryPostProcessors和spring内部定义的BeanFactoryPostProcessors。最重要的是去执行ConfigurationClassPostProcessor
	1.得到自己定义的BeanFactoryPostProcessors（就是程序员自己写的，并且没有加上@Component交给spring管理。而是自己手动调用方法） getBeanFactoryPostProcessors();
	2.方法调用，执行spring内部自己维护的BeanDefinitionRegistryPostProcessor(ConfigurationClassPostProcessor， 就是一开始往map中注入的)。 invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry); 
	
	3.循环处理处理BeanDefinitionRegistryPostProcessor
		1.循环所有的BeanDefinitionRegistryPostProcessor
		2.调用方法postProcessor.postProcessBeanDefinitionRegistry（最重要，其实就是ConfigurationClassPostProcessor的方法）
			1.调用processConfigBeanDefinitions  拿出所有的7个bd，判断bd是否包含了@Configuration、@Import，@Compent。。。注解
				1.checkConfigurationClassCandidate 	
					1.得到bd当中描述的类的元数据（类的信息）。 
					2.metadata.isAnnotated(Configuration.class.getName())判断是不是加了@Configuration。如果加了@Configuration，添加到一个set当中,把这个set传给下面的方法去解析
				2.ConfigurationClassParser#parse 扫描包，开始解析。只会解析一个，就是加了@Configuration的配置类
					1.processConfigurationClass
						1.doProcessConfigurationClass  处理内部类(一般不会写内部类),注册bean,处理import
							1.this.componentScanParser.parse 解析扫描所有普通类（com.zss）并且放到了map当中进行注册。 所以一开始初始化的那个scan并没啥软用
								1.scanner.doScan
									1.findCandidateComponents 扫描所有类
									2.registerBeanDefinition 往map中注册
										1.scanCandidateComponents 
							2.processImports 处理@import 3种情况.(ImportSelector,普通类,ImportBeanDefinitionRegistrar)
```





