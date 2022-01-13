package step_05.bean;

import step_08.beans.BeansException;
import step_08.beans.factory.BeanClassLoaderAware;
import step_08.beans.factory.BeanFactory;
import step_08.beans.factory.BeanFactoryAware;
import step_08.beans.factory.BeanNameAware;
import step_08.context.ApplicationContext;
import step_08.context.ApplicationContextWare;

//查询用户信息的一个服务
public class UserService implements BeanFactoryAware, BeanClassLoaderAware, BeanNameAware ,ApplicationContextWare{
    private String uId;
    private UserDao userDao;
    private BeanFactory beanFactory;

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void queryUserInfo (){
        System.out.println("查询用户信息"+userDao.queryUserName(uId));
    }


    public void initDataMethod(){
        System.out.println("UserService的初始化");
    };
    public void destroyDataMethod(){
        System.out.println("UserService的销毁");
    };
    //可以获取内部的beanFactory资源
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("beanFactory 资源获取");
        //获取当前的beanFactory
        this.beanFactory=beanFactory;
    }

    @Override
    public void setBeanName(String beanName) {
        System.out.println("beanName 资源获取");
        //获取当前bean的Bean的名字
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("classLoader 资源获取");

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        System.out.println("applicationContext 资源获取");
    }
}
