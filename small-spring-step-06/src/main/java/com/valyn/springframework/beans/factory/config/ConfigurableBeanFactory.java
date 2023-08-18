package com.valyn.springframework.beans.factory.config;

import com.valyn.springframework.beans.factory.HierarchicalBeanFactory;

/**
 * ConfigurableBeanFactory 接口继承自 HierarchicalBeanFactory 接口和 SingletonBeanRegistry 接口。
 * 该接口定义了可配置的 Bean 工厂的功能。
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    /**
     * 单例作用域的常量。
     */
    String SCOPE_SINGLETON = "singleton";

    /**
     * 原型作用域的常量。
     */
    String SCOPE_PROTOTYPE = "prototype";

    /**
     * 添加一个 Bean 后置处理器。
     *
     * @param beanPostProcessor 要添加的 Bean 后置处理器
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}


