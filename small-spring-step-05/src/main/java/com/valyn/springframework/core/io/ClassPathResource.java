package com.valyn.springframework.core.io;

import cn.hutool.core.lang.Assert;
import org.springframework.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * ClassPathResource 类实现了 Resource 接口，用于表示类路径资源。
 */
public class ClassPathResource implements Resource {

    private final String path;
    private ClassLoader classLoader;

    /**
     * 使用给定的路径创建一个 ClassPathResource 对象。
     *
     * @param path 类路径资源的路径
     */
    public ClassPathResource(String path) {
        this(path, null);
    }

    /**
     * 使用给定的路径和类加载器创建一个 ClassPathResource 对象。
     *
     * @param path         类路径资源的路径
     * @param classLoader  类加载器，用于加载资源
     */
    public ClassPathResource(String path, ClassLoader classLoader) {
        Assert.notNull(path, "Path must not be null");
        this.path = path;
        this.classLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
    }

    /**
     * 获取该类路径资源的输入流。
     *
     * @return 类路径资源的输入流
     * @throws IOException 如果无法打开输入流时抛出此异常
     */
    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = classLoader.getResourceAsStream(path);
        if (is == null) {
            throw new FileNotFoundException(this.path + " cannot be opened because it does not exist");
        }
        return is;
    }
}
