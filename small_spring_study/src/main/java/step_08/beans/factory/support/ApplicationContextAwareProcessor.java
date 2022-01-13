package step_08.beans.factory.support;

import step_08.beans.BeansException;
import step_08.beans.factory.config.BeanPostProcessor;
import step_08.context.ApplicationContext;
import step_08.context.ApplicationContextWare;

/**
 为了在bean创建的时候可以获取到ApplicationContext,所以包装一下，refresh()调用的时候将上下文存到对象中方便调用
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {
    private ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextWare){
            ((ApplicationContextWare) bean).setApplicationContext(this.applicationContext);
        }
        //这里会获取存在池子里的bean(继承了BeanPostProcessor的bean以及手动添加的本类)
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
