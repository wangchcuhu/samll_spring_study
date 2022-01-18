package step_09;

import step_09.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: small_spring_study
 * @description:
 * @author: 这里就相当于吧Definition的内容使用ProxyBeanFactory运用了，在内部也建立了对应的池子
 * @create: 2022-01-14 10:22
 */
public class ProxyBeanFactory implements FactoryBean<IUserDao>{
    @Override
    public IUserDao getObject() throws Exception {
        InvocationHandler handler=(proxy, method, args) -> {
            Map<String, String> hashMap = new HashMap<>();
            hashMap.put("10001", "小傅哥");
            hashMap.put("10002", "八杯水");
            hashMap.put("10003", "阿毛");
            return "你被代理了" + method.getName() + ":" + hashMap.get(args[0].toString());
        };
        //这里就会被存到FactoryBean的池子里面
        return (IUserDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IUserDao.class}, handler);

    }
    //实际上放在bean池子中用到的是这一步
    @Override
    public Class<?> getObjectType() {
        return IUserDao.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
