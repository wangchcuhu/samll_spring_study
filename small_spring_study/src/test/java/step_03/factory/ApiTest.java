package step_03.factory;

import org.junit.Test;
import step_03.factory.bean.UserService;
import step_03.factory.config.BeanDefinition;
import step_03.factory.support.DefaultListableBeanFactory;


public class ApiTest {
    @Test
    public void test_BeanFactory() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2.注册 bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3.第一次获取 bean
        UserService userService = (UserService) beanFactory.getBean("userService",18);
        System.out.println(userService.getAge());

        // 4.第二次获取 bean from Singleton
        UserService userService_singleton = (UserService) beanFactory.getSingleton("userService");
        System.out.println(userService.getAge());

    }
}
