package com.valyn.springframework.beans.factory.xml;

import com.valyn.springframework.beans.BeansException;
import com.valyn.springframework.beans.PropertyValue;
import com.valyn.springframework.beans.factory.config.BeanDefinition;
import com.valyn.springframework.beans.factory.config.BeanReference;
import com.valyn.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import com.valyn.springframework.beans.factory.support.BeanDefinitionRegistry;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
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
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    /**
     * 使用给定的 Bean 定义注册表创建一个新的 XmlBeanDefinitionReader 对象。
     *
     * @param registry Bean 定义注册表
     */
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    /**
     * 使用给定的 Bean 定义注册表和资源加载器创建一个新的 XmlBeanDefinitionReader 对象。
     *
     * @param registry       Bean 定义注册表
     * @param resourceLoader 资源加载器
     */
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    /**
     * 从给定的资源中加载 Bean 定义。
     *
     * @param resource 资源对象
     * @throws BeansException 如果解析 XML 文档出错，抛出该异常
     */
    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try (InputStream inputStream = resource.getInputStream()) {
            doLoadBeanDefinitions(inputStream);
        } catch (IOException | ClassNotFoundException e) {
            throw new BeansException("IOException parsing XML document from " + resource, e);
        }
    }

    /**
     * 从给定的多个资源中加载 Bean 定义。
     *
     * @param resources 多个资源对象
     * @throws BeansException 如果解析 XML 文档出错，抛出该异常
     */
    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    /**
     * 根据给定的位置加载 Bean 定义。
     *
     * @param location 位置（资源路径）
     * @throws BeansException 如果解析 XML 文档出错，抛出该异常
     */
    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    /**
     * 执行加载 Bean 定义的实际操作。
     *
     * @param inputStream 输入流
     * @throws ClassNotFoundException 如果找不到指定的类，抛出该异常
     */
    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException {
        // 使用 XmlUtil 工具类读取输入流生成 Document 对象
        Document doc = XmlUtil.readXML(inputStream);
        // 获取根元素
        Element root = doc.getDocumentElement();
        // 获取所有子节点
        NodeList childNodes = root.getChildNodes();

        // 遍历子节点
        for (int i = 0; i < childNodes.getLength(); i++) {
            // 判断是否为元素节点
            if (!(childNodes.item(i) instanceof Element)) continue;
            // 判断节点名称是否为 "bean"
            if (!"bean".equals(childNodes.item(i).getNodeName())) continue;

            // 解析 bean 标签
            Element bean = (Element) childNodes.item(i);
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");
            // 获取 Class 对象，方便获取类中的名称
            Class<?> clazz = Class.forName(className);
            // 根据 id 或 name 确定 bean 的名称
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if (StrUtil.isEmpty(beanName)) {
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }

            // 创建 BeanDefinition 对象
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            // 读取属性并填充到 BeanDefinition 中
            for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                if (!(bean.getChildNodes().item(j) instanceof Element)) continue;
                if (!"property".equals(bean.getChildNodes().item(j).getNodeName())) continue;
                // 解析 property 标签
                Element property = (Element) bean.getChildNodes().item(j);
                String attrName = property.getAttribute("name");
                String attrValue = property.getAttribute("value");
                String attrRef = property.getAttribute("ref");
                // 根据属性值类型，创建对应的属性值对象
                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
                // 创建 PropertyValue 对象
                PropertyValue propertyValue = new PropertyValue(attrName, value);
                // 将 PropertyValue 添加到 BeanDefinition 的属性列表中
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            // 检查是否已存在相同名称的 BeanDefinition
            if (getRegistry().containsBeanDefinition(beanName)) {
                throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
            }
            // 注册 BeanDefinition
            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }
    }
}
