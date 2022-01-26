package step_11.context.event;

/**
 * @program: small_spring_study
 * @description:关闭
 * @author: Z-Man
 * @create: 2022-01-18 19:07
 */
public class ContextClosedEvent extends ApplicationContextEvent {
    public ContextClosedEvent(Object source) {
        super(source);
    }
}
