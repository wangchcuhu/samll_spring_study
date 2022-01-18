package step_09.utils;

/**
 * @program: small_spring_study
 * @description:
 * @author: Z-Man
 * @create: 2022-01-10 09:20
 */
public class ClassUtils {
    public static ClassLoader getDefaultClassLoader() {

        ClassLoader cl = null;
        try
        {
            //获取当前线程的类加载器,我们使用的对象都是通过这个生成的，如果当前线程没有就获取付父线程的类加载器
            cl = Thread.currentThread().getContextClassLoader();
        }catch(Throwable ex)
        {
            //Cannot access thread context ClassLoader -- failing back to system class Loader
        }
        if(cl==null){
            cl = ClassUtils.class.getClassLoader();
        }
        return cl;
    }


}
