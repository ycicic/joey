package com.zhongshi.joey.data;

import java.lang.annotation.*;

/**
 * 数据构建类型声明注解
 *
 * @author ycc
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataType {
    String value();
}
