### Spring 五个扩展点：
```
1、BeanPostProcessor			    通过CommonAnnotationBeanPostProcessor类处理
    插手Bean初始化过程、实例化前后(new出来之后，产生了bd)，在bean放到bean容器管理之前处理
    经典场景：@PostConstruct、AOP

2、BeanFactoryPostProcessor			
    springBean被容器中任意一个bean被实例化之前来回调它的方法，主要针对beanFactory来建设
    经典场景：ConfigurationClassPostProcessor #postProcessBeanFactory 针对配置类加上cglib代理。 
             在bean被实例化之前，改变注入模型3，通过构造， 可实现不用@autowired ,实现构造方法 自动装配（mybatis mapperFactoryBean）

3、BeanDefinitionRegistryPostProcessor		
    BeanFactoryPostProcessor的子类。在BeanFactoryPostProcessor之前执行，why？
    spring底层源码决定的，是先遍历BeanDefinitionRegistryPostProcessor(有 自定义的（手动添加.addBeanFactoryPostProcessor）和 系统内部的。 自定义先执行)
	经典场景：spring内部提供一个ConfigurationClassPostProcessor类 实现了BeanDefinitionRegistryPostProcessor这个接口
	    其中有个postProcessBeanDefinitionRegistry回调方法，完成了spring核心的功能：扫描里面的类、解析XML、解析3个import、解析配置类并判断是否为完整的配置类

4、ImportSelector				
    通过selectImports()方法，返回一个类名(全名)，由spring自己把它变成bd，从而动态添加bd(这个bd是死的)
	DeferredImportSelector	延迟加载
	经典场景：动态扫描

5、ImportBeanDefinitionRegistrar		
    registerBeanDefinitions()方法可以得到 BeanDefinitionRegistry注册器，所以可以动态改变添加bd。selector能做的事情，registrar都能做，反之则不可
    经典场景：Mybatis的mapperScan
```  