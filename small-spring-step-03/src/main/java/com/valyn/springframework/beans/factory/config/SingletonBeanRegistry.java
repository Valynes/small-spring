package com.valyn.springframework.beans.factory.config;

/**
 * SingletonBeanRegistry是一个接口，用于管理单例Bean的注册和获取。
 *
 * 该接口定义了一个方法：
 *
 * getSingleton(String beanName)：根据Bean的名称获取对应的单例Bean实例。
 *
 * SingletonBeanRegistry提供了单例Bean的注册与获取的功能。在Spring容器中，单例Bean是指在整个容器中只有一个实例的Bean对象。通过getSingleton方法，
 * 可以根据Bean的名称获取对应的单例Bean实例。SingletonBeanRegistry一般由容器实现，用于维护单例Bean的缓存，并提供对单例Bean的访问操作。
 */
public interface SingletonBeanRegistry {

    /**
     * 根据Bean的名称获取对应的单例Bean实例。
     *
     * @param beanName Bean的名称
     * @return 单例Bean实例
     */
    Object getSingleton(String beanName);

}


