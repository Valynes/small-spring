package com.valyn.springframework.beans.factory.support;

import com.valyn.springframework.core.io.DefaultResourceLoader;
import com.valyn.springframework.core.io.ResourceLoader;

/**
 * AbstractBeanDefinitionReader 是一个抽象类，实现了 BeanDefinitionReader 接口。
 * 它定义了 Bean 定义读取的通用行为，并提供了对 BeanDefinitionRegistry 和 ResourceLoader 的访问方法。
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private final BeanDefinitionRegistry registry;
    private ResourceLoader resourceLoader;

    /**
     * 使用给定的 BeanDefinitionRegistry 创建一个 AbstractBeanDefinitionReader 实例，
     * 并使用默认的资源加载器 DefaultResourceLoader。
     *
     * @param registry Bean 定义注册表
     */
    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }

    /**
     * 使用给定的 BeanDefinitionRegistry 和 ResourceLoader 创建一个 AbstractBeanDefinitionReader 实例。
     *
     * @param registry       Bean 定义注册表
     * @param resourceLoader 资源加载器
     */
    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    /**
     * 获取 BeanDefinitionRegistry 对象。
     *
     * @return BeanDefinitionRegistry 对象
     */
    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    /**
     * 获取 ResourceLoader 对象。
     *
     * @return ResourceLoader 对象
     */
    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}

