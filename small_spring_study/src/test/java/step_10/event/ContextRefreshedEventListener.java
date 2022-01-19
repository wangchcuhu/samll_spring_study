package step_10.event;


import step_10.context.ApplicationListener;
import step_10.context.event.ContextRefreshedEvent;
/**
 * 生命周期，这个bean被注入到池子中，然后从池子中放到set中，当街收到ContextClosedEvent的对象时就会出发，上下文手动在refresh塞了
 * ContextClosedEvent的对象，会触发
 */
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("刷新事件：" + this.getClass().getName());
    }

}
