package step_03.factory.support;

import step_03.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public interface instantiationStrategy {
    //使用构造函数创建对象
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor,Object[] args);
}
