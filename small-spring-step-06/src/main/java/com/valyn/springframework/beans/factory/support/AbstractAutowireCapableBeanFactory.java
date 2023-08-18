package com.valyn.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.valyn.springframework.beans.BeansException;
import com.valyn.springframework.beans.PropertyValue;
import com.valyn.springframework.beans.PropertyValues;
import com.valyn.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.valyn.springframework.beans.factory.config.BeanDefinition;
import com.valyn.springframework.beans.factory.config.BeanPostProcessor;
import com.valyn.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;

/**
 * AbstractAutowireCapableBeanFactory是可自动装配的Bean工厂的抽象实现。
 * 它继承自AbstractBeanFactory类，提供了创建和填充Bean的能力。
 */
/**
 * 可自动装配的抽象 Bean 工厂类，继承了 AbstractBeanFactory 类并实现了 AutowireCapableBeanFactory 接口。
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    /** 实例化策略，默认使用 CglibSubclassingInstantiationStrategy */
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    /**
     * 创建指定名称的 Bean 对象。
     *
     * @param beanName       Bean 的名称
     * @param beanDefinition Bean 的定义
     * @param args           Bean 的参数
     * @return 创建的 Bean 对象
     * @throws BeansException 如果创建 Bean 失败抛出此异常
     */
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try {
            // 创建 Bean 实例
            bean = createBeanInstance(beanDefinition, beanName, args);
            // 填充 Bean 属性
            applyPropertyValues(beanName, bean, beanDefinition);
            // 初始化 Bean，并应用 BeanPostProcessor 的前置和后置处理方法
            bean = initializeBean(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        // 将创建的 Bean 添加到单例缓存中
        addSingleton(beanName, bean);
        return bean;
    }

    /**
     * 创建指定 Bean 定义的实例。
     *
     * @param beanDefinition Bean 的定义
     * @param beanName       Bean 的名称
     * @param args           Bean 的参数
     * @return 创建的 Bean 实例
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
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    /**
     * 填充 Bean 的属性值。
     *
     * @param beanName       Bean 的名称
     * @param bean           Bean 对象
     * @param beanDefinition Bean 的定义
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {

                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                if (value instanceof BeanReference) {
                    // 如果属性值是 BeanReference，从容器中获取对应 Bean 的实例
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                // 使用反射给属性赋值
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeansException("Error setting property values: " + beanName);
        }
    }

    /**
     * 返回实例化策略。
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

    /**
     * 初始化 Bean 对象。
     *
     * @param beanName       Bean 的名称
     * @param bean           Bean 对象
     * @param beanDefinition Bean 的定义
     * @return 初始化后的 Bean 对象
     */
    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        // 1. 执行 BeanPostProcessor 的 Before 处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

        // TODO: 调用初始化方法
        invokeInitMethods(beanName, wrappedBean, beanDefinition);

        // 2. 执行 BeanPostProcessor 的 After 处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
        return wrappedBean;
    }

    /**
     * 调用 Bean 的初始化方法。
     *
     * @param beanName       Bean 的名称
     * @param wrappedBean    初始化前的 Bean 对象
     * @param beanDefinition Bean 的定义
     */
    private void invokeInitMethods(String beanName, Object wrappedBean, BeanDefinition beanDefinition) {
        // 待完成内容
    }

    /**
     * 应用 BeanPostProcessor 的 Before 处理。
     *
     * @param existingBean 初始化前的 Bean 对象
     * @param beanName     Bean 的名称
     * @return 处理后的 Bean 对象
     * @throws BeansException 如果处理失败抛出此异常
     */
    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if (null == current) {
                return result;
            }
            result = current;
        }
        return result;
    }

    /**
     * 应用 BeanPostProcessor 的 After 处理。
     *
     * @param existingBean 初始化后的 Bean 对象
     * @param beanName     Bean 的名称
     * @return 处理后的 Bean 对象
     * @throws BeansException 如果处理失败抛出此异常
     */
    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (null == current) {
                return result;
            }
            result = current;
        }
        return result;
    }

}

