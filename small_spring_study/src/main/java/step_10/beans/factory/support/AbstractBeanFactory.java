package step_10.beans.factory.support;

import step_03.BeansException;
import step_10.beans.factory.FactoryBean;
import step_10.beans.factory.config.BeanDefinition;
import step_10.beans.factory.config.BeanPostProcessor;
import step_10.beans.factory.config.ConfigurableBeanFactory;
import step_10.utils.ClassUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 *
 * BeanDefinition 注册表接口
 */
public abstract class AbstractBeanFactory extends FactoryBeanRegisterSupport implements ConfigurableBeanFactory {
    /** ClassLoader to resolve bean class names with, if necessary */
    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();
    /** BeanPostProcessors to apply in createBean */
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();

    //这里是注册Factory,先生成FactoryBean的实例，再放到实际存的地方FactoryBeanRegisterSupport中的factoryBeanObjectCache中
    protected <T> T doGetBean(final String name, final Object[] args) {
          //先去取再去存
        Object sharedInstance = getSingleton(name);
        if (sharedInstance != null) {
            return (T) getObjectForBeanInstance(sharedInstance, name);
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        Object bean =createBean(name,beanDefinition,args);
            return (T) getObjectForBeanInstance(bean, name);
    }
    protected <T> T doGetBean(final String name) {
        //先去取再去存
        Object sharedInstance = getSingleton(name);
        if (sharedInstance != null) {
            return (T) getObjectForBeanInstance(sharedInstance, name);
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        Object bean =createBean(name,beanDefinition);
        return (T) getObjectForBeanInstance(bean, name);
    }
    /**
     *     doGetBean会在内部将bean和FactoryBean的生成区分开来，在这个方法是内部生成FactoryBean的地方
     *     实际存的地方FactoryBeanRegisterSupport中的factoryBeanObjectCache中
     */
    private Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        if (!(beanInstance instanceof FactoryBean)) {
            return beanInstance;
        }
        Object object = getCacheObjectForFactoryBean(beanName);

        if (object == null) {
            FactoryBean<?> factoryBean = (FactoryBean<?>) beanInstance;
            object = getCacheObjectForFactoryBean(factoryBean, beanName);
        }

        return object;
    }

    /**
     *这里包裹一下，除了判断一下要返回池子里的数据，也可能是返回代理的数据，这里包裹一下
     */
    @Override
    public Object getBean(String name) throws BeansException {
        return   doGetBean(name);
//        Object bean = getSingleton(name);
//        if (bean != null) {
//            return bean;
//        }
//        BeanDefinition beanDefinition = getBeanDefinition(name);
//        return createBean(name, beanDefinition);

    }

    /**
     *这里包裹一下，除了判断一下要返回池子里的数据，也可能是返回代理的数据，这里包裹一下
     */
    @Override
    public Object getBean(String name, Object... args) {

        return  doGetBean(name, args);
//        Object bean = getSingleton(name);
//        if (bean != null) {
//            return bean;
//        }
//        BeanDefinition beanDefinition = getBeanDefinition(name);
//        return createBean(name, beanDefinition,args);
    }
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;
    //传入参数构建
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;


    /**
     * 存储BeanPostProcessor
     * @param beanPostProcessor
     */

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor){
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }
    /**
     * Return the list of BeanPostProcessors that will get applied
     * to beans created with this factory.
     */
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader;
    }
}
