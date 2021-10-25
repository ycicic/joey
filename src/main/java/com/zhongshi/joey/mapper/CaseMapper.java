package com.zhongshi.joey.mapper;

import com.zhongshi.joey.entity.Case;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用例Mapper
 *
 * @author ycc
 */
public interface CaseMapper {

    /**
     * 通过module查询要执行的case
     *
     * @param module   module
     * @param caseType caseType
     * @return 要执行的case
     */
    List<Case> queryCaseByModule(@Param("module") String module, @Param("caseType") String caseType);

}
