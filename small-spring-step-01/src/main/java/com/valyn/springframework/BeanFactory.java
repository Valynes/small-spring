package com.valyn.springframework;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Spring容器
 *
 * BeanFactory是Spring框架中的一个核心接口，用于管理和获取Bean实例。它是Spring IoC容器的基础接口，负责创建、配置和管理Bean的生命周期。
 */
public class BeanFactory {
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    /**
     * 获取Bean对象
     * @param name 存入BeanDefinition时的Key，Spring将会使用默认的Bean名称来标识该Bean
     * @return
     */
    public Object getBean(String name) {
        // beanDefinitionMap.get(name)取到的是BeanDefinition对象，通过getBean()取到的才是需要的Bean对象
        return beanDefinitionMap.get(name).getBean();
    }

    /**
     * 存入Spring容器
     * @param name Key
     * @param beanDefinition Value
     */
    public  void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
    }
}
