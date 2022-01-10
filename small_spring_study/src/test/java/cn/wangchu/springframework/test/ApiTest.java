package cn.wangchu.springframework.test;

import cn.wangchu.springframework.BeanDefinition;
import cn.wangchu.springframework.BeanFactory;
import cn.wangchu.springframework.test.bean.UserService;
import org.junit.Test;


public class ApiTest {
    @Test
    public void test_BeanFactory(){
        //1.初始化BeanFactory
        BeanFactory beanFactory = new BeanFactory();

        //2.初始化bean然后装载(注入bean)
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        beanFactory.registerBeanDefinition("userService",beanDefinition);

        //3.获取bean
        UserService userService =(UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
        }
}
