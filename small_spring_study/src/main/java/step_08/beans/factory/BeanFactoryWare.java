package step_08.beans.factory;


import step_08.beans.factory.BeanFactory;

//感知BeanFactory
public interface BeanFactoryWare extends Aware{
    public void setBeanFactory(BeanFactory beanFactory);
}