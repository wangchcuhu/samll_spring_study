package step_09.beans.factory.support;

import step_09.beans.BeansException;
import step_09.beans.factory.FactoryBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: small_spring_study
 * @description:
 * @author: Z-Man
 * @create: 2022-01-14 09:36
 */
public class FactoryBeanRegisterSupport extends DefaultSingletonBeanRegistry{
    private final Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>();
    /**
     * 从池子中去取数据
     */
    protected Object getCacheObjectForFactoryBean(String beanName) {
        Object object = factoryBeanObjectCache.get(beanName);
        //这里的NULL_OBJECT指的是new Object();
        return (object != NULL_OBJECT? object : null);
    }

    /**
       现根据factory中判断是否是单例，是单例的话先从池子里取，
     没有的话先生成再存再取，不是单例直接生成返回，跟getBean的模式差不多
     */
    protected Object getCacheObjectForFactoryBean(FactoryBean factory,String beanName) {
        if (factory.isSingleton()) {
            Object object = this.factoryBeanObjectCache.get(beanName);
            if (object == null) {
                object = doGetObjectFromFactoryBean(factory, beanName);
                this.factoryBeanObjectCache.put(beanName, (object != null ? object : NULL_OBJECT));
            }
            return (object != NULL_OBJECT ? object : null);
        } else {
            return doGetObjectFromFactoryBean(factory, beanName);
        }
    }

    /**
     *     获取FactoryBean中的对象(),这个接口时真正获取beans池子中继承了FactoryBean这个接口的对象，目的是为了
     *     扩者spring这个框架，本层的目的是为了处理这种类型的bean,他的目的仅仅是为了生成FactoryBean，然后根据是否是
     *     单例来看是不是存在factoryBeanObjectCache这个池子里面，他的角色有点像是DefinitionBean的角色
     */
    private Object doGetObjectFromFactoryBean(final FactoryBean factory, final String beanName){
        try {
            Object object = factory.getObject();
            return object;
        } catch (Exception e) {
            throw new BeansException("FactoryBean threw exception on object[" + beanName + "] creation", e);
        }
    }
}
