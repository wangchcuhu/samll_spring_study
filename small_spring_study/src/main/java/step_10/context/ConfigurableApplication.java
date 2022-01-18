package step_10.context;

import cn.hutool.core.bean.BeanException;

//添加刷新功能
public interface ConfigurableApplication extends ApplicationContext {
    //刷新功能
    void refresh() throws BeanException;

    void registerShutDownHook();

    void close();
}
