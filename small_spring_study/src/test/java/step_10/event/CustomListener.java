package step_10.event;

import step_10.context.ApplicationListener;

import java.util.Date;

/**
 * @program: small_spring_study
 * @description: 消费监听者
 * @author: Z-Man
 * @create: 2022-01-19 13:50
 */
public class CustomListener implements ApplicationListener<CustomEvent> {
    //这个方法最终都会被触发(便利触发),当CustomEvent类型的事件传入的时候
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("收到："+event.getSource()+"消息;时间:"+new Date());
        System.out.println("消息:"+event.getId()+":"+event.getMessage());
    }
}
