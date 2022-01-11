package step_05.core.io;

import step_05.utils.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @program: small_spring_study
 * @description: get class resource（路径加载）
 * @author: Z-Man
 * @create: 2022-01-10 09:04
 */
 public class ClassPathResource implements Resource{

     private final String path;

     private ClassLoader classLoader;

     public ClassPathResource(String path) {
         //使用内部的构造函数
         this(path, (ClassLoader) null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        this.path = path;
        //获取类加载器
        this.classLoader = (classLoader != null) ? classLoader : ClassUtils.getDefaultClassLoader();
    }
    //使用本地路径加载资源
    @Override
    public InputStream getInputStream() throws IOException {
        //使用类加载器
        InputStream is = classLoader.getResourceAsStream(path);
        if(is==null){
            throw new FileNotFoundException(this.path);
        }
        return is;
    }
}
