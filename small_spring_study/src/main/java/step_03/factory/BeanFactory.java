package step_03.factory;


import step_03.BeansException;

/**
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 */
public interface BeanFactory {
    //默认使用无参构造函数
    Object getBean(String name) throws BeansException;

    //使用有参数的构造函数
    Object getBean(String name, Object... args);
}
