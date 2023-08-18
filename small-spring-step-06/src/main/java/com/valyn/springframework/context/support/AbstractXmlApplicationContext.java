package com.valyn.springframework.context.support;

import com.valyn.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.valyn.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 抽象的 XML 应用上下文类，继承了 AbstractRefreshableApplicationContext 类。
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    /**
     * 加载 Bean 定义到 BeanFactory 中的方法，实现了抽象方法 loadBeanDefinitions()。
     *
     * @param beanFactory BeanFactory 对象
     */
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        // 创建一个 XmlBeanDefinitionReader 对象，用于读取 XML 配置文件中的 Bean 定义
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        // 获取配置文件的位置数组
        String[] configLocations = getConfigLocations();
        if (configLocations != null) {
            // 调用 XmlBeanDefinitionReader 的 loadBeanDefinitions() 方法加载配置文件中的 Bean 定义
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    /**
     * 获取配置文件的位置数组的抽象方法，需要子类去实现。
     *
     * @return 配置文件的位置数组
     */
    protected abstract String[] getConfigLocations();

}
