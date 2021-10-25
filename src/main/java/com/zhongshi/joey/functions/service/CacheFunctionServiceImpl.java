package com.zhongshi.joey.functions.service;

import com.zhongshi.joey.cache.CaseResponseCache;
import com.zhongshi.joey.functions.FunctionType;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * 缓存方法类
 *
 * @author ycc
 */
@Slf4j
@FunctionType("cache")
public class CacheFunctionServiceImpl implements FunctionService {

    @SneakyThrows
    @Override
    public String execute(String... args) {
        return CaseResponseCache.CACHE.get(Long.valueOf(args[0]));
    }
}
