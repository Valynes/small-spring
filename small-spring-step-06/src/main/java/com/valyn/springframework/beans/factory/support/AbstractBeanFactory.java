package com.valyn.springframework.beans.factory.support;

import com.valyn.springframework.beans.BeansException;
import com.valyn.springframework.beans.factory.BeanFactory;
import com.valyn.springframework.beans.factory.config.BeanDefinition;
import com.valyn.springframework.beans.factory.config.BeanPostProcessor;
import com.valyn.springframework.beans.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象的 Bean 工厂类，继承了 DefaultSingletonBeanRegistry 类并实现了 ConfigurableBeanFactory 接口。
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    /** 存储 BeanPostProcessor 对象的列表，用于在创建 Bean 时应用后置处理器 */
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();

    /**
     * 根据 Bean 的名称获取对应的 Bean 对象。
     *
     * @param name Bean 的名称
     * @return 对应的 Bean 对象
     * @throws BeansException 如果获取 Bean 失败抛出此异常
     */
    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }

    /**
     * 根据 Bean 的名称和参数获取对应的 Bean 对象。
     *
     * @param name Bean 的名称
     * @param args Bean 的参数
     * @return 对应的 Bean 对象
     * @throws BeansException 如果获取 Bean 失败抛出此异常
     */
    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }

    /**
     * 根据 Bean 的名称和类型获取对应的 Bean 对象。
     *
     * @param name         Bean 的名称
     * @param requiredType Bean 的类型
     * @param <T>          Bean 的类型参数
     * @return 对应的 Bean 对象
     * @throws BeansException 如果获取 Bean 失败抛出此异常
     */
    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    /**
     * 根据给定的名称和参数，获取对应的 Bean 对象。
     *
     * @param name Bean 的名称
     * @param args Bean 的参数
     * @param <T>  Bean 的类型参数
     * @return 对应的 Bean 对象
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
     * 获取指定名称的 Bean 定义。
     *
     * @param beanName Bean 的名称
     * @return 对应的 Bean 定义
     * @throws BeansException 如果获取 Bean 定义失败抛出此异常
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 创建指定名称的 Bean 对象。
     *
     * @param beanName       Bean 的名称
     * @param beanDefinition Bean 的定义
     * @param args           Bean 的参数
     * @return 创建的 Bean 对象
     * @throws BeansException 如果创建 Bean 失败抛出此异常
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;

    /**
     * 向工厂中添加一个 Bean 后置处理器。
     *
     * @param beanPostProcessor 要添加的 Bean 后置处理器
     */
    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    /**
     * 返回应用于该工厂创建的 Bean 的 Bean 后置处理器列表。
     *
     * @return Bean 后置处理器列表
     */
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

}

