package step_09.beans.factory;

import step_09.beans.BeansException;
import step_09.beans.factory.config.AutowireCapableBeanFactory;
import step_09.beans.factory.config.BeanDefinition;
import step_09.beans.factory.config.BeanPostProcessor;
import step_09.beans.factory.config.ConfigurableBeanFactory;

//可配置的工厂
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
