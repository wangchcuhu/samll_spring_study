package step_10.context;

import java.util.EventObject;

/**
 * @program: small_spring_study
 * @description:EventObject的作用是存储是使用Object存储资源，并且资源不可以被序列化
 * @author: Z-Man
 * @create: 2022-01-18 18:54
 */
public abstract class ApplicationEvent extends EventObject {
    public ApplicationEvent(Object source) {
        super(source);
    }
}
