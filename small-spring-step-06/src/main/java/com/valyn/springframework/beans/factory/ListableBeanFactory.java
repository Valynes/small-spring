package com.valyn.springframework.beans.factory;

import com.valyn.springframework.beans.BeansException;

import java.util.Map;

public interface ListableBeanFactory extends BeanFactory {

    /**
     * 按照类型返回 Bean 实例。
     *
     * @param type 要获取的 Bean 的类型
     * @param <T>  Bean 的类型参数
     * @return 匹配类型的 Bean 实例的映射，键为 Bean 的名称，值为相应的 Bean 实例
     * @throws BeansException 如果在获取 Bean 实例时出现异常
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * 返回注册表中所有的 Bean 名称。
     *
     * @return 所有 Bean 的名称数组
     */
    String[] getBeanDefinitionNames();

}

