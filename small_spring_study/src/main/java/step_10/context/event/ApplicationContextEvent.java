package step_10.context.event;

import step_10.context.ApplicationContext;
import step_10.context.ApplicationEvent;

/**
 * @program: small_spring_study
 * @description:
 * @author: Z-Man
 * @create: 2022-01-18 19:01
 */
public class ApplicationContextEvent extends ApplicationEvent {

    public ApplicationContextEvent(Object source) {
        super(source);
    }

    //获取资源
    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
