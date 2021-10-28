package com.zhongshi.joey.assertion;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

/**
 * 断言类型
 *
 * @author ycc
 */
@AllArgsConstructor
public enum AssertType {

    /**
     * 相等
     */
    EQUAL("="),
    /**
     * 不相等
     */
    UNEQUAL("!="),
    /**
     * 大于
     */
    MORE_THAN(">"),
    /**
     * 小于
     */
    LESS_THAN("<");

    private final String operator;

    public static AssertType getAssertType(String operator) {
        Optional<AssertType> first = Arrays.stream(AssertType.values()).filter(type -> type.operator.equals(operator)).findFirst();
        return first.orElse(null);
    }

}
