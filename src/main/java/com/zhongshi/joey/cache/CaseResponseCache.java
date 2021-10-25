package com.zhongshi.joey.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用例返回值缓存
 *
 * @author ycc
 */
public class CaseResponseCache {

    public static final Map<Long, String> CACHE = new ConcurrentHashMap<>();

}
