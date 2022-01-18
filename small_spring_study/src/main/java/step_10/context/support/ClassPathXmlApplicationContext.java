package step_10.context.support;

import step_10.beans.BeansException;
import step_10.beans.factory.support.DefaultListableBeanFactory;

/**
 * @program: small_spring_study
 * @description:
 * @author: Z-Man
 * @create: 2022-01-12 10:59
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {
    private String[] configLocations;

    public ClassPathXmlApplicationContext() {
    }

    /**
     * 从 XML 中加载 BeanDefinition，并刷新上下文
     *
     * @param configLocations
     * @throws BeansException
     */
    public ClassPathXmlApplicationContext(String configLocations) throws BeansException {
        this(new String[]{configLocations});
    }

    /**
     * 从 XML 中加载 BeanDefinition，并刷新上下文
     * @param configLocations
     * @throws BeansException
     */
    public ClassPathXmlApplicationContext(String[] configLocations) throws BeansException {
        this.configLocations = configLocations;
        refresh();
    }
    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }
    //这里朱错误的示范，只是为了验证BeanFactory是否争取，不应该将内部的BeanFactory这个对对象暴露出去
    @Override
    public DefaultListableBeanFactory getBeanFactory() {
        return super.getBeanFactory();
    }
}
