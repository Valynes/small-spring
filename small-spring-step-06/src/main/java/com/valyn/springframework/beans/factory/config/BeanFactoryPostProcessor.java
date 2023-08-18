package com.valyn.springframework.beans.factory.config;

import com.valyn.springframework.beans.BeansException;
import com.valyn.springframework.beans.factory.ConfigurableListableBeanFactory;

/**
 * BeanFactoryPostProcessor 接口定义了在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制。
 */
public interface BeanFactoryPostProcessor {

    /**
     * 对给定的 Bean 工厂进行后置处理，以自定义修改 BeanDefinition 属性。
     *
     * @param beanFactory 可配置的可列举的 Bean 工厂
     * @throws BeansException 如果处理过程中出现异常，则抛出 BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}
