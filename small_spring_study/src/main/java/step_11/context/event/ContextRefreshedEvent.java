package step_11.context.event;

/**
 * @program: small_spring_study
 * @description:刷新
 * @author: Z-Man
 * @create: 2022-01-18 19:08
 */
public class ContextRefreshedEvent extends ApplicationContextEvent {
    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}
