package com.valyn.springframework.beans.factory;

import com.valyn.springframework.beans.BeansException;
import com.valyn.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.valyn.springframework.beans.factory.config.BeanDefinition;
import com.valyn.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * ConfigurableListableBeanFactory 接口继承了 ListableBeanFactory、AutowireCapableBeanFactory 和 ConfigurableBeanFactory 接口，
 * 并定义了获取 BeanDefinition 的功能。
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    /**
     * 根据给定的 bean 名称获取对应的 BeanDefinition 对象。
     *
     * @param beanName bean 的名称
     * @return 对应的 BeanDefinition 对象
     * @throws BeansException 如果在获取 BeanDefinition 对象时出现异常
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

}

