package step_07.io;

import cn.hutool.core.lang.Assert;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @program: small_spring_study
 * @description:这个资源处理器只能加载路径的（classPath和URL的资源）---不用让外部知道过多的细节，仅仅是调用最终的结果就可以了
 * @author: Z-Man
 * @create: 2022-01-10 10:44
 */
public class DefaultGetRecourse implements ResourcesLoader{
    @Override
    public Resource getRecourse(String location) {
        Assert.notNull(location, "Location cannot be null");
        //判断是不是pathClass加载
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        }else{
            //url
            try {
                URL url = new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException e) {
                return new FileSystemResource(location);
            }
        }
    }
}
