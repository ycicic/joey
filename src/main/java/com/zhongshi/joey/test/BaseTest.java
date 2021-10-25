package com.zhongshi.joey.test;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterSuite;

/**
 * 测试基类
 *
 * @author ycc
 */
@Slf4j
public class BaseTest {

    @AfterSuite
    public void generateTestReport() {
        log.info("生成测试报告");
    }

}
