package com.valyn.springframework.beans.factory;

import com.valyn.springframework.beans.BeansException;

/**
 * BeanFactory 是一个接口，定义了获取 Bean 实例的方法。
 *
 * 在接口中，提供了两个重载的 getBean 方法：
 * - getBean(String name)：根据 Bean 的名称获取对应的 Bean 实例
 * - getBean(String name, Object... args)：根据 Bean 的名称以及构造函数参数值获取对应的 Bean 实例
 *
 * 这两个方法都可能抛出 BeansException 异常，表示获取 Bean 实例失败。
 * @author demon
 */
public interface BeanFactory {

    /**
     * 根据 Bean 的名称获取对应的 Bean 实例。
     *
     * @param name Bean 的名称
     * @return Bean 实例
     * @throws BeansException 如果获取 Bean 实例失败，则抛出该异常
     */
    Object getBean(String name) throws BeansException;

    /**
     * 根据 Bean 的名称以及构造函数参数值获取对应的 Bean 实例。
     *
     * @param name Bean 的名称
     * @param args 构造函数参数值
     * @return Bean 实例
     * @throws BeansException 如果获取 Bean 实例失败，则抛出该异常
     */
    Object getBean(String name, Object... args) throws BeansException;

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