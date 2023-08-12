package com.valyn.springframework.beans.factory.support;

import com.valyn.springframework.beans.BeansException;
import com.valyn.springframework.beans.factory.BeanFactory;
import com.valyn.springframework.beans.factory.config.BeanDefinition;

/**
 * AbstractBeanFactory 是一个抽象类，继承了 DefaultSingletonBeanRegistry 类并实现了 BeanFactory 接口。
 * 它提供了获取 bean 对象的方法，并定义了两个抽象方法，用于获取 BeanDefinition 和创建实例对象。
 * @author demon
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    /**
     * 根据 bean 的名称获取对应的 bean 对象。
     * 这里委托给 doGetBean 方法进行处理。
     *
     * @param name bean 的名称
     * @return 对应的 bean 对象
     * @throws BeansException 如果获取 bean 失败会抛出 BeansException 异常
     */
    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }

    /**
     * 根据 bean 的名称和参数获取对应的 bean 对象。
     * 这里委托给 doGetBean 方法进行处理。
     *
     * @param name bean 的名称
     * @param args 创建 bean 对象时使用的参数
     * @return 对应的 bean 对象
     * @throws BeansException 如果获取 bean 失败会抛出 BeansException 异常
     */
    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }

    /**
     * 根据 bean 的名称和参数获取对应的 bean 对象。
     * 首先尝试从单例对象缓存中获取 bean 对象，如果存在则直接返回。
     * 否则，根据 bean 的名称获取对应的 BeanDefinition，并通过调用 createBean 方法创建 bean 对象。
     *
     * @param name bean 的名称
     * @param args 创建 bean 对象时使用的参数
     * @param <T>  bean 对象的类型
     * @return 对应的 bean 对象
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
     * 根据 bean 的名称获取对应的 BeanDefinition。
     * 这是一个抽象方法，由子类来实现具体的逻辑。
     *
     * @param beanName bean 的名称
     * @return 对应的 BeanDefinition
     * @throws BeansException 如果获取 bean 的定义失败会抛出 BeansException 异常
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 创建 bean 对象。
     * 这是一个抽象方法，由子类来实现具体的逻辑。
     *
     * @param beanName       bean 的名称
     * @param beanDefinition bean 的定义
     * @param args           创建 bean 对象时使用的参数
     * @return 创建的 bean 对象
     * @throws BeansException 如果创建 bean 失败会抛出 BeansException 异常
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;

}

/**
 * 这段代码定义了一个名为 AbstractBeanFactory 的抽象类，它继承自 DefaultSingletonBeanRegistry 类并实现了 BeanFactory 接口。
 *
 * 这个抽象类提供了获取 bean 对象的方法，包括两个重载的 getBean 方法。第一个方法根据名称获取 bean 对象，第二个方法根据名称和参数获取 bean 对象。这两个方法内部都委托给 doGetBean 方法来处理。
 *
 * 在 doGetBean 方法中，首先尝试从单例对象缓存中获取 bean 对象，如果存在则直接返回。如果缓存中不存在，则根据 bean 的名称获取相应的 BeanDefinition，然后通过调用 createBean 方法来创建 bean 对象。
 *
 * 此外，AbstractBeanFactory 定义了两个抽象方法：getBeanDefinition 和 createBean。这两个方法需要由子类来具体实现，用于获取 bean 的定义和创建 bean 对象。
 *
 * 通过继承 AbstractBeanFactory 类并实现其中的抽象方法，可以创建具体的 bean 工厂类，用于管理和创建 bean 对象。
 */
