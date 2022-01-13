package step_07.beans.factory;

/**
 * 继承这个接口的bean都可以在bean初始化时候调用
 */
public interface InitializingBean {
    /**
     * Bean 处理了属性填充后调用
     *
     * @throws Exception
     */
    void afterPropertiesSet() throws Exception;

}
