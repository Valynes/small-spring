package com.valyn.springframework.core.io;

/**
 * ResourceLoader 接口定义了获取资源的方法。
 */
public interface ResourceLoader {

    /**
     * 用于从类路径加载资源的伪URL前缀： "classpath:"
     */
    String CLASSPATH_URL_PREFIX = "classpath:";

    /**
     * 获取给定位置的资源。
     *
     * @param location 资源的位置
     * @return 表示资源的对象
     */
    Resource getResource(String location);

}

