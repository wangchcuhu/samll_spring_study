package step_06.beans.factory.support;

import step_06.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public interface instantiationStrategy {
    //使用构造函数创建对象
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args);
}
