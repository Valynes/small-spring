package com.valyn.springframework.beans.factory.support;

import com.valyn.springframework.beans.BeansException;
import com.valyn.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.valyn.springframework.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * DefaultListableBeanFactory 是一个实现了 BeanDefinitionRegistry 和 ConfigurableListableBeanFactory 接口的默认可列表化 Bean 工厂类。
 * 该类继承了 AbstractAutowireCapableBeanFactory 抽象类，提供了对 BeanDefinition 的注册、获取和操作的功能。
 * 内部使用了一个 HashMap 来存储 BeanDefinition 对象。
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    /**
     * 注册一个 BeanDefinition。
     *
     * @param beanName       Bean 的名称
     * @param beanDefinition BeanDefinition 对象
     */
    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    /**
     * 检查指定名称的 BeanDefinition 是否存在。
     *
     * @param beanName Bean 的名称
     * @return 如果存在则返回 true，否则返回 false
     */
    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    /**
     * 获取指定类型的所有 Bean 实例，以 Bean 名称为键存储在一个 Map 中并返回。
     *
     * @param type Bean 的类型
     * @return 包含指定类型的所有 Bean 实例的 Map，键是 Bean 名称，值是对应的 Bean 实例
     * @throws BeansException 如果获取过程中出现异常，则抛出 BeansException 异常
     */
    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        Map<String, T> result = new HashMap<>();
        beanDefinitionMap.forEach((beanName, beanDefinition) -> {
            Class<?> beanClass = beanDefinition.getBeanClass();
            if (type.isAssignableFrom(beanClass)) {
                result.put(beanName, (T) getBean(beanName));
            }
        });
        return result;
    }

    /**
     * 获取所有已注册的 Bean 定义的名称。
     *
     * @return 包含所有已注册的 Bean 定义名称的数组
     */
    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }

    /**
     * 根据 Bean 的名称获取对应的 BeanDefinition。
     *
     * @param beanName Bean 的名称
     * @return 对应的 BeanDefinition 对象
     * @throws BeansException 如果找不到对应的 BeanDefinition，则抛出 BeansException 异常
     */
    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null)
            throw new BeansException("No bean named '" + beanName + "' is defined");
        return beanDefinition;
    }

}
