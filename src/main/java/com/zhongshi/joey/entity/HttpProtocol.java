package com.zhongshi.joey.entity;

import com.zhongshi.joey.entity.enums.RequestDataType;
import com.zhongshi.joey.entity.enums.RequestMethod;
import lombok.Data;

/**
 * http协议渠道
 *
 * @author ycc
 */
@Data
public class HttpProtocol {

    private String host;
    private String url;
    private RequestMethod method;
    private RequestDataType dataType;

}
