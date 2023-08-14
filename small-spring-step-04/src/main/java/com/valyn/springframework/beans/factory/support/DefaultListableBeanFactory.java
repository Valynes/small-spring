package com.valyn.springframework.beans.factory.support;

import com.valyn.springframework.beans.BeansException;
import com.valyn.springframework.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * 默认的可列表化Bean工厂实现类，继承自AbstractAutowireCapableBeanFactory类，并实现了BeanDefinitionRegistry接口。
 * 该类负责管理和操作Bean定义，提供了注册Bean定义和获取Bean定义的功能。
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

    // 存储Bean名称和对应的Bean定义的映射关系
    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    /**
     * 将指定的Bean定义注册到Bean定义映射表中。
     *
     * @param beanName       Bean名称
     * @param beanDefinition Bean定义
     */
    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    /**
     * 根据Bean名称获取对应的Bean定义。
     *
     * @param beanName Bean名称
     * @return Bean定义
     * @throws BeansException 如果获取Bean定义失败，则抛出BeansException异常
     */
    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new BeansException("No bean named '" + beanName + "' is defined");
        }
        return beanDefinition;
    }
}
