package com.valyn.springframework.beans.factory.support;

import com.valyn.springframework.beans.BeansException;
import com.valyn.springframework.core.io.Resource;
import com.valyn.springframework.core.io.ResourceLoader;

/**
 * BeanDefinitionReader 接口定义了加载和解析 BeanDefinition 的读取器的方法。
 * 通过这些方法，可以将配置文件或其他形式的资源加载到 BeanDefinitionRegistry 中，并进行解析生成 BeanDefinition 对象，供容器使用。
 */
public interface BeanDefinitionReader {

    /**
     * 获取 BeanDefinition 注册器。
     *
     * @return BeanDefinitionRegistry 对象
     */
    BeanDefinitionRegistry getRegistry();

    /**
     * 获取资源加载器。
     *
     * @return ResourceLoader 对象
     */
    ResourceLoader getResourceLoader();

    /**
     * 加载给定的资源并解析其中的 BeanDefinition。
     *
     * @param resource 资源对象
     * @throws BeansException 如果加载和解析失败抛出异常
     */
    void loadBeanDefinitions(Resource resource) throws BeansException;

    /**
     * 加载给定的多个资源并解析其中的 BeanDefinition。
     *
     * @param resources 资源对象数组
     * @throws BeansException 如果加载和解析失败抛出异常
     */
    void loadBeanDefinitions(Resource... resources) throws BeansException;

    /**
     * 加载指定位置的资源并解析其中的 BeanDefinition。
     *
     * @param location 资源位置
     * @throws BeansException 如果加载和解析失败抛出异常
     */
    void loadBeanDefinitions(String location) throws BeansException;

}

