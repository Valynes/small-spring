package com.valyn.springframework.beans.factory;

import com.valyn.springframework.beans.BeansException;

public interface BeanFactory {

    /**
     * 根据给定的名称获取对应的Bean对象。
     *
     * @param name Bean名称
     * @return 对应的Bean对象
     * @throws BeansException 如果获取Bean对象失败，则抛出BeansException异常
     */
    Object getBean(String name) throws BeansException;

    /**
     * 根据给定的名称和参数获取对应的Bean对象。
     *
     * @param name Bean名称
     * @param args 构造函数参数（可选）
     * @return 对应的Bean对象
     * @throws BeansException 如果获取Bean对象失败，则抛出BeansException异常
     */
    Object getBean(String name, Object... args) throws BeansException;

}

