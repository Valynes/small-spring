package com.valyn.springframework.beans;

/**
 * PropertyValue类表示属性的名称和值。
 */
public class PropertyValue {

    private final String name; // 属性的名称

    private final Object value; // 属性的值

    /**
     * 构造方法，用于创建PropertyValue对象。
     *
     * @param name  属性的名称
     * @param value 属性的值
     */
    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    /**
     * 获取属性的名称。
     *
     * @return 属性的名称
     */
    public String getName() {
        return name;
    }

    /**
     * 获取属性的值。
     *
     * @return 属性的值
     */
    public Object getValue() {
        return value;
    }

}
