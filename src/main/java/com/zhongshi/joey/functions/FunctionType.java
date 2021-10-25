package com.zhongshi.joey.functions;

import java.lang.annotation.*;

/**
 * 公共方法类型声明注解
 *
 * @author ycc
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FunctionType {
    String value();
}
