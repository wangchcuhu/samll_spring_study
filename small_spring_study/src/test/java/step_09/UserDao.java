package step_09;

import step_07.beans.factory.DisposableBean;
import step_07.beans.factory.InitializingBean;
import step_08.beans.BeansException;
import step_08.beans.factory.config.BeanPostProcessor;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: small_spring_study
 * @description:
 * @author: Z-Man
 * @create: 2022-01-08 15:22
 */
public class UserDao implements InitializingBean, DisposableBean, BeanPostProcessor,IUserDao {
    private static Map<String, String> map = new HashMap<>();

    static {
        map.put("10001", "小傅哥");
        map.put("10002", "八杯水");
        map.put("10003", "阿毛");
    }

    public String queryUserName(String uID) {
        return map.get(uID);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("UserDao初始化操作--实现方式继承接口InitializingBean");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("执行：UserDao.destroy");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("UserDao 的前置处理"+beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("UserDao 的后置处理"+beanName);
        return bean;
    }
}
