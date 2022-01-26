package step_11.context.support;


import step_11.beans.factory.BeanFactory;
import step_11.context.ApplicationEvent;
import step_11.context.ApplicationListener;
import step_11.context.event.AbstractApplicationEventMulticaster;

/**
 * @program: small_spring_study
 * @description:
 * @author: Z-Man
 * @create: 2022-01-19 11:30
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {
    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }
//    挂载监听
    @Override
    public void multicastEvent(final ApplicationEvent event) {
        for (final ApplicationListener applicationListener : getApplicationListeners(event)) {
            applicationListener.onApplicationEvent(event);
        }
    }
}
