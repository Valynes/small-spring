package com.valyn.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.valyn.springframework.beans.BeansException;
import com.valyn.springframework.beans.PropertyValue;
import com.valyn.springframework.beans.PropertyValues;
import com.valyn.springframework.beans.factory.config.BeanDefinition;
import com.valyn.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;

/**
 * AbstractAutowireCapableBeanFactory是可自动装配的Bean工厂的抽象实现。
 * 它继承自AbstractBeanFactory类，提供了创建和填充Bean的能力。
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    /**
     * 创建Bean实例的方法。
     *
     * @param beanName       Bean名称
     * @param beanDefinition Bean定义
     * @param args           构造函数参数
     * @return 创建的Bean实例
     * @throws BeansException 如果创建Bean实例失败，则抛出BeansException异常
     */
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try {
            // 创建Bean实例
            bean = createBeanInstance(beanDefinition, beanName, args);
            // 填充Bean属性
            applyPropertyValues(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        // 将单例Bean添加到单例池中
        addSingleton(beanName, bean);
        return bean;
    }

    /**
     * 根据Bean定义创建Bean实例。
     *
     * @param beanDefinition Bean定义
     * @param beanName       Bean名称
     * @param args           构造函数参数
     * @return 创建的Bean实例
     */
    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor ctor : declaredConstructors) {
            if (null != args && ctor.getParameterTypes().length == args.length) {
                constructorToUse = ctor;
                break;
            }
        }
        // 使用实例化策略创建Bean实例
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    /**
     * 填充Bean的属性值。
     *
     * @param beanName       Bean名称
     * @param bean           Bean实例
     * @param beanDefinition Bean定义
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                if (value instanceof BeanReference) {
                    // 若属性值是BeanReference类型，获取引用的Bean实例
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                // 填充属性值
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeansException("Error setting property values：" + beanName);
        }
    }

    /**
     * 获取实例化策略。
     *
     * @return 实例化策略
     */
    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    /**
     * 设置实例化策略。
     *
     * @param instantiationStrategy 实例化策略
     */
    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

}
