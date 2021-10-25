package com.zhongshi.joey.processor;

import com.zhongshi.joey.entity.enums.ProtocolType;

import java.lang.annotation.*;

/**
 * 协议
 *
 * @author ycc
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Protocol {

    ProtocolType value();

}
