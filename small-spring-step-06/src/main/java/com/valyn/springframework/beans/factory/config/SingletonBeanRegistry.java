package com.valyn.springframework.beans.factory.config;

/**
 * SingletonBeanRegistry接口定义了获取单例Bean的能力。
 */
public interface SingletonBeanRegistry {

    /**
     * 根据Bean名称获取对应的单例Bean。
     *
     * @param beanName Bean名称
     * @return 单例Bean实例，如果找不到则返回null
     */
    Object getSingleton(String beanName);

}
