package com.paulzhangcc.tools.mybatis.util;

/**
 * @author paul
 * @description
 * @date 2019/4/17
 */
public class ObjectRefUtil<T> {
    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    private volatile T value;

}
