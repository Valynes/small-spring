package com.valyn.springframework.context.support;

import com.valyn.springframework.beans.BeansException;
import com.valyn.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.valyn.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * 抽象的可刷新的应用上下文类，继承了 AbstractApplicationContext 类。
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    /**
     * 刷新 BeanFactory 的方法，实现了抽象方法 refreshBeanFactory()。
     *
     * @throws BeansException 如果刷新 BeanFactory 失败抛出此异常
     */
    @Override
    protected void refreshBeanFactory() throws BeansException {
        // 创建一个新的 DefaultListableBeanFactory 对象
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        // 加载 Bean 定义到 BeanFactory 中
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    /**
     * 创建一个新的 DefaultListableBeanFactory 对象的方法。
     *
     * @return 新的 DefaultListableBeanFactory 对象
     */
    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    /**
     * 加载 Bean 定义到 BeanFactory 中的抽象方法，需要子类去实现。
     *
     * @param beanFactory BeanFactory 对象
     */
    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    /**
     * 获取 BeanFactory 的方法，返回之前创建的 DefaultListableBeanFactory 对象。
     *
     * @return BeanFactory 对象
     */
    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

}

