package step_10.context.event;


import step_10.beans.BeansException;
import step_10.beans.factory.BeanFactory;
import step_10.beans.factory.BeanFactoryAware;
import step_10.context.ApplicationEvent;
import step_10.context.ApplicationListener;
import step_10.utils.ClassUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @program: small_spring_study
 * @description: 事件监听主体
 * @author: Z-Man
 * @create: 2022-01-19 10:11
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {
    private final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new LinkedHashSet<>();
    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void addApplicationListener(ApplicationListener<?> applicationListener) {
        applicationListeners.add((ApplicationListener<ApplicationEvent>) applicationListener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> applicationListener) {
        applicationListeners.remove((ApplicationListener<ApplicationEvent>) applicationListener);

    }

    //获取监听者的集合
    protected Collection<ApplicationListener> getApplicationListeners(ApplicationEvent event) {
        LinkedList<ApplicationListener> allListeners = new LinkedList<>();
        for (ApplicationListener<ApplicationEvent> applicationListener : applicationListeners) {
               //这里需要一个功能就是判断一个事件event是否属于一个listener
            if (support(applicationListener, event)) {
                allListeners.add(applicationListener);
            }
        }
        return allListeners;
    }

    protected  boolean support(ApplicationListener<ApplicationEvent> applicationListener, ApplicationEvent event){
        Class<? extends ApplicationListener> listenerClass = applicationListener.getClass();
        //需要判断监听者是以什么方式实例化的
        Class<?> targetClass = ClassUtils.isCglibProxyClass(listenerClass) ? listenerClass.getSuperclass() : listenerClass;
        //返回表示由该对象表示的类或接口直接实现的接口的类型就是ApplicationListener类型
        Type genericInterface = targetClass.getGenericInterfaces()[0];
        System.out.println(genericInterface);
        //返回实际的返回表示此类型的实际类型参数的 Type 对象数组。
        Type actualTypeArgument = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
        System.out.println(actualTypeArgument);
        String className = actualTypeArgument.getTypeName();
        Class<?>eventClassName ;
        try { eventClassName = Class.forName(className); }
        catch (ClassNotFoundException e) { throw new BeansException("wrong event class name: " + className); }
        // 判定此 eventClassName 对象所表示的类或接口与指定的 event.getClass() 参数所表 示的类或接口是否相同，
        // 或是否是其超类或超接口。 // isAssignableFrom 是用来判断子类和父类的关系的，或者接口的实现类和接口的关系的，
        // 默认所有的类的终极父类都是 Object。如果 A.isAssignableFrom(B)结果是 true，证明 B 可以转换成 为 A,
        // 也就是 A 可以由 B 转换而来。 return eventClassName.isAssignableFrom(event.getClass());
        return eventClassName.isAssignableFrom(event.getClass());
    };

}
