package com.valyn.springframework.beans.factory;

import com.valyn.springframework.beans.BeansException;

/**
 * BeanFactory 接口定义了获取 Bean 对象的功能。
 */
public interface BeanFactory {

    /**
     * 根据名称获取对应的 Bean 对象。
     *
     * @param name Bean 的名称
     * @return 对应的 Bean 对象
     * @throws BeansException 如果在获取 Bean 对象时出现异常
     */
    Object getBean(String name) throws BeansException;

    /**
     * 根据名称和参数获取对应的 Bean 对象。
     *
     * @param name Bean 的名称
     * @param args 构造函数参数
     * @return 对应的 Bean 对象
     * @throws BeansException 如果在获取 Bean 对象时出现异常
     */
    Object getBean(String name, Object... args) throws BeansException;

    /**
     * 根据名称和目标类型获取对应的 Bean 对象。
     *
     * @param name         Bean 的名称
     * @param requiredType 目标类型
     * @param <T>          目标类型的泛型
     * @return 对应的 Bean 对象
     * @throws BeansException 如果在获取 Bean 对象时出现异常
     */
    <T> T getBean(String name, Class<T> requiredType) throws BeansException;

}

