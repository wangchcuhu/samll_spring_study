package step_09;

import org.junit.Test;
import step_09.beans.factory.FactoryBean;
import step_09.beans.factory.support.DefaultListableBeanFactory;
import step_09.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;


public class ApiTest {
    @Test
    public void test_BeanFactory() throws IOException {
        //自动执行
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring9.xml");
//        //暴露出内部的Factory资源,直接使用池子中的资源
        DefaultListableBeanFactory factoryBean = classPathXmlApplicationContext.getBeanFactory();
        UserService userService= (UserService) factoryBean.getBean("userService");
//      System.out.println(userService.getUserDao().queryUserName("10001"));
        //这里使用了FactoryBean池子中的代理
        IUserDao iUserDao = (IUserDao) factoryBean.getBean("IUserDao");
        System.out.println(iUserDao.queryUserName("10001"));
        System.out.println(userService.getUserDao().queryUserName("10001"));
    }
}
