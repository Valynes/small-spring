package com.valyn.springframework.beans.factory.config;

import com.valyn.springframework.beans.BeansException;
import com.valyn.springframework.beans.factory.ConfigurableListableBeanFactory;

/**
 * BeanPostProcessor 接口定义了在 Bean 对象执行初始化方法之前和之后，提供自定义的处理逻辑的方法。
 */
public interface BeanPostProcessor {

    /**
     * 在 Bean 对象执行初始化方法之前，执行此方法。
     *
     * @param bean     当前的 Bean 对象
     * @param beanName 当前的 Bean 对象的名称
     * @return 修改后的 Bean 对象（可以是原始对象或者经过修改的代理对象）
     * @throws BeansException 如果处理过程中出现异常，则抛出 BeansException
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * 在 Bean 对象执行初始化方法之后，执行此方法。
     *
     * @param bean     当前的 Bean 对象
     * @param beanName 当前的 Bean 对象的名称
     * @return 修改后的 Bean 对象（可以是原始对象或者经过修改的代理对象）
     * @throws BeansException 如果处理过程中出现异常，则抛出 BeansException
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;

}

