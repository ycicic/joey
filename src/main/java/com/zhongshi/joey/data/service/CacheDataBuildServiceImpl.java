package com.zhongshi.joey.data.service;

import com.alibaba.fastjson.JSONObject;
import com.zhongshi.joey.cache.CaseResponseCache;
import com.zhongshi.joey.data.DataType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * 缓存数据构建服务实现类
 *
 * @author ycc
 */
@Slf4j
@DataType("cache")
public class CacheDataBuildServiceImpl implements DataBuildService {
    @Override
    public String build(String data) {
        String[] split = data.split("\\.");
        String key = split[1];
        String value = CaseResponseCache.CACHE.get(Long.valueOf(key));
        if (StringUtils.isEmpty(value)) {
            throw new IllegalArgumentException("无此参数对应的缓存");
        }
        for (int i = 2; i < split.length; i++) {
            JSONObject object = JSONObject.parseObject(value);
            value = object.getString(split[i]);
        }
        return value;
    }
}
