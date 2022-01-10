package cn.wangchu.springframework;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//处理bean
public class BeanFactory {
    //存储bean使用的是HashMap(这里就当做一种存储结构)
    private Map<String, BeanDefinition> beanDefinitionMap=new ConcurrentHashMap<>();
    //注册Bean,实际上就是把bean存进去
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
    }

    //取出bean
    public Object getBean(String name) {
        return beanDefinitionMap.get(name).getBean();
    }
}
