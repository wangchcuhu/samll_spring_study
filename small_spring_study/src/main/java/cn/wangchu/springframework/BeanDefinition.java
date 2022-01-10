package cn.wangchu.springframework;
//初步猜测这个bean因该是会被所有的bean继承
public class BeanDefinition {
    private Object bean;

    public Object getBean() {
        return bean;
    }

    public  BeanDefinition(Object bean) {
        this.bean = bean;
    }
}
