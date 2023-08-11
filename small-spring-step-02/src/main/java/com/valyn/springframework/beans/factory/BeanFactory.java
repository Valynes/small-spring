package com.valyn.springframework.beans.factory;

import com.valyn.springframework.beans.BeansException;

/**
 * BeanFactory是一个接口，用于获取Bean实例。
 */
public interface BeanFactory {

    /**
     * 根据Bean的名称获取对应的Bean实例。
     *
     * @param name Bean的名称
     * @return Bean实例
     * @throws BeansException 如果获取Bean实例失败，则抛出该异常
     */
    Object getBean(String name) throws BeansException;

}

/**
 * BeanFactory是一个接口，用于获取Bean实例。
 *
 * 该接口定义了一个方法：
 *
 * getBean(String name)：根据Bean的名称获取对应的Bean实例。
 * 如果成功获取到Bean实例，则返回该实例；否则，抛出BeansException异常。
 *
 * BeanFactory是Spring框架中的核心接口之一，它定义了Bean的获取方式，供开发者使用。具体的Bean获取逻辑由实现BeanFactory接口的类来实现。
 */