package com.zhongshi.joey.test;

import com.alibaba.fastjson.JSONObject;
import com.zhongshi.joey.assertion.AssertType;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 测试基类
 *
 * @author ycc
 */
@Slf4j
public class BaseTest {
    private static final Pattern PRE_PATTERN = Pattern.compile("^(\\$\\.)?([\\w.]*)(=|>|<|!=)\"(.*)\"$");

    @SneakyThrows
    protected void assertCase(String preResults, String exec) {
        JSONObject object = JSONObject.parseObject(exec);
        String[] preResult = preResults.split(";");
        for (String s : preResult) {
            Matcher matcher = PRE_PATTERN.matcher(s);
            if (matcher.find()) {
                Object obj;
                if (StringUtils.isNotEmpty(matcher.group(1))) {
                    String[] keys = matcher.group(2).split("\\.");
                    obj = object;
                    for (String key : keys) {
                        if (obj instanceof JSONObject) {
                            obj = ((JSONObject) obj).get(key);
                        } else {
                            throw new Exception("类型不匹配");
                        }
                    }
                } else {
                    obj = matcher.group(2);
                }

                AssertType assertType = AssertType.getAssertType(matcher.group(3));
                String value = matcher.group(4);

                switch (assertType) {
                    case EQUAL:
                        Assert.assertEquals(obj.toString(), value);
                        break;
                    case UNEQUAL:
                        Assert.assertNotEquals(obj.toString(), value);
                        break;
                    case LESS_THAN:
                        Assert.assertTrue(0 > new BigDecimal(obj.toString()).compareTo(new BigDecimal(value)));
                        break;
                    case MORE_THAN:
                        Assert.assertTrue(0 < new BigDecimal(obj.toString()).compareTo(new BigDecimal(value)));
                        break;
                    default:
                        break;
                }
            }
        }
    }

}
