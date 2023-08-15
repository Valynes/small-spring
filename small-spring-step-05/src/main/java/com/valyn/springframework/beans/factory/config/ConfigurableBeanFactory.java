package com.valyn.springframework.beans.factory.config;

import com.valyn.springframework.beans.factory.HierarchicalBeanFactory;

/**
 * ConfigurableBeanFactory 是一个接口，扩展了 HierarchicalBeanFactory 和 SingletonBeanRegistry 接口。
 * 它定义了一些常量用于指定 Bean 的作用域。
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    /**
     * 作用域为 singleton 的常量表示每个 Bean 在容器中只存在一个实例。
     */
    String SCOPE_SINGLETON = "singleton";

    /**
     * 作用域为 prototype 的常量表示每个 Bean 每次请求时都会创建一个新的实例。
     */
    String SCOPE_PROTOTYPE = "prototype";

}

