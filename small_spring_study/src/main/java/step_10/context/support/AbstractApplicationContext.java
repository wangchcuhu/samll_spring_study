package step_10.context.support;

import cn.hutool.core.bean.BeanException;
import step_10.beans.BeansException;
import step_10.beans.factory.config.BeanFactoryPostProcessor;
import step_10.beans.factory.config.BeanPostProcessor;
import step_10.beans.factory.support.ApplicationContextAwareProcessor;
import step_10.beans.factory.support.DefaultListableBeanFactory;
import step_10.context.ConfigurableApplication;

import java.util.Map;

/**
 * @program: small_spring_study
 * @description:抽象应用上下文（引用上下文的基础模板）----控制整个程序的运行
 * @author: Z-Man
 * @create: 2022-01-12 08:38
 */
public abstract class AbstractApplicationContext implements ConfigurableApplication {
    //这里有点像是生命周期的概念
    @Override
    public void refresh() throws BeanException {
        //1.创建BeanFactory，并且加载BeanDefinition
        refreshBeanFactory();

        //2.获取BeanFactory
        DefaultListableBeanFactory beanFactory=getBeanFactory();

        //3.添加ApplicationContextAwareProcessor,让继承字ApplicationContextAwareProcessor，让继承 自 ApplicationContextAware 的 Bean 对象都能感知所属的 ApplicationContext
         beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        //4.在Bean 实例化之前，执行 BeanFactoryPostProcessor（调用在上下文中注册为bean的工厂处理器）
        invokeBeanFactoryPostProcessors(beanFactory);

        //5.BeanPostProcessor 需要提前于其他 Bean 对象实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);

        //6.提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();
    }

    @Override
    public void registerShutDownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }
     //关闭的时候自动调用池子中的销毁方法
    @Override
    public void close() {
        getBeanFactory().destroySingletons();
    }
    //凡是继承了BeanPostProcessor这个接口的bean都会被存到beanPostProcessors(List)中
    protected  void registerBeanPostProcessors(DefaultListableBeanFactory beanFactory){
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        //添加到BeanPostProcessor的list中
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);}
    };
    //beanFactory前置中间件调用的地方(实际上是从池子里面取我们准备好的bean,然后传入beanFactory)
    protected  void invokeBeanFactoryPostProcessors(DefaultListableBeanFactory beanFactory){
        //取所有的Bean
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        //便利执行
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);}
    }

    protected abstract DefaultListableBeanFactory getBeanFactory();

    protected abstract void refreshBeanFactory();

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }
    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }
    //上下文的getBean是代理的工厂的getBeanFactory,所以factory的this指向还是指向自生
    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

//    @Override
//    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
//        return getBeanFactory().getBean(name, requiredType);
//    }
}
