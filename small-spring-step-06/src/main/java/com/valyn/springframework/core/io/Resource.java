package com.valyn.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * Resource 接口定义了获取资源输入流的方法。
 */
public interface Resource {

    /**
     * 获取资源的输入流。
     *
     * @return 资源的输入流
     * @throws IOException 如果无法打开输入流时抛出此异常
     */
    InputStream getInputStream() throws IOException;

}
