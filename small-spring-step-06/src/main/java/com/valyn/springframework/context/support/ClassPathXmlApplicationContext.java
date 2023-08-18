package com.valyn.springframework.context.support;

import com.valyn.springframework.beans.BeansException;

/**
 * 基于类路径的 XML 应用上下文类，继承了 AbstractXmlApplicationContext 类。
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

    private String[] configLocations;

    /**
     * 默认构造函数。
     */
    public ClassPathXmlApplicationContext() {
    }

    /**
     * 从 XML 中加载 BeanDefinition，并刷新上下文。
     *
     * @param configLocations 配置文件的位置
     * @throws BeansException 如果刷新上下文失败抛出此异常
     */
    public ClassPathXmlApplicationContext(String configLocations) throws BeansException {
        this(new String[]{configLocations});
    }

    /**
     * 从 XML 中加载 BeanDefinition，并刷新上下文。
     *
     * @param configLocations 配置文件的位置数组
     * @throws BeansException 如果刷新上下文失败抛出此异常
     */
    public ClassPathXmlApplicationContext(String[] configLocations) throws BeansException {
        this.configLocations = configLocations;
        refresh();
    }

    /**
     * 获取配置文件的位置数组的方法，返回之前设置的配置文件位置数组。
     *
     * @return 配置文件的位置数组
     */
    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }
}
