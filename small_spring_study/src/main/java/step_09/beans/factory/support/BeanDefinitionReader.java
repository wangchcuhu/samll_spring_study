package step_09.beans.factory.support;

import step_03.BeansException;
import step_09.io.Resource;
import step_09.io.ResourcesLoader;

//
public interface BeanDefinitionReader {
    //
    BeanDefinitionRegistry getRegister();

    //获取
    ResourcesLoader getResourcesLoader();

    //加载路径资源
    void loadBeanDefinitions(String location) throws BeansException;

    //直接是加载已经读取好的资源
    void loadBeanDefinitions(Resource resource) throws BeansException;

    //加载多个已经生成好的资源
    void loadBeanDefinitions(Resource...resources) throws BeansException;


}
