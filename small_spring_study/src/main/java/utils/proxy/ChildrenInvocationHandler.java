package utils.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @program: small_spring_study
 * @description:
 * @author: Z-Man
 * @create: 2022-01-18 16:23
 */
public class ChildrenInvocationHandler implements InvocationHandler {
    private final Children children;

    public ChildrenInvocationHandler(Children children) {
        this.children = children;
    }

    /**
    接口的所有方法都会触发这个方法，可以用Method区分。
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method);
        System.out.println("------before-------");
        Object invoke =method.invoke(children,args);
        System.out.println("------after--------");
        return invoke;
    }
}
