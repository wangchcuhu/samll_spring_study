package step_10.event;


import step_10.context.ApplicationListener;
import step_10.context.event.ContextClosedEvent;

/**
 * 生命周期，这个bean被注入到池子中，然后从池子中放到set中，当街收到ContextClosedEvent的对象时就会出发，上下文手动在close塞了
 * ContextClosedEvent的对象，会触发
 */
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("关闭事件：" + this.getClass().getName());
    }

}
