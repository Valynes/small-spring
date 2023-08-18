package com.valyn.springframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.valyn.springframework.beans.BeansException;
import com.valyn.springframework.beans.PropertyValue;
import com.valyn.springframework.beans.factory.config.BeanDefinition;
import com.valyn.springframework.beans.factory.config.BeanReference;
import com.valyn.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import com.valyn.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.valyn.springframework.core.io.Resource;
import com.valyn.springframework.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

/**
 * XmlBeanDefinitionReader 类是从 XML 文件中读取 Bean 定义的类。
 * 它继承自 AbstractBeanDefinitionReader 抽象类，实现了加载 Bean 定义的方法。
 */
/**
 * 从 XML 文件中读取 Bean 定义的读取器。继承了抽象类 AbstractBeanDefinitionReader。
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    /**
     * 使用给定的 Bean 定义注册表构造一个 XmlBeanDefinitionReader 对象。
     *
     * @param registry Bean 定义注册表，用于注册解析出的 Bean 定义。
     */
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    /**
     * 使用给定的 Bean 定义注册表和资源加载器构造一个 XmlBeanDefinitionReader 对象。
     *
     * @param registry       Bean 定义注册表，用于注册解析出的 Bean 定义。
     * @param resourceLoader 资源加载器，用于获取 Bean 定义所在的资源。
     */
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    /**
     * 从指定的资源中加载 Bean 定义。
     *
     * @param resource 要加载的资源。
     * @throws BeansException 如果解析 XML 文档出现异常，则抛出此异常。
     */
    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            try (InputStream inputStream = resource.getInputStream()) {
                doLoadBeanDefinitions(inputStream);
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new BeansException("IOException parsing XML document from " + resource, e);
        }
    }

    /**
     * 从多个资源中加载 Bean 定义。
     *
     * @param resources 要加载的多个资源。
     * @throws BeansException 如果解析 XML 文档出现异常，则抛出此异常。
     */
    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    /**
     * 从指定的位置加载 Bean 定义。
     *
     * @param location 要加载的位置。
     * @throws BeansException 如果解析 XML 文档出现异常，则抛出此异常。
     */
    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    /**
     * 从多个位置加载 Bean 定义。
     *
     * @param locations 要加载的多个位置。
     * @throws BeansException 如果解析 XML 文档出现异常，则抛出此异常。
     */
    @Override
    public void loadBeanDefinitions(String... locations) throws BeansException {
        for (String location : locations) {
            loadBeanDefinitions(location);
        }
    }

    /**
     * 实际执行从输入流中加载 Bean 定义的操作。
     *
     * @param inputStream 包含 Bean 定义的输入流。
     * @throws ClassNotFoundException 如果无法找到指定类的定义，则抛出此异常。
     */
    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException {
        // 解析 XML 文档，并获取根节点和子节点列表
        Document doc = XmlUtil.readXML(inputStream);
        Element root = doc.getDocumentElement();
        NodeList childNodes = root.getChildNodes();

        // 遍历子节点，解析并注册 BeanDefinition
        for (int i = 0; i < childNodes.getLength(); i++) {
            // 判断元素是否为 Element 类型
            if (!(childNodes.item(i) instanceof Element)) continue;
            Element element = (Element) childNodes.item(i);

            // 判断节点名称是否为 "bean"
            if (!"bean".equals(element.getNodeName())) continue;

            // 解析 bean 标签的属性
            String id = element.getAttribute("id");
            String name = element.getAttribute("name");
            String className = element.getAttribute("class");

            // 获取 Class 对象
            Class<?> clazz = Class.forName(className);

            // 根据 id 和 name 进行 Bean 名称的确定
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if (StrUtil.isEmpty(beanName)) {
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }

            // 创建 BeanDefinition 对象
            BeanDefinition beanDefinition = new BeanDefinition(clazz);

            // 解析 bean 标签的子节点，即 <property> 标签
            NodeList propertyNodes = element.getChildNodes();
            for (int j = 0; j < propertyNodes.getLength(); j++) {
                // 判断元素是否为 Element 类型
                if (!(propertyNodes.item(j) instanceof Element)) continue;
                Element propertyElement = (Element) propertyNodes.item(j);

                // 判断节点名称是否为 "property"
                if (!"property".equals(propertyElement.getNodeName())) continue;

                // 解析 property 标签的属性
                String attrName = propertyElement.getAttribute("name");
                String attrValue = propertyElement.getAttribute("value");
                String attrRef = propertyElement.getAttribute("ref");

                // 根据属性值的类型创建 PropertyValue 对象
                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
                PropertyValue propertyValue = new PropertyValue(attrName, value);

                // 将 PropertyValue 添加到 BeanDefinition 的属性列表中
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }

            // 检查注册表中是否已存在同名的 BeanDefinition
            if (getRegistry().containsBeanDefinition(beanName)) {
                throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
            }

            // 注册 BeanDefinition
            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }
    }
}
