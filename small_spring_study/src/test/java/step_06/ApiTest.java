package step_06;

import cn.hutool.core.io.IoUtil;
import org.junit.Test;
import step_05.bean.UserDao;
import step_05.bean.UserService;
import step_05.core.io.ClassPathResource;
import step_05.core.io.Resource;
import step_05.factory.support.BeanDefinitionRegistry;
import step_05.factory.support.DefaultListableBeanFactory;
import step_05.factory.xml.XmlBeanDefinitionReader;
import step_06.beans.factory.BeanFactory;
import step_06.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;


public class ApiTest {
    @Test
    public void test_BeanFactory() throws IOException {
        //自动执行
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        //取出bean(这里我加了一个方法将内部私有的beanFactory暴露出来，实际上不因该这样做)
        BeanFactory beanFactory = classPathXmlApplicationContext.getBeanFactory();
        UserDao userDao = (UserDao) beanFactory.getBean("userDao");
        System.out.println(userDao.queryUserName("10001"));
        System.out.println(userDao.queryUserName("10002"));
        System.out.println(userDao.queryUserName("10003"));
        UserService userService = (UserService) beanFactory.getBean("userService");
        //验证前置beanFactory的中间件开发完成
        System.out.println(userService.getuId());
        System.out.println(userService.getUserDao().queryUserName("10001"));
    }
}
