package com.valyn.springframework.beans.factory.support;

import com.valyn.springframework.beans.BeansException;
import com.valyn.springframework.beans.factory.config.BeanDefinition;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * CglibSubclassingInstantiationStrategy 是一个实现了 InstantiationStrategy 接口的类。
 * 它使用Cglib库来实例化 bean 对象，并进行子类代理。
 *
 * 在 instantiate 方法中，首先创建一个 Enhancer 对象，用于对 bean 类进行增强。
 * 然后设置增强器的父类为 beanDefinition.getBeanClass()，即要创建 bean 对象的类。
 *
 * 然后，使用 NoOp 回调对象来设置增强器的回调函数，这里重写了 hashCode 方法并返回 super.hashCode()。
 *
 * 接下来，判断是否存在构造函数 ctor。如果不存在构造函数，则调用 enhancer.create() 来创建 bean 对象。
 * 如果存在构造函数，则调用 enhancer.create(ctor.getParameterTypes(), args) 来创建 bean 对象，并传入参数 args。
 *
 * 最后，返回创建的 bean 对象。
 * @author demon
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        if (null == ctor) {
            return enhancer.create();
        }
        return enhancer.create(ctor.getParameterTypes(), args);
    }

}

/**
 * CglibSubclassingInstantiationStrategy 是一个实现了 InstantiationStrategy 接口的类，用于使用 Cglib 库来实例化 bean 对象并进行子类代理。
 *
 * 在 instantiate 方法中，首先创建一个 Enhancer 对象，用于对需要创建的 bean 类进行增强。然后通过 enhancer.setSuperclass(beanDefinition.getBeanClass()) 设置增强器的父类为 beanDefinition.getBeanClass()，即要创建 bean 对象的类。
 *
 * 接下来，使用 NoOp 回调对象来设置增强器的回调函数。在这段代码中，重写了 hashCode 方法并返回 super.hashCode()。
 *
 * 然后，判断参数 ctor 是否为 null。如果 ctor 为 null，则调用 enhancer.create() 方法来创建 bean 对象。如果 ctor 不为 null，则调用 enhancer.create(ctor.getParameterTypes(), args) 方法来创建 bean 对象，并传入参数 args。
 *
 * 最后，返回创建的 bean 对象。
 *
 * 通过使用 CglibSubclassingInstantiationStrategy，可以在创建 bean 对象时使用 Cglib 进行子类代理，从而实现对其进行增强和定制。
 */

