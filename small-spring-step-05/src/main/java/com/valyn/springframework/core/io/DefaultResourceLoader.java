package com.valyn.springframework.core.io;

import cn.hutool.core.lang.Assert;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * DefaultResourceLoader 类实现了 ResourceLoader 接口，用于加载资源。
 */
public class DefaultResourceLoader implements ResourceLoader {

    /**
     * 根据给定的资源位置返回对应的 Resource 对象。
     *
     * @param location 资源的位置
     * @return 对应的 Resource 对象
     */
    @Override
    public Resource getResource(String location) {
        Assert.notNull(location, "Location must not be null");

        // 如果资源位置以 "classpath:" 开头，则创建 ClassPathResource 对象
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        }
        else {
            try {
                // 尝试将资源位置解析为 URL，并创建 UrlResource 对象
                URL url = new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException e) {
                // 如果解析 URL 失败，则创建 FileSystemResource 对象
                return new FileSystemResource(location);
            }
        }
    }

}

