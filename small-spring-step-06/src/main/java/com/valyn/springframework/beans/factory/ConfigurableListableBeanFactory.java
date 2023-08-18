package com.valyn.springframework.beans.factory;

import com.valyn.springframework.beans.BeansException;
import com.valyn.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.valyn.springframework.beans.factory.config.BeanDefinition;
import com.valyn.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * 可配置的可列举 Bean 工厂接口，继承了 ListableBeanFactory、AutowireCapableBeanFactory 和 ConfigurableBeanFactory 接口。
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    /**
     * 根据 Bean 的名称获取对应的 Bean 定义。
     *
     * @param beanName Bean 的名称
     * @return Bean 的定义
     * @throws BeansException 如果获取 Bean 失败抛出此异常
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 预实例化所有单例 Bean 的方法。
     *
     * @throws BeansException 如果预实例化失败抛出此异常
     */
    void preInstantiateSingletons() throws BeansException;

}



