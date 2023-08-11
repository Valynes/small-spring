package com.valyn.springframework.test;

import com.valyn.springframework.beans.factory.config.BeanDefinition;
import com.valyn.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.valyn.springframework.test.bean.UserService;
import org.junit.jupiter.api.Test;


public class ApiTest {

    @Test
    public void test_BeanFactory(){
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2.注册 bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3.第一次获取 bean
        UserService userService1 = (UserService) beanFactory.getBean("userService");
        userService1.queryUserInfo();

        UserService userService2 = (UserService) beanFactory.getBean("userService");
        userService2.queryUserInfo();

        // 4.第二次获取 bean from Singleton
        UserService userService_singleton1 = (UserService) beanFactory.getSingleton("userService");
        userService_singleton1.queryUserInfo();

        UserService userService_singleton2 = (UserService) beanFactory.getSingleton("userService");
        userService_singleton2.queryUserInfo();

        System.out.println(userService1 == userService2);
        System.out.println(userService2 == userService_singleton1);
        System.out.println(userService_singleton1 == userService_singleton2);
    }

}