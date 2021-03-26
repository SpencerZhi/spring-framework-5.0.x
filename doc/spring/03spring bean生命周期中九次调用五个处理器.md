
### 以下这些处理器贯穿了bean的生命周期过程： 5个后置处理器， 9次调用
```
第一次  InstantiationAwareBeanPostProcessor---postProcessBeforeInstantiation(){

cityService  -------------------BeanPostProcessor---------------postProcessAfterInitialization(aop){

	}
}

作用：	执行InstantiationAwareBeanPostProcessor 的postProcessBeforeInstantiation
	判断 bean在实例化之前要不要产生一些新的对象，这个方法可以返回任意类型，
	如果返回空，spring就会往后面进行，按spring自己的来；
	如果返回别的类型，spring就不会进行后面逻辑！！！只会进行 父类 BeanPostProcessor的 postProcessAfterInitialization
	经典应用场景：AOP spring 判断一个类，要不要增强，如果不需要增强就会放入一个 map中，后续进行增强操作时就会忽略这个 map的对象。
	            （根据注解选出一定不需要增强的类）

	在bean没有开始实例化之前执行

第二次 determineConstructorsFromBeanPostProcessors--->SmartInstantiationAwareBeanPostProcessor--determineCandidateConstructors()
	推断构造方法，默认选择无参构造方法。
	如果一个构造方法加了@autowired,则选择对应的

第三次 applyMergedBeanDefinitionPostProcessors--->MergedBeanDefinitionPostProcessor---postProcessMergedBeanDefinition
	缓存注解信息

第四次 addSingletonFactory --->SmartInstantiationAwareBeanPostProcessor---getEarlyBeanReference()
	用于解决循环依赖。  得到一个提前暴露的对象。是对象而不是bean（bean是在spring容器当中，并且由spring自己产生的）

第五次 populateBean----->InstantiationAwareBeanPostProcessor--postProcessAfterInstantiation()
	判断你的bean需不需要完成属性填充


第六次 populateBean-------->InstantiationAwareBeanPostProcessor---postProcessPropertyValues()
	自动装配，属性填充---自动注入


bean初始化过程：当bean已经new出来了，并且已经完成了属性的填充（自动装配）
bean初始化顺序：static  --->construct --->postProcessBeforeInitialization --> @postConstruct init方法 --> postProcessAfterInitialization
java对象初始化春旭

第七次 BeanPostProcessor----postProcessBeforeInitialization()
        bean初始化之前执行
        （上面属性已经填充，bean已经new有了。Instantiation 实例化，  Initialization初始化）

第八次 BeanPostProcessor----postProcessAfterInitialization()
        bean初始化之后执行，


第九次 bean销毁的一个后置处理器 -----destory
```





###spring的后置处理器
```
一、InstantiationAwareBeanPostProcessor
InstantiationAwareBeanPostProcessor接口继承BeanPostProcessor接口，它内部提供了3个方法，再加上BeanPostProcessor接口内部的2个方法，所以实现这个接口需要实现5个方法。InstantiationAwareBeanPostProcessor接口的主要作用在于目标对象的实例化过程中需要处理的事情，包括实例化对象的前后过程以及实例的属性设置

在org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#createBean()方法的Object bean = resolveBeforeInstantiation(beanName, mbdToUse);方法里面执行了这个后置处理器

1、postProcessBeforeInstantiation
在目标对象实例化之前调用，方法的返回值类型是Object，我们可以返回任何类型的值。由于这个时候目标对象还未实例化，所以这个返回值可以用来代替原本该生成的目标对象的实例(一般都是代理对象)。如果该方法的返回值代替原本该生成的目标对象，后续只有postProcessAfterInitialization方法会调用，其它方法不再调用；否则按照正常的流程走

2、postProcessAfterInstantiation
方法在目标对象实例化之后调用，这个时候对象已经被实例化，但是该实例的属性还未被设置，都是null。如果该方法返回false，会忽略属性值的设置；如果返回true，会按照正常流程设置属性值。方法不管postProcessBeforeInstantiation方法的返回值是什么都会执行

3、postProcessPropertyValues
方法对属性值进行修改(这个时候属性值还未被设置，但是我们可以修改原本该设置进去的属性值)。如果postProcessAfterInstantiation方法返回false，该方法不会被调用。可以在该方法内对属性值进行修改

4、postProcessBeforeInitialization&postProcessAfterInitialization
父接口BeanPostProcessor的2个方法postProcessBeforeInitialization和postProcessAfterInitialization都是在目标对象被实例化之后，并且属性也被设置之后调用的

二、SmartInstantiationAwareBeanPostProcessor
智能实例化Bean后置处理器（继承InstantiationAwareBeanPostProcessor）

1、determineCandidateConstructors
检测Bean的构造器，可以检测出多个候选构造器

2、getEarlyBeanReference
循环引用的后置处理器，这个东西比较复杂， 获得提前暴露的bean引用。主要用于解决循环引用的问题，只有单例对象才会调用此方法

3、predictBeanType
预测bean的类型

三、MergedBeanDefinitionPostProcessor
1、postProcessMergedBeanDefinition
缓存bean的注入信息的后置处理器，仅仅是缓存或者干脆叫做查找更加合适，没有完成注入，注入是另外一个后置处理器的作用
```
