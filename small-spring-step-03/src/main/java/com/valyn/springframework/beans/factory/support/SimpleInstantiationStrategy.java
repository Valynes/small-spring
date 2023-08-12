package com.valyn.springframework.beans.factory.support;

import com.valyn.springframework.beans.BeansException;
import com.valyn.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
/**
 * SimpleInstantiationStrategy 是一个实现了 InstantiationStrategy 接口的类。
 * 它使用反射来实例化 bean 对象，通过调用构造函数来创建对象实例。
 *
 * 在 instantiate 方法中，首先获取 beanDefinition.getBeanClass()，即要创建 bean 对象的类。
 * 然后判断参数 ctor 是否为 null。如果不为 null，说明存在构造函数，则使用反射创建对象实例，
 * 调用 clazz.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args) 来创建对象。
 *
 * 如果 ctor 为 null，说明不存在构造函数，则使用反射创建对象实例，调用 clazz.getDeclaredConstructor().newInstance() 来创建对象。
 *
 * 如果在实例化过程中出现异常，捕获并抛出 BeansException 异常，异常信息为 "Failed to instantiate [类名]"。
 * @author demon
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        Class clazz = beanDefinition.getBeanClass();
        try {
            if (null != ctor) {
                return clazz.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            } else {
                return clazz.getDeclaredConstructor().newInstance();
            }
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new BeansException("Failed to instantiate [" + clazz.getName() + "]", e);
        }
    }

}

/**
 * SimpleInstantiationStrategy 是一个实现了 InstantiationStrategy 接口的类，用于使用反射来实例化 bean 对象。
 *
 * 在 instantiate 方法中，首先获取 beanDefinition.getBeanClass()，即要创建 bean 对象的类。
 *
 * 然后判断参数 ctor 是否为 null。如果不为 null，说明存在构造函数，则使用反射创建对象实例，调用 clazz.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args) 来创建对象。
 *
 * 如果 ctor 为 null，说明不存在构造函数，则使用反射创建对象实例，调用 clazz.getDeclaredConstructor().newInstance() 来创建对象。
 *
 * 如果在实例化过程中出现异常，捕获并抛出 BeansException 异常，异常信息为 "Failed to instantiate [类名]"。
 */
