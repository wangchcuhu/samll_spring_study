package step_05.factory.support;


import cn.hutool.core.bean.BeanUtil;
import step_03.BeansException;
import step_05.PropertyValue;
import step_05.PropertyValues;
import step_05.factory.config.BeanDefinition;
import step_05.factory.config.BeanReference;

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
        //是否包含属性
        if(beanDefinition.getPropertyValues()!=null){
            applyPropertyValues(beanName, bean, beanDefinition);
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
        //是否包含属性
        if(beanDefinition.getPropertyValues()!=null){
            applyPropertyValues(beanName, bean, beanDefinition);
        }
        addSingleton(beanName, bean);
        return bean;
    }

    //为Bean进行属性填充
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        //取出所有的属性
        PropertyValues propertyValuesList = beanDefinition.getPropertyValues();
        //对每个属性执行填充操作(在遍历的时候取到的值可以使null)
        for (PropertyValue propertyValue : propertyValuesList.getPropertyValue()) {
            String name = propertyValue.getName();
            Object value = propertyValue.getValue();
            //这里以该有个判断bean
            if ( value instanceof BeanReference) {
                // A 依赖 B，获取 B 的实例化
                BeanReference beanReference = (BeanReference) value;
                value = getBean(beanReference.getBeanName());
            }

            //添加属性
            BeanUtil.setFieldValue(bean, name, value);
        }
    }
}
