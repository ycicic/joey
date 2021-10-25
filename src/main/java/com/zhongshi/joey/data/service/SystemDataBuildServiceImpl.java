package com.zhongshi.joey.data.service;

import com.zhongshi.joey.data.DataType;
import org.apache.commons.lang3.StringUtils;

/**
 * 系统变量数据构建服务实现类
 *
 * @author ycc
 */
@DataType("system")
public class SystemDataBuildServiceImpl implements DataBuildService {
    @Override
    public String build(String data) {
        String[] split = data.split("\\.");
        String key = split[1];
        String property = System.getProperty(key);
        if (StringUtils.isEmpty(property)) {
            throw new IllegalArgumentException("未匹配到该系统变量: " + key);
        }
        return property;
    }
}
