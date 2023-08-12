package com.valyn.springframework.beans.factory.support;

import com.valyn.springframework.beans.BeansException;
import com.valyn.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * InstantiationStrategy 是一个实例化策略的接口，用于创建对象实例。
 *
 * 在实现类中，通过 instantiate 方法来实例化对象。该方法接收以下参数：
 * - beanDefinition：要创建的 bean 的定义信息
 * - beanName：要创建的 bean 的名称
 * - ctor：要使用的构造函数（可为 null）
 * - args：构造函数参数的值（可为 null）
 *
 * 实现类需要根据具体的实例化策略，创建并返回相应的对象实例。
 *
 * 如果在实例化过程中发生异常，应该抛出 BeansException 异常。
 * @author demon
 */
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException;

}

