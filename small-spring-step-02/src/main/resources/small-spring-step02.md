```java
SingletonBeanRegistry(接口)
    getSingleton(String beanName) // 获取单例对象

DefaultSingletonBeanRegistry(实现SingletonBeanRegistry)
    Map<String, Object> singletonObjects = new HashMap<>(); // 存储单例Bean的实例
	getSingleton(String beanName) // 获取单例对象
    addSingleton(String beanName, Object singletonObject) //添加到单例Bean实例的注册表中
        
BeanFactory(接口)
    getBean(String name) //获取对应的Bean实例 

AbstractBeanFactory(继承DefaultSingletonBeanRegistry实现BeanFactory)
    getBean(String name) 
        // 先尝试从单例Bean缓存中获取实例
        Object bean = getSingleton(name);
        if (bean != null) {
            return bean;
        }

        // 如果缓存中不存在，则根据名称获取Bean定义
        BeanDefinition beanDefinition = getBeanDefinition(name);

        // 创建并返回Bean实例
        return createBean(name, beanDefinition);
	
	getBeanDefinition(String beanName) //定义
    createBean(String beanName, BeanDefinition beanDefinition) //定义
        
AbstractAutowireCapableBeanFactory(继承AbstractBeanFactory)
    createBean(String beanName, BeanDefinition beanDefinition) //实例化bean，加入单例bean缓存中
        
BeanDefinitionRegistry
    private Class beanClass;
    registerBeanDefinition(String beanName, BeanDefinition beanDefinition); //定义

DefaultListableBeanFactory(继承AbstractAutowireCapableBeanFactory实现BeanDefinitionRegistry)
    final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>(); //存储Bean定义的Map
	registerBeanDefinition(String beanName, BeanDefinition beanDefinition) //注册指定名称的Bean定义。
    getBeanDefinition(String beanName) //要获取的Bean的名称
        
BeanDefinition //bean定义
    private Class beanClass; //属性
	getBeanClass() //获取bean
    setBeanClass //设置bean
```



##### SingletonBeanRegistry(接口)定义 

1. getSingleton(String beanName) 获取单例Bean实例方法 （抽象方法）

##### DefaultSingletonBeanRegistry类  

- singletonObjects（HashMap）容器储存单例Bean 

1. 实现getSingleton(String beanName)方法（获取单例Bean实例）
2. addSingleton(String beanName, Object singletonObject) （将单例Bean实例添加到单例Bean容器中）

##### BeanFactory(接口)

1. getBean(String name)  获取Bean（抽象方法）

##### AbstractBeanFactory(继承DefaultSingletonBeanRegistry实现BeanFactory) （抽象类）

1. 实现getBean(String name)  获取Bean
2. getBeanDefinition(String beanName)（抽象方法）
3.  createBean(String beanName, BeanDefinition beanDefinition)（抽象方法）

##### AbstractAutowireCapableBeanFactory(继承AbstractBeanFactory) （抽象类）

1. 实现createBean(String beanName, BeanDefinition beanDefinition) 使用反射实例化Bean对象，并添加到单例Bean容器中

##### BeanDefinitionRegistry

- beanClass属性

1. registerBeanDefinition(String beanName, BeanDefinition beanDefinition) （抽象方法）

##### DefaultListableBeanFactory(继承AbstractAutowireCapableBeanFactory实现BeanDefinitionRegistry)

- beanDefinitionMap（HahMap）储存Bean定义对象的容器

1. registerBeanDefinition(String beanName, BeanDefinition beanDefinition) 注册指定名称的Bean定义，即将Bean定义对象放入容器
2.  getBeanDefinition(String beanName) 获取Bean定义对象

##### BeanDefinition（Bean定义，Bean的增强版定义了Sping容器需要用到的属性）

-  private Class beanClass; （属性）