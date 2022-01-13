package step_07.io;

//统一加载资源
public interface ResourcesLoader {
    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getRecourse(String location);

}
