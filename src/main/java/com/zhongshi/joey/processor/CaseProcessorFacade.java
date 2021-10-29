package com.zhongshi.joey.processor;

import com.zhongshi.joey.entity.Case;
import com.zhongshi.joey.utils.BuildDataUtil;
import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;

/**
 * 用例处理器门面
 *
 * @author ycc
 */
@Slf4j
public class CaseProcessorFacade {

    public String exec(Case testCase) {
        String requestData = BuildDataUtil.buildRequestData(testCase.getRequestData());
        Allure.step("解析请求数据：[" + testCase.getRequestData() + "] --> [" + requestData + "]");
        testCase.setRequestData(requestData);
        return CaseProcessorFactory.getService(testCase.getProtocolType()).exec(testCase);
    }

}
