package com.valyn.springframework.context.support;

import com.valyn.springframework.beans.BeansException;
import com.valyn.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.valyn.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.valyn.springframework.beans.factory.config.BeanPostProcessor;
import com.valyn.springframework.context.ConfigurableApplicationContext;
import com.valyn.springframework.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * 抽象的应用上下文类，继承了 DefaultResourceLoader 类并实现了 ConfigurableApplicationContext 接口。
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    /**
     * 刷新上下文的方法。
     *
     * @throws BeansException 如果刷新上下文失败抛出此异常
     */
    @Override
    public void refresh() throws BeansException {
        // 1. 创建 BeanFactory，并加载 BeanDefinition
        refreshBeanFactory();

        // 2. 获取 BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 3. 在 Bean 实例化之前，执行 BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);

        // 4. BeanPostProcessor 需要在其他 Bean 对象实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);

        // 5. 提前实例化单例 Bean 对象
        beanFactory.preInstantiateSingletons();
    }

    /**
     * 刷新 BeanFactory 的抽象方法。
     *
     * @throws BeansException 如果刷新 BeanFactory 失败抛出此异常
     */
    protected abstract void refreshBeanFactory() throws BeansException;

    /**
     * 获取 BeanFactory 的抽象方法。
     *
     * @return BeanFactory 对象
     */
    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    /**
     * 执行 BeanFactoryPostProcessor 的方法。
     *
     * @param beanFactory BeanFactory 对象
     */
    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    /**
     * 注册 BeanPostProcessor 的方法。
     *
     * @param beanFactory BeanFactory 对象
     */
    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    /**
     * 获取指定类型的所有 Bean 对象。
     *
     * @param type Bean 对象的类型
     * @param <T>  Bean 对象的类型
     * @return 指定类型的所有 Bean 对象的 Map，键为 Bean 的名称，值为 Bean 的实例
     * @throws BeansException 如果获取 Bean 失败抛出此异常
     */
    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    /**
     * 获取所有 Bean 定义的名称。
     *
     * @return 所有 Bean 定义的名称数组
     */
    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    /**
     * 根据名称获取对应的 Bean 对象。
     *
     * @param name Bean 的名称
     * @return 对应名称的 Bean 对象
     * @throws BeansException 如果获取 Bean 失败抛出此异常
     */
    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    /**
     * 根据名称和参数获取对应的 Bean 对象。
     *
     * @param name Bean 的名称
     * @param args 构造函数参数
     * @return 对应名称和参数的 Bean 对象
     * @throws BeansException 如果获取 Bean 失败抛出此异常
     */
    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    /**
     * 根据名称和类型获取对应的 Bean 对象。
     *
     * @param name         Bean 的名称
     * @param requiredType Bean 的类型
     * @param <T>          Bean 的类型
     * @return 对应名称和类型的 Bean 对象
     * @throws BeansException 如果获取 Bean 失败抛出此异常
     */
    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }

}
