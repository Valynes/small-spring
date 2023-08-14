package com.valyn.springframework.beans.factory.support;

import com.valyn.springframework.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {

    /**
     * 向注册表中注册Bean定义。
     *
     * @param beanName       Bean名称
     * @param beanDefinition Bean定义
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

}
