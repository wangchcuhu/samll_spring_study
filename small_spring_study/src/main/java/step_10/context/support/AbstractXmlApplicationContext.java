package step_10.context.support;

import step_10.beans.factory.support.DefaultListableBeanFactory;
import step_10.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @program: small_spring_study
 * @description:
 * @author: Z-Man
 * @create: 2022-01-12 10:51
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {
    //为传入的beanFactory装载xml配置信息
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String[] configLocations = getConfigLocations();
        if (null != configLocations){
            //加载xml等信息
//            这里应该是遍历加载
            for (String configLocation : configLocations) {
                beanDefinitionReader.loadBeanDefinitions(configLocation);
            }

        }
    }
    //这个返回的是xml文件的信息
    protected abstract String[] getConfigLocations();
}
