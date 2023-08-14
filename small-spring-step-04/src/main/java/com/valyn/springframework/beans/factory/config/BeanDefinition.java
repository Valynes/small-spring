package com.valyn.springframework.beans.factory.config;

import com.valyn.springframework.beans.PropertyValues;

/**
 * BeanDefinition类表示一个Bean的定义。
 */
public class BeanDefinition {

    private Class beanClass; // Bean的类

    private PropertyValues propertyValues; // 属性值集合

    /**
     * 构造方法，通过指定Bean的类创建BeanDefinition对象，并初始化属性值集合为空。
     *
     * @param beanClass Bean的类
     */
    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    /**
     * 构造方法，通过指定Bean的类和属性值集合创建BeanDefinition对象。如果属性值集合为null，则初始化为空集合。
     *
     * @param beanClass       Bean的类
     * @param propertyValues  属性值集合
     */
    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }

    /**
     * 获取Bean的类。
     *
     * @return Bean的类
     */
    public Class getBeanClass() {
        return beanClass;
    }

    /**
     * 设置Bean的类。
     *
     * @param beanClass Bean的类
     */
    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    /**
     * 获取属性值集合。
     *
     * @return 属性值集合
     */
    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    /**
     * 设置属性值集合。
     *
     * @param propertyValues 属性值集合
     */
    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
