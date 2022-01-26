package step_11.context.event;

import step_11.context.ApplicationEvent;
import step_11.context.ApplicationListener;

/**
 * @program: small_spring_study
 * @description:多播
 * @author: Z-Man
 * @create: 2022-01-18 19:10
 */
public interface ApplicationEventMulticaster {
    /**
     * 添加一个侦听器以接收所有事件的通知。
     */
    void addApplicationListener(ApplicationListener<?> applicationListener) ;

    /**
     * 从通知列表中删除一个侦听器。
     */
    void removeApplicationListener(ApplicationListener<?> applicationListener) ;

    /**
     * 将给定的应用程序事件多播到适当的侦听器。
     */
    void multicastEvent(ApplicationEvent event);

}
