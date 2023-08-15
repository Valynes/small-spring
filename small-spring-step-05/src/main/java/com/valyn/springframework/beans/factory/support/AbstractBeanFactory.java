package com.valyn.springframework.beans.factory.support;

import com.valyn.springframework.beans.BeansException;
import com.valyn.springframework.beans.factory.BeanFactory;
import com.valyn.springframework.beans.factory.config.BeanDefinition;

/**
 * AbstractBeanFactory 是一个抽象类，实现了 BeanFactory 接口，并继承了 DefaultSingletonBeanRegistry 类。
 * 它提供了获取 Bean 的通用实现，并定义了获取 BeanDefinition 和创建 Bean 的抽象方法。
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    /**
     * 根据名称获取 Bean 实例。
     *
     * @param name Bean 的名称
     * @return Bean 实例
     * @throws BeansException 如果获取 Bean 失败抛出异常
     */
    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }

    /**
     * 根据名称和参数获取 Bean 实例。
     *
     * @param name Bean 的名称
     * @param args 构造函数参数
     * @return Bean 实例
     * @throws BeansException 如果获取 Bean 失败抛出异常
     */
    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }

    /**
     * 根据名称和类型获取 Bean 实例。
     *
     * @param name         Bean 的名称
     * @param requiredType Bean 的类型
     * @param <T>          Bean 的类型
     * @return Bean 实例
     * @throws BeansException 如果获取 Bean 失败抛出异常
     */
    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    /**
     * 实际获取 Bean 的操作。
     *
     * @param name Bean 的名称
     * @param args 构造函数参数
     * @param <T>  Bean 的类型
     * @return Bean 实例
     */
    protected <T> T doGetBean(final String name, final Object[] args) {
        Object bean = getSingleton(name);
        if (bean != null) {
            return (T) bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T) createBean(name, beanDefinition, args);
    }

    /**
     * 根据名称获取 BeanDefinition。
     *
     * @param beanName Bean 的名称
     * @return BeanDefinition 对象
     * @throws BeansException 如果获取 BeanDefinition 失败抛出异常
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 创建 Bean 实例。
     *
     * @param beanName       Bean 的名称
     * @param beanDefinition BeanDefinition 对象
     * @param args           构造函数参数
     * @return Bean 实例
     * @throws BeansException 如果创建 Bean 失败抛出异常
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;
}
