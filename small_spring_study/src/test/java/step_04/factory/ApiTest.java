package step_04.factory;

import org.junit.Test;
import step_04.PropertyValue;
import step_04.PropertyValues;
import step_04.factory.bean.UserDao;
import step_04.factory.bean.UserService;
import step_04.factory.config.BeanDefinition;
import step_04.factory.config.BeanReference;
import step_04.factory.support.DefaultListableBeanFactory;


public class ApiTest {
    @Test
    public void test_BeanFactory() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //疑问同名的不同的有参无参的类生成的对象该怎么区分
        //先在容器中中注册一个UserDao
        //注册BeanFactory
        beanFactory.registerBeanDefinition("userDao",new BeanDefinition(UserDao.class));
        //获取UserDao
        UserDao userDao = (UserDao) beanFactory.getBean("userDao");
        System.out.println(userDao.queryUserName("10001"));

        //准备要初始化的属性
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId","10001"));

        //这里实际上不是存的真正的userDao的对象
        //propertyValues.addPropertyValue(new PropertyValue("userDao",new UserDao()));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));


        // 2.注册 bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class,propertyValues);
        beanFactory.registerBeanDefinition("userService", beanDefinition);


        // 3.第一次获取 bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();

    }
}
