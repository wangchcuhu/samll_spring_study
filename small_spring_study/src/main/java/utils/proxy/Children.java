package utils.proxy;

import java.lang.reflect.InvocationHandler;

/**
 * @program: small_spring_study
 * @description:
 * @author: Z-Man
 * @create: 2022-01-18 16:21
 */
public class Children implements Person{
    @Override
    public void play() {
        System.out.println("孩子喜欢玩玩具");
    }

    @Override
    public void like() {
        System.out.println("孩子喜欢玩具");
    }
}
