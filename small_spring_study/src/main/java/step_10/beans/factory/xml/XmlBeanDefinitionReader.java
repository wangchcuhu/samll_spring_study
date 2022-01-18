package step_10.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import step_03.BeansException;
import step_10.beans.PropertyValue;
import step_10.beans.PropertyValues;
import step_10.beans.factory.config.BeanDefinition;
import step_10.beans.factory.config.BeanReference;
import step_10.beans.factory.support.AbstractBeanDefinitionReader;
import step_10.beans.factory.support.BeanDefinitionRegistry;
import step_10.io.Resource;
import step_10.io.ResourcesLoader;

import java.io.IOException;
import java.io.InputStream;
//测试失败

/**
 * @program: small_spring_study
 * @description:
 * @author: Z-Man
 * @create: 2022-01-10 13:06
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {
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
        for (int i = 0; i < childNodes.getLength(); i++) {
            //判断元素
            if(!(childNodes.item(i) instanceof Element)) continue;
            //判断对象
            if(!"bean".equals(childNodes.item(i).getNodeName()))continue;

            //解析对象
            Element bean = (Element) childNodes.item(i);
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");
            String initMethodName = bean.getAttribute("destroy-method");
            String destroyMethodName = bean.getAttribute("destroy-method");
            String scope = bean.getAttribute("scope");

            // 获取 Class，方便获取类中的名称(根据类名直接获取Class)
            Class<?> clazz = Class.forName(className);
            // 优先级 id > name
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if (StrUtil.isEmpty(beanName)) {
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }
            //定义Bean(这里有个问题，如果是含有属性的初始化会怎么样？是在注入属性的时判断PropertyValue的时null)
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            //读取属性并且填充
            beanDefinition.setInitMethodName(initMethodName);
            beanDefinition.setDestroyMethodName(destroyMethodName);
            //设置该bean的域
            beanDefinition.setScope(scope);

            for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                //解析属性
                if (!(bean.getChildNodes().item(j) instanceof Element)) continue;
                if (!"property".equals(bean.getChildNodes().item(j).getNodeName())) continue;
                // 解析标签：property
                Element property = (Element) bean.getChildNodes().item(j);
                String attrName = property.getAttribute("name");
                String attrValue = property.getAttribute("value");
                String attrRef = property.getAttribute("ref");
                // 获取属性值：引入对象、值对象(这里有个问题userDao还是需要提前注入Bean中)
                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
                // 创建属性信息
                PropertyValue propertyValue = new PropertyValue(attrName, value);
                if (beanDefinition.getPropertyValues() == null) {
                    beanDefinition.setPropertyValues(new PropertyValues());
                }
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
                //判断这个bean是不是重复的
                if (getRegister().containsBeanDefinition(beanName)) {
                    throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
                }
            }
            // 注册 BeanDefinition
            getRegister().registerBeanDefinition(beanName, beanDefinition);
        }
    }
}
