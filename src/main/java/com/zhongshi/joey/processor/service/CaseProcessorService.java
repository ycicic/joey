package com.zhongshi.joey.processor.service;

import com.zhongshi.joey.entity.Case;

/**
 * 用例处理服务
 *
 * @author ycc
 */
public interface CaseProcessorService {
    /**
     * 处理
     *
     * @param testCase 用例
     * @return obj
     */
    String exec(Case testCase);
}
