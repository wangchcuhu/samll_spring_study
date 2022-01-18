package step_09.beans.factory;


//感知BeanFactory
public interface BeanFactoryWare extends Aware {
    public void setBeanFactory(BeanFactory beanFactory);
}