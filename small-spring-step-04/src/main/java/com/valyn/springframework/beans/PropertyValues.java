package com.valyn.springframework.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * PropertyValues类用于管理一组属性值。
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>(); // 属性值列表

    /**
     * 添加属性值到列表中。
     *
     * @param pv 属性值对象
     */
    public void addPropertyValue(PropertyValue pv) {
        this.propertyValueList.add(pv);
    }

    /**
     * 获取所有属性值。
     *
     * @return 所有属性值的数组
     */
    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    /**
     * 根据属性名称获取对应的属性值。
     *
     * @param propertyName 属性名称
     * @return 对应的属性值，如果找不到则返回null
     */
    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue pv : this.propertyValueList) {
            if (pv.getName().equals(propertyName)) {
                return pv;
            }
        }
        return null;
    }

}
