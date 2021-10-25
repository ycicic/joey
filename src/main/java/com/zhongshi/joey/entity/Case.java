package com.zhongshi.joey.entity;

import com.zhongshi.joey.entity.enums.CaseType;
import com.zhongshi.joey.entity.enums.ProtocolType;
import lombok.Data;

import java.io.Serializable;

/**
 * 用例实体类
 *
 * @author ycc
 */
@Data
public class Case implements Serializable {
    private static final long serialVersionUID = -2845703121922031921L;

    private Long caseId;

    private String groupModule;

    private String groupComment;

    private String caseComment;

    private CaseType caseType;

    private ProtocolType protocolType;

    private String requestData;

    private HttpProtocol httpProtocol;

    private Boolean isCache;

}
