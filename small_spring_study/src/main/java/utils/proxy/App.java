package utils.proxy;

import java.lang.reflect.Proxy;

/**
 * @program: small_spring_study
 * @description:
 * @author: Z-Man
 * @create: 2022-01-18 16:26
 */
public class App {
    public static void main(String[] args) {
        Children children = new Children();
        children.play();

        Person kkk = (Person) Proxy.newProxyInstance(children.getClass().getClassLoader(), Children.class.getInterfaces(), new ChildrenInvocationHandler(children));
        kkk.play();
        kkk.like();
    }
}
