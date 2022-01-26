package step_11.context.support;

import cn.hutool.core.bean.BeanException;
import step_11.beans.BeansException;
import step_11.beans.factory.ConfigurableListableBeanFactory;
import step_11.beans.factory.config.BeanFactoryPostProcessor;
import step_11.beans.factory.config.BeanPostProcessor;
import step_11.beans.factory.support.ApplicationContextAwareProcessor;
import step_11.beans.factory.support.DefaultListableBeanFactory;
import step_11.context.ApplicationEvent;
import step_11.context.ApplicationListener;
import step_11.context.ConfigurableApplication;
import step_11.context.event.ApplicationEventMulticaster;
import step_11.context.event.ContextClosedEvent;
import step_11.context.event.ContextRefreshedEvent;

import java.util.Collection;
import java.util.Map;

/**
 * @program: small_spring_study
 * @description:抽象应用上下文（引用上下文的基础模板）----控制整个程序的运行
 * @author: Z-Man
 * @create: 2022-01-12 08:38
 */
public abstract class AbstractApplicationContext implements ConfigurableApplication {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    //观测者是上下文里面的变量，并且还放在了beans的池子里面了
    private ApplicationEventMulticaster applicationEventMulticaster;
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

        // 6. 初始化事件发布者
        initApplicationEventMulticaster();

        // 7. 注册事件监听器
        registerListeners();

        //8.提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();

        // 9. 发布容器刷新完成事件
        finishRefresh();
    }
    // 9. 发布容器刷新完成事件
    protected  void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }

    /**
      当有人触发这个方法传递寄来事件，就会触发对应的处理的listener的bean
     */
    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }
    // 7. 注册事件监听器
    protected  void registerListeners() {
        //从池子里面将所有继承了ApplicationListener这个接口的对象取出来
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener applicationListener : applicationListeners) {
            applicationEventMulticaster.addApplicationListener(applicationListener);
        }
    }

    // 6. 初始化事件发布者
    protected  void initApplicationEventMulticaster(){
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        //直接向bean池子里面放事件监听对象
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
    };

    @Override
    public void registerShutDownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }
     //关闭的时候自动调用池子中的销毁方法
    @Override
    public void close() {
        // 发布容器关闭事件
        publishEvent(new ContextClosedEvent(this));

        // 执行销毁单例bean的销毁方法
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
