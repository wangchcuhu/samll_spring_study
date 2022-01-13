package step_08;

import org.junit.Test;
import step_08.beans.factory.BeanFactory;
import step_08.context.support.ClassPathXmlApplicationContext;


import java.io.IOException;


public class ApiTest {
    @Test
    public void test_BeanFactory() throws IOException {
        //自动执行
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        //取出bean(这里我加了一个方法将内部私有的beanFactory暴露出来，实际上不因该这样做)
        BeanFactory beanFactory = classPathXmlApplicationContext.getBeanFactory();
    }
}
