package step_07;

import org.junit.Test;

import step_07.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;


public class ApiTest {
    @Test
    public void test_BeanFactory() throws IOException {
        //自动执行
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        //调取所有的close的方法
        classPathXmlApplicationContext.registerShutDownHook();
    }
}
