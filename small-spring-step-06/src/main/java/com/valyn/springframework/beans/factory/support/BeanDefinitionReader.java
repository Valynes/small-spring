package com.valyn.springframework.beans.factory.support;

import com.valyn.springframework.beans.BeansException;
import com.valyn.springframework.core.io.Resource;
import com.valyn.springframework.core.io.ResourceLoader;

/**
 * BeanDefinitionReader 接口定义了加载和解析 BeanDefinition 的读取器的方法。
 * 通过这些方法，可以将配置文件或其他形式的资源加载到 BeanDefinitionRegistry 中，并进行解析生成 BeanDefinition 对象，供容器使用。
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String... locations) throws BeansException;

}

