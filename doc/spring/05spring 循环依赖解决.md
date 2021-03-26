### Spring会存在互相依赖的情况，那么怎么解决呢？


#### 循环依赖的解决方式

```
定义三个map:
1、singletonFactories      这是map                  存放 bean工厂对象解决循环依赖
2、earlySingletonObjects    这是map             	存放原始的bean对象用于解决循环依赖,注意：存到里面的对象还没有被填充属性
3、singletonObjects         这是map         		存放完全初始化好的 bean从该缓存中取出的 bean可以直接使用
```

```
	假设原对象为 A，依赖的对象为 B，然后 B又依赖A。
	这里是 一开始 A开始创建，会给 spring给个属性(正在创建中)，并且将A的 Bean放到 earlySingletonObjects中。
	然后处理后置处理器（@autowired），然后根据依赖去 getBean(B)，此时B开始创建。
	B开始创建后，也给 spring赋值一个属性(正在创建中)，也放到earlySingletonObjects中。
	B根据依赖要创建A时，发现A已经正在创建中了，就去singletonFactories获取A后，注入到 B中，B完成创建，再把 B返给 A，A完成创建
	最终放入singletonObjects。

    
    去spring容器中找bean：descriptor.resolveCandidate
```