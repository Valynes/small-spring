package com.valyn.springframework.beans.factory.support;

import com.valyn.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * DefaultSingletonBeanRegistry是一个实现了SingletonBeanRegistry接口的类，用于管理单例Bean实例。
 * 它使用一个名为singletonObjects的HashMap来存储单例Bean的实例。
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    // 存储单例Bean的实例
    private Map<String, Object> singletonObjects = new HashMap<>();

    /**
     * 获取指定名称的单例Bean实例。
     *
     * @param beanName 要获取的单例Bean的名称
     * @return 单例Bean的实例，如果不存在则返回null
     */
    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    /**
     * 将指定名称的Bean实例添加到单例Bean实例的注册表中。
     *
     * @param beanName        Bean的名称
     * @param singletonObject 单例Bean的实例
     */
    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }
}

/**
 * DefaultSingletonBeanRegistry是一个实现了SingletonBeanRegistry接口的类，用于管理单例Bean的实例。它通过一个名为singletonObjects的HashMap来存储单例Bean的实例。
 *
 * 该类提供了以下方法：
 *
 * getSingleton(String beanName)：根据指定的Bean名称，从singletonObjects中获取对应的单例Bean实例。如果找不到对应的实例，则返回null。
 *
 * addSingleton(String beanName, Object singletonObject)：将指定名称的Bean实例添加到singletonObjects中，用于注册单例Bean的实例。
 *
 * 注意：DefaultSingletonBeanRegistry只是提供了单例Bean实例的存储和获取功能，并不负责创建Bean实例。具体的Bean创建逻辑由其他类来实现。
 */
