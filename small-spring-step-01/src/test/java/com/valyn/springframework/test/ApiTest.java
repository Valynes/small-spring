package com.valyn.springframework.test;

import com.valyn.springframework.BeanDefinition;
import com.valyn.springframework.BeanFactory;
import com.valyn.springframework.test.bean.UserService;
import org.testng.annotations.Test;

public class ApiTest {
    @Test
    public void test_BeanFactory() {
        // 1.初始化Bean容器
        BeanFactory beanFactory = new BeanFactory();

        // 2.注册Bean
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3.获取Bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();

    }
}
