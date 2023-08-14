package com.valyn.springframework.beans.factory.support;

import com.valyn.springframework.beans.BeansException;
import com.valyn.springframework.beans.factory.BeanFactory;
import com.valyn.springframework.beans.factory.config.BeanDefinition;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    /**
     * 根据Bean名称获取Bean实例。
     *
     * @param name Bean名称
     * @return Bean实例
     * @throws BeansException 如果获取Bean实例失败，则抛出BeansException异常
     */
    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }

    /**
     * 根据Bean名称和构造函数参数获取Bean实例。
     *
     * @param name Bean名称
     * @param args 构造函数参数
     * @return Bean实例
     * @throws BeansException 如果获取Bean实例失败，则抛出BeansException异常
     */
    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }

    /**
     * 根据Bean名称和构造函数参数获取Bean实例。
     *
     * @param name Bean名称
     * @param args 构造函数参数
     * @param <T>  Bean类型
     * @return Bean实例
     */
    protected <T> T doGetBean(final String name, final Object[] args) {
        // 先尝试从单例池中获取Bean实例
        Object bean = getSingleton(name);
        if (bean != null) {
            return (T) bean;
        }

        // 如果单例池中没有对应的Bean实例，则创建新的Bean实例
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T) createBean(name, beanDefinition, args);
    }

    /**
     * 根据Bean名称获取Bean定义。
     *
     * @param beanName Bean名称
     * @return Bean定义
     * @throws BeansException 如果获取Bean定义失败，则抛出BeansException异常
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 创建Bean实例。
     *
     * @param beanName       Bean名称
     * @param beanDefinition Bean定义
     * @param args           构造函数参数
     * @return 创建的Bean实例
     * @throws BeansException 如果创建Bean实例失败，则抛出BeansException异常
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;

}
