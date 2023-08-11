package com.valyn.springframework.beans.factory.support;

import com.valyn.springframework.beans.BeansException;
import com.valyn.springframework.beans.factory.config.BeanDefinition;

/**
 * AbstractAutowireCapableBeanFactory是Spring框架中的一个抽象类，继承自AbstractBeanFactory，用于实现可自动装配的Bean工厂。
 *
 * 在AbstractAutowireCapableBeanFactory中，重写了createBean方法，用于创建Bean实例。具体的实现逻辑如下：
 *
 * 首先，通过beanDefinition获取到Bean的Class类型。
 * 使用反射机制，通过调用newInstance方法创建Bean的实例。
 * 将创建的Bean实例添加到单例Bean缓存中，使用addSingleton方法进行管理。
 * 最后返回创建的Bean实例。
 * 需要注意的是，AbstractAutowireCapableBeanFactory只是提供了Bean实例的创建过程，并未进行属性注入等其他装配操作。具体的装配行为是由子类来实现的，
 * 例如XmlBeanFactory、AnnotationConfigApplicationContext等都是AbstractAutowireCapableBeanFactory的子类，并负责完成具体的装配过程。
 *
 * 通过继承AbstractAutowireCapableBeanFactory，可以实现不同类型的Bean自动装配的功能，包括构造函数注入、属性注入等。同时，也可以根据具体需求在创建
 * Bean实例前后进行一些自定义的处理。
 *
 * 总之，AbstractAutowireCapableBeanFactory是Spring框架中用于实现可自动装配的Bean工厂的抽象类。它提供了一种通用的方式来创建Bean实例，并通过继承
 * 和重写的方式实现不同类型的自动装配行为。
 */


/**
 * AbstractAutowireCapableBeanFactory是一个抽象类，继承了AbstractBeanFactory。
 * 它实现了AbstractBeanFactory中的createBean方法，提供了创建Bean的能力并将其添加到单例Bean缓存中。
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    /**
     * 创建指定名称和定义的Bean实例。
     *
     * @param beanName       Bean的名称
     * @param beanDefinition Bean的定义
     * @return Bean实例
     * @throws BeansException 如果创建Bean实例失败，则抛出该异常
     */
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean;
        try {
            // 使用反射实例化Bean对象
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        // 将创建的Bean添加到单例Bean缓存中
        addSingleton(beanName, bean);

        return bean;
    }
}

/**
 * AbstractAutowireCapableBeanFactory是一个抽象类，继承了AbstractBeanFactory。它实现了AbstractBeanFactory中的createBean方法，提供了创建Bean的能力并将其添加到单例Bean缓存中。
 *
 * 该类重写了createBean方法，实现如下逻辑：
 *
 * 根据BeanDefinition中的beanClass属性使用反射实例化Bean对象。
 * 将创建的Bean添加到单例Bean缓存中，使用addSingleton方法实现。
 * 注意：AbstractAutowireCapableBeanFactory是一个抽象类，仅提供了创建Bean的基础实现。具体的自动装配、属性填充等逻辑由其子类实现。
 */
