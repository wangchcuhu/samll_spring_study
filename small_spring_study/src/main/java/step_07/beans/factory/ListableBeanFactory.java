package step_07.beans.factory;


import java.util.Map;

/**
 //想要的功能是可以枚举所有的beans实例
 */

public interface ListableBeanFactory extends BeanFactory {
    // 按照类型返回Bean的实例
    <T> Map<String, T> getBeansOfType(Class<T> type);

    //返回注册表中的所有Bean的名称
    String[] getBeanDefinitionNames();
}
