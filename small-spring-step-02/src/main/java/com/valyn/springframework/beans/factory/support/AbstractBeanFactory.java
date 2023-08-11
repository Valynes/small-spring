package com.valyn.springframework.beans.factory.support;

import com.valyn.springframework.beans.BeansException;
import com.valyn.springframework.beans.factory.BeanFactory;
import com.valyn.springframework.beans.factory.config.BeanDefinition;

/**
 * AbstractBeanFactory是Spring框架中的一个抽象类，实现了BeanFactory接口，用于提供通用的Bean工厂功能。
 *
 * 在AbstractBeanFactory中，重写了getBean方法，用于获取Bean实例。具体的实现逻辑如下：
 *
 * 首先，通过调用getSingleton方法从单例Bean缓存中获取已存在的Bean实例，如果存在则直接返回。
 * 如果单例Bean缓存中不存在该Bean实例，则通过调用getBeanDefinition方法获取到该Bean的定义信息。
 * 接着，调用createBean方法创建Bean实例，并将其添加到单例Bean缓存中。
 * 最后返回创建的Bean实例。
 * 需要注意的是，AbstractBeanFactory只是提供了获取Bean实例的逻辑，而具体的Bean定义信息以及Bean的创建过程是由子类来实现的。子类需要实现getBeanDefinition方法
 * 来获取对应Bean名称的定义信息，并实现createBean方法来创建Bean实例。
 *
 * 通过继承AbstractBeanFactory，可以实现不同类型的Bean工厂，包括使用XML配置的XmlBeanFactory、注解驱动的AnnotationConfigApplicationContext等。子类可以
 * 根据具体需求来实现getBeanDefinition和createBean方法，以支持不同的Bean定义和创建逻辑。
 *
 * 总之，AbstractBeanFactory是Spring框架中用于提供通用的Bean工厂功能的抽象类。它通过实现BeanFactory接口，定义了获取Bean实例的通用逻辑，并提供了抽象方法供子类
 * 实现具体的Bean定义和创建过程。
 */


/**
 * AbstractBeanFactory是一个抽象类，继承了DefaultSingletonBeanRegistry并实现了BeanFactory接口。
 * 它提供了获取Bean的方法，并定义了获取Bean定义和创建Bean的抽象方法。
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    /**
     * 根据名称获取Bean实例。
     * 模板方法，只定义过程不做具体实现（设计模式之一）
     *
     * @param name Bean的名称
     * @return Bean实例
     * @throws BeansException 如果获取Bean实例失败，则抛出该异常
     */
    @Override
    public Object getBean(String name) throws BeansException {
        // 先尝试从单例Bean缓存中获取实例
        Object bean = getSingleton(name);
        if (bean != null) {
            return bean;
        }

        // 如果缓存中不存在，则根据名称获取Bean定义
        BeanDefinition beanDefinition = getBeanDefinition(name);

        // 创建并返回Bean实例
        return createBean(name, beanDefinition);
    }

    /**
     * 获取指定名称的Bean定义。
     *
     * @param beanName Bean的名称
     * @return Bean的定义
     * @throws BeansException 如果找不到对应的Bean定义，则抛出该异常
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 创建指定名称和定义的Bean实例。
     *
     * @param beanName       Bean的名称
     * @param beanDefinition Bean的定义
     * @return Bean实例
     * @throws BeansException 如果创建Bean实例失败，则抛出该异常
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;
}

/**
 * AbstractBeanFactory是一个抽象类，它继承了DefaultSingletonBeanRegistry并实现了BeanFactory接口。它提供了获取Bean的方法，并定义了获取Bean定义和创建Bean的抽象方法。
 *
 * 该类提供了以下方法：
 *
 * getBean(String name)：根据名称获取Bean实例。首先尝试从单例Bean缓存中获取实例，如果缓存中不存在，则根据名称获取Bean定义，并使用抽象方法createBean创建并返回Bean实例。
 *
 * getBeanDefinition(String beanName)：获取指定名称的Bean定义。由子类实现具体的获取Bean定义的逻辑。
 *
 * createBean(String beanName, BeanDefinition beanDefinition)：创建指定名称和定义的Bean实例。由子类实现具体的创建Bean的逻辑。
 *
 * 注意：AbstractBeanFactory继承自DefaultSingletonBeanRegistry，该类提供了单例Bean的注册和获取功能。具体的单例Bean管理过程由DefaultSingletonBeanRegistry及其相关类实现。
 */