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
     * @param workflowId 工作流ID
     * @return 要执行的case
     */
    List<Case> queryCaseByWorkflowId(@Param("workflowId") String workflowId);

    /**
     * 通过workflowId查询节点执行顺序
     *
     * @param workflowId 工作流ID
     * @return 节点执行顺序
     */
    String selectOrder(@Param("workflowId") String workflowId);

}
