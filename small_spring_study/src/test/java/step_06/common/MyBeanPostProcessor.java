package step_06.common;


import step_05.bean.UserService;
import step_06.beans.BeansException;
import step_06.beans.factory.config.BeanPostProcessor;
//此方法的生命周期是在将bean加到池子中之前调用
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if("userService".equals(beanName)){
            //在放入池子前做一些动作
            UserService userService = (UserService) bean;
            userService.setuId("改为北京");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
