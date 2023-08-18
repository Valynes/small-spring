package com.valyn.springframework.beans.factory.config;

/**
 * BeanReference类表示一个对其他Bean的引用。
 */
public class BeanReference {

    private final String beanName; // 引用的Bean名称

    /**
     * 构造方法，通过指定Bean名称创建BeanReference对象。
     *
     * @param beanName 引用的Bean名称
     */
    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    /**
     * 获取引用的Bean名称。
     *
     * @return 引用的Bean名称
     */
    public String getBeanName() {
        return beanName;
    }

}
