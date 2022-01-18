package step_09.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import step_06.beans.BeansException;
import step_09.beans.factory.DisposableBean;
import step_09.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * @program: small_spring_study
 * @description:
 * @author: Z-Man
 * @create: 2022-01-13 10:28
 */
public class DisposableBeanAdapter implements DisposableBean {
    private String beanName;
    private Object bean;
    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName,   BeanDefinition beanDefinition) {
        this.beanName = beanName;
        this.bean = bean;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }
    //销毁的方法
    @Override
    public void destroy() throws Exception {
        // 1. 实现接口 DisposableBean
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }

        // 2. 注解配置 destroy-method {判断是为了避免二次执行销毁}
        if (StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))) {
            Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
            if (null == destroyMethod) {
                throw new BeansException("Couldn't find a destroy method named '" + destroyMethodName + "' on bean with name '" + beanName + "'");
            }
            destroyMethod.invoke(bean);
        }

    }
}
