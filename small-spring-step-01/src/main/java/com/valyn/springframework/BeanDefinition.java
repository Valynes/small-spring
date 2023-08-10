package com.valyn.springframework;

/**
 * BeanDefinition（也称为“Bean定义”）是用于描述和配置一个Bean的元数据对象。它包含了有关Bean的各种属性和配置信息，如类名、作用域、依赖关系、初始化方法、销毁方法等。
 *
 * BeanDefinition接口定义了访问和操作Bean定义的方法。通过BeanDefinition，我们可以动态地创建、修改和管理Bean的配置信息。在Spring容器启动时，会解析和注册所有的
 * BeanDefinition，并根据这些定义创建相应的Bean实例。
 */
public class BeanDefinition {
    private Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }
}
