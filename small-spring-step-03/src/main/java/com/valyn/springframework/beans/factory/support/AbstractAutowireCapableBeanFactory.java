package com.valyn.springframework.beans.factory.support;

import com.valyn.springframework.beans.BeansException;
import com.valyn.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * AbstractAutowireCapableBeanFactory 是一个抽象类，继承自 AbstractBeanFactory 类。
 * 它实现了创建 bean 对象的方法，并提供了默认的实例化策略（InstantiationStrategy）。
 * @author demon
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    /**
     * 实例化策略，默认使用 SimpleInstantiationStrategy。
     */
    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    /**
     * 重写了父类的 createBean 方法，用于创建 bean 对象。
     * 首先通过调用 createBeanInstance 方法创建 bean 实例，
     * 然后将其添加到单例对象缓存中，并返回该实例。
     */
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition, beanName, args);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        addSingleton(beanName, bean);
        return bean;
    }

    /**
     * 使用指定的构造函数参数来创建 bean 实例。
     * 遍历 bean 类的所有声明的构造函数，
     * 如果找到与参数个数匹配的构造函数，则使用该构造函数实例化 bean 对象。
     * 最后，调用实例化策略的 instantiate 方法完成实例化过程，并返回实例化后的 bean 对象。
     */
    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor ctor : declaredConstructors) {
            if (null != args && ctor.getParameterTypes().length == args.length) {
                constructorToUse = ctor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    /**
     * 获取当前的实例化策略。
     * @return 当前的实例化策略
     */
    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    /**
     * 设置实例化策略。
     * @param instantiationStrategy 要设置的实例化策略
     */
    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

}
