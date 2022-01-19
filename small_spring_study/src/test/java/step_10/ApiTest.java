package step_10;

import org.junit.Test;
import step_10.context.support.ClassPathXmlApplicationContext;
import step_10.event.CustomEvent;


import java.io.IOException;


public class ApiTest {
    @Test
    public void test_BeanFactory() throws IOException {
        //自动执行
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring10.xml");
        //向监测者推送信息,这里为什么推送信息就能触犯对应的方法呢，很简单使用便利在推送的过程中就去触发每个需要的方法，但是通过条件过滤调不需要的
        //的执行事件(用户注册后发送优惠券和短信息通知等)
        classPathXmlApplicationContext.publishEvent(new CustomEvent(classPathXmlApplicationContext,1019129009086763L,"成功了"
 ));
        classPathXmlApplicationContext.registerShutDownHook();
        System.out.println("结束");
    }
}
