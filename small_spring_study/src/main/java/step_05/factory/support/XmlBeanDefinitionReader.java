package step_05.factory.support;

import cn.hutool.core.util.XmlUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import step_03.BeansException;
import step_05.core.io.Resource;
import step_05.core.io.ResourcesLoader;

import java.io.IOException;
import java.io.InputStream;
/**
 * @program: small_spring_study
 * @description:
 * @author: Z-Man
 * @create: 2022-01-10 13:06
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader{
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourcesLoader resourcesLoader) {
        super(registry, resourcesLoader);
    }
    //提供注册的对象
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        ResourcesLoader resourcesLoader = getResourcesLoader();
        Resource resource = resourcesLoader.getRecourse(location);
        loadBeanDefinitions(resource);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try { try (InputStream inputStream = resource.getInputStream())
        { doLoadBeanDefinitions(inputStream); } } catch (IOException |ClassNotFoundException e)
        { throw new BeansException("IOException parsing XML document from " + resource, e); }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }
    //具体处理xml文件的内容(到这一步就已经获取到输入的数据流了)
    private void doLoadBeanDefinitions(InputStream inputStream) throws  ClassNotFoundException{
        //通过xml获取Document对象
        Document doc = XmlUtil.readXML(inputStream);
        Element root = doc.getDocumentElement();
        NodeList childNodes = root.getChildNodes();

    }
}
