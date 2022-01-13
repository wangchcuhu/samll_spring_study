package step_07.beans.factory.support;

import step_07.io.DefaultGetRecourse;
import step_07.io.ResourcesLoader;

/**
 * @program: small_spring_study
 * @description:扩展抽象的读取器
 * @author: Z-Man
 * @create: 2022-01-10 13:01
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {
    //注册的地方
    private final BeanDefinitionRegistry registry;
    //资源加载的工具(没有就会有个默认的实现)
    private ResourcesLoader resourcesLoader;

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourcesLoader resourcesLoader) {
        this.registry = registry;
        this.resourcesLoader = resourcesLoader;
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultGetRecourse());
    }

    @Override
    public BeanDefinitionRegistry getRegister() {
        return registry;
    }

    @Override
    public ResourcesLoader getResourcesLoader() {
        return resourcesLoader;
    }
}
