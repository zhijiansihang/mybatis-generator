package com.paulzhangcc.tools.mybatis.util;

import lombok.Data;

/**
 * @author paul
 * @description
 * @date 2019/4/17
 */
@Data
public class ObjectRefUtil<T> {
    private volatile T value;
}
