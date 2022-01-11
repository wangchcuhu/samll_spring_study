package step_05;

import cn.hutool.core.io.IoUtil;
import org.junit.Test;

import step_05.bean.UserDao;
import step_05.factory.support.DefaultListableBeanFactory;
import step_05.core.io.ClassPathResource;
import step_05.core.io.Resource;
import step_05.factory.support.BeanDefinitionRegistry;
import step_05.factory.xml.XmlBeanDefinitionReader;

import java.io.IOException;
import java.io.InputStream;


public class ApiTest {
    @Test
    public void test_BeanFactory() throws IOException {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //注册Bean
        //准备数据流
        Resource resource = new ClassPathResource("spring.xml");
        InputStream inputStream = resource.getInputStream();
        //将输入流转换成可以看的字符
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
        //装载工厂
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader((BeanDefinitionRegistry) beanFactory);
        //mapper加载和注册bean
        xmlBeanDefinitionReader.loadBeanDefinitions(resource);

//        获取bean
        UserDao userDao = (UserDao) beanFactory.getBean("userDao");
        System.out.println(userDao.queryUserName("10001"));


//        // 2.注册 bean
//        BeanDefinition beanDefinition = new BeanDefinition(UserService.class,propertyValues);
//        beanFactory.registerBeanDefinition("userService", beanDefinition);
//
//
//        // 3.第一次获取 bean
//        UserService userService = (UserService) beanFactory.getBean("userService");
//        userService.queryUserInfo();

    }
}
