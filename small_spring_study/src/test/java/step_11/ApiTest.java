package step_11;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;
import step_11.aop.AdvisedSupport;
import step_11.aop.TargetSource;
import step_11.aop.aspectj.AspectJExpressionPointcut;
import step_11.aop.framework.JdkDynamicAopProxy;
import step_11.aop.framework.ReflectiveMethodInvocation;
import step_11.event.IUserService;
import step_11.event.UserService;
import step_11.event.UserServiceInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class ApiTest {


    @Test
    public void test_proxy_class() {
        IUserService userService = (IUserService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IUserService.class}, (proxy, method, args) -> "你被代理了！");
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }


    @Test public void test_aop() throws NoSuchMethodException {
        //给我一个正则,这个对象提供验证类或者方法是否符合包里面的对象(代表的是UserService接口的随意方法的运行)，替代了验证方法
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* step_11.event.UserService.*(..))");

        Class<UserService> clazz = UserService.class;

        Method queryUserInfo = clazz.getDeclaredMethod("queryUserInfo");


        System.out.println(pointcut.matches(clazz));

        System.out.println(pointcut.matches(queryUserInfo, clazz));

        // true、true
        }

    /**
     * 实现面向切面的方法，代理方法
     */
    @Test
    public void test_proxy_method()throws NoSuchMethodException{
        Object targetObject = new UserService();
        //Aop代理(代理目标实现的两个方法)Proxy的作用是当你使用IUserService的方法时会自动调用invoke(对于每个方法都适用)
        IUserService iUserService =(IUserService)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), targetObject.getClass().getInterfaces(), new InvocationHandler() {
            //方法匹配器(存储你需要验证的方法，包括接口,)
         AspectJExpressionPointcut pointcut= new AspectJExpressionPointcut("execution(* step_11.event.IUserService.*(..))");

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //判断调用的方法是不是需要拦截的方法
                if (pointcut.matches(method, targetObject.getClass())) {
                    System.out.println("符合条件，执行调用");
                    // 方法拦截器

                    MethodInterceptor methodInterceptor = invocation -> {
                        long start = System.currentTimeMillis();
                        try {
                            return invocation.proceed();
                        } finally {
                            System.out.println("监控 - Begin By AOP");
                            System.out.println("方法名称：" + invocation.getMethod().getName());
                            System.out.println("方法耗时：" + (System.currentTimeMillis() - start) + "ms");
                            System.out.println("监控 - End\r\n");
                        }
                    };
                    // 反射调用,内部实际上还是method.invoke(targetObject,args);
                    return methodInterceptor.invoke(new ReflectiveMethodInvocation(targetObject, method, args));
                }
                //正常调用
                return method.invoke(targetObject,args);
            }
        });
        //测试代理的方法是否触发invoke
        System.out.println(iUserService.queryUserInfo());
    }
    @Test
    public void test_proxy(){
        IUserService iUserService = new UserService();
        //组装的对象
        AdvisedSupport advisedSupport = new AdvisedSupport();
        //代理的目标
        advisedSupport.setTargetSource(new TargetSource(iUserService));
        //设置方法匹配器的范围
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* step_11.event.IUserService.*(..))"));
        //设置方法拦截器(额外执行的一些动作)
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        JdkDynamicAopProxy targetProxy = new JdkDynamicAopProxy(advisedSupport);
        //获取代理之后的对象
        IUserService iUserService1 = (IUserService) targetProxy.getProxy();
        System.out.println(iUserService1.queryUserInfo());

    }
    }


