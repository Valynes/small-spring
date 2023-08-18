package com.valyn.springframework.beans.factory.config;

import com.valyn.springframework.beans.BeansException;
import com.valyn.springframework.beans.factory.BeanFactory;

/**
 * AutowireCapableBeanFactory 接口继承自 BeanFactory 接口。
 * 该接口定义了执行 BeanPostProcessors 接口实现类的前置和后置处理方法。
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * 在初始化之前，执行 BeanPostProcessors 接口实现类的 postProcessBeforeInitialization 方法。
     *
     * @param existingBean 被创建的实例对象
     * @param beanName     Bean 的名称
     * @return 处理后的实例对象
     * @throws BeansException 如果处理过程中出现异常，则抛出 BeansException
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    /**
     * 在初始化之后，执行 BeanPostProcessors 接口实现类的 postProcessAfterInitialization 方法。
     *
     * @param existingBean 被创建的实例对象
     * @param beanName     Bean 的名称
     * @return 处理后的实例对象
     * @throws BeansException 如果处理过程中出现异常，则抛出 BeansException
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;

}

