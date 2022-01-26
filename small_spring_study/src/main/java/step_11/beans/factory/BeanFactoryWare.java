package step_11.beans.factory;


//感知BeanFactory
public interface BeanFactoryWare extends Aware {
    public void setBeanFactory(BeanFactory beanFactory);
}