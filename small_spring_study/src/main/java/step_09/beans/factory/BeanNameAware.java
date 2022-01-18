package step_09.beans.factory;

public interface BeanNameAware extends Aware {
    void setBeanName(String beanName);
}
