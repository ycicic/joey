package com.zhongshi.joey.data;

/**
 * 数据构建门面
 *
 * @author ycc
 */
public class DataBuildFacade {

    public static String execute(String key) {
        String[] split = key.split("\\.");
        String type = split[0];
        return DataBuildFactory.getDataBuildService(type).build(key);
    }

}
