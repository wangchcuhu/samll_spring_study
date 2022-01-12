package step_06.beans.factory.config;

import step_06.beans.BeansException;
import step_06.beans.factory.BeanFactory;
import step_06.beans.factory.support.DefaultListableBeanFactory;

//BeanFactory后置处理器
public interface BeanFactoryPostProcessor {
    //对传入的工厂对象进行处理
    void postProcessBeanFactory(DefaultListableBeanFactory beanFactory) throws BeansException;


}
