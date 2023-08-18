package com.valyn.springframework.utils;

/**
 * ClassUtils 类提供了一些处理类的工具方法。
 */
public class ClassUtils {

    /**
     * 获取默认的类加载器。
     *
     * @return 默认的类加载器
     */
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {
            // 无法访问线程上下文类加载器 - 返回系统类加载器...
        }
        if (cl == null) {
            // 没有线程上下文类加载器 -> 使用此类的类加载器。
            cl = ClassUtils.class.getClassLoader();
        }
        return cl;
    }

}
