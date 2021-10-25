package com.zhongshi.joey.processor;

import com.zhongshi.joey.entity.Case;
import com.zhongshi.joey.utils.BuildDataUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 用例处理器门面
 *
 * @author ycc
 */
@Slf4j
public class CaseProcessorFacade {

    public Object exec(Case testCase) {
        String requestData = BuildDataUtil.buildRequestData(testCase.getRequestData());
        testCase.setRequestData(requestData);
        return CaseProcessorFactory.getService(testCase.getProtocolType()).exec(testCase);
    }

}
