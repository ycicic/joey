package com.zhongshi.joey.utils;

import lombok.SneakyThrows;

import java.io.InputStream;
import java.util.Properties;

/**
 * properties工具类
 *
 * @author ycc
 */
public class PropertiesUtil {

    private final Properties properties = new Properties();;

    @SneakyThrows
    public PropertiesUtil(String file){
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("\\"+file);
        properties.load(stream);
    }

    public String get(String key) {
        return properties.getProperty(key);
    }

}
