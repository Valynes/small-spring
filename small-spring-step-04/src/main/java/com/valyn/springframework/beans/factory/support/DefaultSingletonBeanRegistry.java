package com.valyn.springframework.beans.factory.support;

import com.valyn.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    // 用于存储单例Bean对象的Map集合
    private Map<String, Object> singletonObjects = new HashMap<>();

    /**
     * 根据Bean名称获取对应的单例Bean对象。
     *
     * @param beanName Bean名称
     * @return 单例Bean对象
     */
    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    /**
     * 向单例Bean注册表中添加指定的单例Bean对象。
     *
     * @param beanName        Bean名称
     * @param singletonObject 单例Bean对象
     */
    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }

}
