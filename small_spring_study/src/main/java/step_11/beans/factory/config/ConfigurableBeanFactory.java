package step_11.beans.factory.config;


import step_11.beans.factory.HierarchicalBeanFactory;

/**
 大多数bean工厂要实现的配置接口。 提供
 * 配置 bean 工厂的设施，除了 bean 工厂
 * 中的客户端方法
 * 界面。
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁单例对象
     */
    void destroySingletons();
}
