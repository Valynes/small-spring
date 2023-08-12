package com.valyn.springframework.beans.factory.config;

/**
 * BeanDefinition是一个类，用于保存Bean的定义信息。
 *
 * 该类包含以下成员：
 *
 * beanClass：Bean的类对象，表示该Bean对应的类。
 * BeanDefinition提供了以下方法：
 *
 * BeanDefinition(Class beanClass)：构造方法，创建一个新的BeanDefinition对象。
 *
 * getBeanClass()：获取Bean的类对象。
 *
 * setBeanClass(Class beanClass)：设置Bean的类对象。
 *
 * BeanDefinition一般用于描述Bean的基本信息，如Bean所属的类。在Spring容器中，通过解析和注册BeanDefinition对象，可以实现Bean的实例化、依赖注入等操作。
 */
public class BeanDefinition {
    private Class beanClass;

    /**
     * 构造方法，创建一个新的BeanDefinition对象。
     *
     * @param beanClass Bean的类对象
     */
    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    /**
     * 获取Bean的类对象。
     *
     * @return Bean的类对象
     */
    public Class getBeanClass() {
        return beanClass;
    }

    /**
     * 设置Bean的类对象。
     *
     * @param beanClass Bean的类对象
     */
    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}


