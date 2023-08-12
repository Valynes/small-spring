package com.valyn.springframework.beans.factory.support;

import com.valyn.springframework.beans.BeansException;
import com.valyn.springframework.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * DefaultListableBeanFactory是Spring框架中的一个实现了BeanDefinitionRegistry接口的类，用于管理Bean定义信息和创建Bean实例。
 *
 * 在DefaultListableBeanFactory中，它继承了AbstractAutowireCapableBeanFactory类，并实现了BeanDefinitionRegistry接口。
 * 其中，AbstractAutowireCapableBeanFactory提供了自动装配能力的功能。
 *
 * 该类定义了一个beanDefinitionMap属性，用于存储注册的Bean定义信息。通过调用registerBeanDefinition方法，可以将指定的Bean定义信息注册到beanDefinitionMap中。
 *
 * 此外，DefaultListableBeanFactory还重写了getBeanDefinition方法，用于根据给定的Bean名称从beanDefinitionMap中获取对应的Bean定义信息。如果获取不到，则抛出异常。
 *
 * 综上所述，DefaultListableBeanFactory是一个具体实现了BeanDefinitionRegistry接口的类，用于管理Bean定义信息和创建Bean实例。
 * 通过registerBeanDefinition方法可以将Bean定义信息注册到beanDefinitionMap中，通过getBeanDefinition方法可以获取已注册的Bean定义信息。同时，它还继承了
 * AbstractAutowireCapableBeanFactory，提供了自动装配的能力。
 */


/**
 * DefaultListableBeanFactory是一个继承了AbstractAutowireCapableBeanFactory并实现了BeanDefinitionRegistry接口的类。
 * 它用于注册和管理Bean定义，并提供获取Bean定义的方法。
 * 它使用一个名为beanDefinitionMap的HashMap来存储Bean定义。
 * @author demon
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

    // 存储Bean定义的Map
    private final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    /**
     * 注册指定名称的Bean定义。
     *
     * @param beanName       要注册的Bean的名称
     * @param beanDefinition Bean的定义
     */
    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    /**
     * 获取指定名称的Bean定义。
     *
     * @param beanName 要获取的Bean的名称
     * @return Bean的定义
     * @throws BeansException 如果找不到对应的Bean定义，则抛出该异常
     */
    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new BeansException("No bean named '" + beanName + "' is defined");
        }
        return beanDefinition;
    }
}

/**
 * DefaultListableBeanFactory是一个继承了AbstractAutowireCapableBeanFactory并实现了BeanDefinitionRegistry接口的类。它用于注册和管理Bean定义，并提供获取Bean定义的方法。
 *
 * 该类提供了以下方法：
 *
 * registerBeanDefinition(String beanName, BeanDefinition beanDefinition)：注册指定名称的Bean定义，将其存储到beanDefinitionMap中。
 *
 * getBeanDefinition(String beanName)：获取指定名称的Bean定义。如果找不到对应的Bean定义，则抛出BeansException异常。
 *
 * 注意：DefaultListableBeanFactory继承自AbstractAutowireCapableBeanFactory，该类提供了创建和初始化Bean实例的能力。具体的Bean创建、初始化和依赖注入过程由AbstractAutowireCapableBeanFactory及其相关类实现。
 */