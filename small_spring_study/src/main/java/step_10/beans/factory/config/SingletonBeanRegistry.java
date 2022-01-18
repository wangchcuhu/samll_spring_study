package step_10.beans.factory.config;

/**
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 *
 * 单例注册表
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

}
