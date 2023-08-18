BeanFactory（I）：用于获取Bean

ListableBeanFactory（I）：按照类型获取Bean，获取Bean容器内的Bean名称

ConfirguableListableBeanFactory（I）：获取BeanDefinition对象

BeanDefinition（C）：Bean的定义（放入IOC容器的Bean对象）

BeanReference（C）：实例属性的引用

ConfirguableBeanFactory（I）：定义了两个常量：singleton和prototype，用于设置Bean的创建方式

SingletonBeanRegistry（I）：从单例Bean容器内获取单例Bean

AbstractAutowireCapableBeanFactory（A）：实例化Bean，填充属性，添加到单例池

AbstractBeanDefinitionReader（A）：用于获取BeanDefinitionRegistry 和 ResourceLoader 

AbstractBeanFactory（C）：实现BeanFactory里的获取Bean方法

BeanDefinitionReader（I）：定义了加载和解析BeanDefinition的读取器的方法，可以将配置文件或其他形式的资源加载到BeanDefinitionRegistry中

BeanDefinitionRegistry（I）：将BeanDefinition添加到IOC容器中，以及获取是否包含BeanDefinition

CglibSubclassingInstantiationStrategy（C）：类的实例化

DefaultListableBeanFactory（C）：初始化IOC容器，将BeanDefinition添加到IOC容器中，获取Bean实例

DefaultSingletonBeanRegistry（C）：拥有一个单例池属性，可以添加获取BeanDefinition

InstantiationStrategy（I）：定义实例化方式方法

SimpleInstantiationStrategy（C）：JDK实例

XmlBeanDefinitionReader（C）：以读取XML文件方式来实例化Bean

BeanException：通用异常类

PropertyValue（C）：类的属性

PropertyValues（C）：类的属性列表集合



ClassPathResource（C）：获取InputStream

DefaultResourceLoader（C）：类实现了ResourceLoader接口，用于加载资源。

FileSystemResource（C）：以File方式打开资源

Resource（I）：定义获取InputStream方法

ResourceLoader（I）：获取resource

UrlResource（C）：以URL方式打开资源





ClassUtils（C）：获取类加载器



步骤：

1. new DefaultListableBeanFactory初始化IOC容器
2. new BeanDefinition(UserDao.class)实力化BeanDefinition（UserDao）对象
3. beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class))将BeanDefinition对象添加到IOC容器内
4. new PropertyValues()初始化Bean属性列表
5. propertyValues.addPropertyValue(new PropertyValue("uId", "10001"));
   propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));添加属性
6. BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues)初始化BeanDefinition（UserService）并注入属性
7. beanFactory.registerBeanDefinition("userService", beanDefinition);添加到IOC容器
8. UserService userService = (UserService) beanFactory.getBean("userService");获取Bean，先从单例池中获取，如果没有则实例化Bean对象

```java
@Test
    public void test_xml() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 读取配置文件&注册Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml"); //封装了读取，创建Bean添加属性代码

        // 3. 获取Bean对象调用方法
        UserService userService = beanFactory.getBean("userService", UserService.class);
        UserService userService1 = beanFactory.getBean("userService", UserService.class);
        System.out.println(userService.equals(userService1));
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }
```

