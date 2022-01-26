package step_11.aop.framework;

import org.aopalliance.intercept.MethodInterceptor;
import step_11.aop.AdvisedSupport;
import step_11.event.IUserService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @program: small_spring_study
 * @description:
 * @author: Z-Man
 * @create: 2022-01-26 13:35
 */
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {
    /**
     * 里面包装了匹配器和目标对象
     */
    private final AdvisedSupport advised;

    /**
     * 包装一下符合需要代理的条件
     * @param advised
     */
    public JdkDynamicAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //判断调用的方法是不是需要拦截的方法
        if (advised.getMethodMatcher().matches(method, advised.getTargetSource().getClass())) {
            System.out.println("符合条件，执行调用");
            // 方法拦截器
            MethodInterceptor methodInterceptor = advised.getMethodInterceptor();
            // 反射调用----(这里会先调用我们额外的操作，最后再调用method.invoke(advised.getTargetSource(),args);)
            return methodInterceptor.invoke(new ReflectiveMethodInvocation(advised. getTargetSource().getTarget(), method, args));
        }
        //正常调用
        return method.invoke(advised.getTargetSource(),args);
    }

    /**
     *返回的就是组装好的代理类
     * @return
     */
    @Override
    public Object getProxy() {
     return  Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),advised.getTargetSource().getTargetClass(), this);
}
}
