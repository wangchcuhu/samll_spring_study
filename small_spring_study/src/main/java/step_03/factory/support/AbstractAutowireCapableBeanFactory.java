package step_03.factory.support;


import step_03.BeansException;
import step_03.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    private SimpleInstantiationStrategyStrategy simpleInstantiationStrategyStrategy = new SimpleInstantiationStrategyStrategy();

    public SimpleInstantiationStrategyStrategy getSimpleInstantiationStrategyStrategy() {
        return simpleInstantiationStrategyStrategy;
    }

    public void setSimpleInstantiationStrategyStrategy(SimpleInstantiationStrategyStrategy simpleInstantiationStrategyStrategy) {
        this.simpleInstantiationStrategyStrategy = simpleInstantiationStrategyStrategy;
    }

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean;
        try {
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        addSingleton(beanName, bean);
        return bean;
    }
    //多参数的时候创建对象
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean;
        Constructor ctor = null;
        try {
            Constructor<?>[] constructor =beanDefinition.getBeanClass().getDeclaredConstructors();
            for (Constructor constructor1 : constructor) {
                //这里只对比了参数的数量的不同，实际上还需要更多的对比
                if (constructor1.getParameterTypes().length == args.length) {
                    ctor=constructor1;
                }

            }
            bean = simpleInstantiationStrategyStrategy.instantiate(beanDefinition, beanName,ctor,args);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        addSingleton(beanName, bean);
        return bean;
    }
}
