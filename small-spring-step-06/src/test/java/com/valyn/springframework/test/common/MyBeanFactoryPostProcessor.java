package com.valyn.springframework.test.common;

import com.valyn.springframework.beans.BeansException;
import com.valyn.springframework.beans.PropertyValue;
import com.valyn.springframework.beans.PropertyValues;
import com.valyn.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.valyn.springframework.beans.factory.config.BeanDefinition;
import com.valyn.springframework.beans.factory.config.BeanFactoryPostProcessor;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();

        propertyValues.addPropertyValue(new PropertyValue("company", "改为：字节跳动"));
    }

}

