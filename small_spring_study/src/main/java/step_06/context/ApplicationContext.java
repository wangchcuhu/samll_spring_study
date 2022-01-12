package step_06.context;


import step_06.beans.factory.ListableBeanFactory;

//  为应用程序提供配置的中央接口。
//  * 这在应用程序运行时是只读的，但可能是
//  * 如果实现支持，则重新加载。
public interface ApplicationContext extends ListableBeanFactory {
}
