package step_06.beans.factory;

import step_06.beans.BeansException;
import step_06.beans.factory.config.*;

//可配置的工厂
public interface ConfigurableListableBeanFactory extends ListableBeanFactory , AutowireCapableBeanFactory, ConfigurableBeanFactory {
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
