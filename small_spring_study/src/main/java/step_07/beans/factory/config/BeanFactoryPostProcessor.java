package step_07.beans.factory.config;

import step_07.beans.BeansException;
import step_07.beans.factory.support.DefaultListableBeanFactory;

//BeanFactory后置处理器
public interface BeanFactoryPostProcessor {
    //对传入的工厂对象进行处理
    void postProcessBeanFactory(DefaultListableBeanFactory beanFactory) throws BeansException;


}
