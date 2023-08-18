package com.valyn.springframework.core.io;

import cn.hutool.core.lang.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * UrlResource 类实现了 Resource 接口，用于表示 URL 资源。
 */
public class UrlResource implements Resource {

    private final URL url;

    /**
     * 使用给定的 URL 创建一个 UrlResource 对象。
     *
     * @param url URL 对象
     * @throws IllegalArgumentException 如果 URL 为 null，则抛出此异常
     */
    public UrlResource(URL url) {
        Assert.notNull(url, "URL must not be null");
        this.url = url;
    }

    /**
     * 获取 URL 资源的输入流。
     *
     * @return URL 资源的输入流
     * @throws IOException 如果无法打开输入流时抛出此异常
     */
    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection con = this.url.openConnection();
        try {
            return con.getInputStream();
        } catch (IOException ex) {
            if (con instanceof HttpURLConnection) {
                ((HttpURLConnection) con).disconnect();
            }
            throw ex;
        }
    }

}
