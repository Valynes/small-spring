package com.valyn.springframework.beans.factory.support;

import com.valyn.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String, Object> singletonObjects = new HashMap<>();

    /**
     * 根据 Bean 的名称获取对应的单例对象。
     *
     * @param beanName Bean 的名称
     * @return 对应的单例对象
     */
    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    /**
     * 将指定的单例对象添加到单例对象集合中。
     *
     * @param beanName        Bean 的名称
     * @param singletonObject 单例对象
     */
    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }

}
