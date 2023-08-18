package com.valyn.springframework.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * FileSystemResource 类实现了 Resource 接口，用于表示文件系统中的资源。
 */
public class FileSystemResource implements Resource {

    private final File file;
    private final String path;

    /**
     * 使用给定的 File 对象创建一个 FileSystemResource 对象。
     *
     * @param file 文件对象
     */
    public FileSystemResource(File file) {
        this.file = file;
        this.path = file.getPath();
    }

    /**
     * 使用给定的路径创建一个 FileSystemResource 对象。
     *
     * @param path 文件路径
     */
    public FileSystemResource(String path) {
        this.file = new File(path);
        this.path = path;
    }

    /**
     * 获取该文件系统资源的输入流。
     *
     * @return 文件系统资源的输入流
     * @throws IOException 如果无法打开输入流时抛出此异常
     */
    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    /**
     * 获取文件系统资源的路径。
     *
     * @return 文件系统资源的路径
     */
    public final String getPath() {
        return this.path;
    }

}
