package com.valyn.springframework.beans.factory.support;

import com.valyn.springframework.beans.factory.config.BeanDefinition;

/**
 * BeanDefinitionRegistry是一个接口，用于向注册表中注册Bean定义。
 *
 * 在Spring框架中，BeanDefinitionRegistry接口定义了一个方法registerBeanDefinition，用于将指定的BeanDefinition注册到注册表中。具体说明如下：
 *
 * java
 * void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
 * 参数说明：
 *
 * beanName：要注册的Bean的名称。
 * beanDefinition：要注册的Bean的定义信息。
 * 通过调用registerBeanDefinition方法，可以将指定的Bean定义信息注册到注册表中，以便后续能够通过名字来获取该Bean的定义信息。
 *
 * 在Spring框架的实现中，存在多个实现了BeanDefinitionRegistry接口的类，比如DefaultListableBeanFactory、XmlBeanDefinitionReader等。这些类会实现
 * registerBeanDefinition方法并将Bean定义信息保存到内部的数据结构中，从而实现了Bean的注册功能。
 *
 * 总之，BeanDefinitionRegistry接口定义了向注册表中注册Bean定义的方法，用于将Bean的定义信息保存到注册表中，以便后续对Bean进行创建和管理。
 */


/**
 * BeanDefinitionRegistry接口定义了向注册表中注册BeanDefinition的方法。
 */
public interface BeanDefinitionRegistry {

    /**
     * 向注册表中注册指定名称的BeanDefinition。
     *
     * @param beanName       要注册的Bean的名称
     * @param beanDefinition Bean的定义
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}

/**
 * BeanDefinitionRegistry是一个接口，定义了向注册表中注册BeanDefinition的方法。
 *
 * 该接口提供了以下方法：
 *
 * registerBeanDefinition(String beanName, BeanDefinition beanDefinition)：向注册表中注册指定名称的BeanDefinition，以便后续根据名称获取和创建对应的Bean实例。
 * 注意：BeanDefinition是用于描述Bean的定义信息的对象，包括Bean的类名、作用域、构造函数参数、属性等信息。不同的容器可以提供不同的BeanDefinition实现来满足各自的需求。
 */

