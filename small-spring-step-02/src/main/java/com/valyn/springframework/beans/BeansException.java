package com.valyn.springframework.beans;

/**
 * BeansException是一个继承自RuntimeException的异常类。
 * 它用于在Bean处理过程中抛出异常，通常用于指示Bean的实例化、注入、依赖等操作出现问题。
 */
public class BeansException extends RuntimeException {

    /**
     * 使用指定的错误消息创建一个新的BeansException对象。
     *
     * @param msg 错误消息
     */
    public BeansException(String msg) {
        super(msg);
    }

    /**
     * 使用指定的错误消息和原因创建一个新的BeansException对象。
     *
     * @param msg   错误消息
     * @param cause 异常的原因（可选）
     */
    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }

}

/**
 * BeansException是一个继承自RuntimeException的异常类。
 *
 * 该类提供了以下构造方法：
 *
 * BeansException(String msg)：使用指定的错误消息创建一个新的BeansException对象。
 *
 * BeansException(String msg, Throwable cause)：使用指定的错误消息和原因创建一个新的BeansException对象。
 *
 * BeansException用于在Bean处理过程中抛出异常，它可以包含一个错误消息和一个可选的原因。通常情况下，它会被用来指示Bean的实例化、注入、依赖等操作出现了问题。
 */