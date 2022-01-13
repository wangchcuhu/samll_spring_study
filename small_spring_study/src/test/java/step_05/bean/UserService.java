package step_05.bean;

import step_08.beans.BeansException;
import step_08.beans.factory.BeanFactory;
import step_08.beans.factory.BeanFactoryAware;

//查询用户信息的一个服务
public class UserService implements BeanFactoryAware{
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
        //获取当前的beanFactory
        this.beanFactory=beanFactory;
    }
}
