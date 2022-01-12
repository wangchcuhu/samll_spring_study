package step_06.common;

import step_06.beans.BeansException;
import step_06.beans.PropertyValue;
import step_06.beans.PropertyValues;
import step_06.beans.factory.config.BeanDefinition;
import step_06.beans.factory.config.BeanFactoryPostProcessor;
import step_06.beans.factory.config.BeanPostProcessor;
import step_06.beans.factory.support.DefaultListableBeanFactory;

/**
 * @program: small_spring_study
 * @description:
 * @author: Z-Man
 * @create: 2022-01-12 14:31
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(DefaultListableBeanFactory beanFactory) throws BeansException {
        //在这一步骤已经已经在beanFactory中加载了BeanDefinition(可以提前放属性信息进去)--这之前已经将xml中的信息读取，属性已经存了起来
        //如果像ref这种的就用对象BeanReference包裹起来，等到真正的实例化对象的时候判断一下再从池子里面取出已经实例化的对象，所以ref的值一定要放在
        //xml中调用之前
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId","改为字节跳动"));
    }
}
