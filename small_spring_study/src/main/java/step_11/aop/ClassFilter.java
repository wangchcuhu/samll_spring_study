package step_11.aop;
//类过滤
public interface ClassFilter {
    boolean matches(Class<?> clazz);
}
