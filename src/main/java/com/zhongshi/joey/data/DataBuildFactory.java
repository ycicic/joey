package com.zhongshi.joey.data;

import com.zhongshi.joey.data.service.DataBuildService;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 数据构建工厂类
 *
 * @author ycc
 */
@Slf4j
public class DataBuildFactory {
    private static final Map<String, DataBuildService> DATA_BUILD_SERVICE_MAP = new HashMap<>();

    static {
        register();
    }

    private static void register() {
        Reflections reflections = new Reflections(DataBuildService.class.getPackage().getName());
        Set<Class<?>> dataBuildClasses = reflections.getTypesAnnotatedWith(DataType.class);
        dataBuildClasses.forEach(dataBuildClass -> {
            DataType dataType = dataBuildClass.getAnnotation(DataType.class);

            if (null != dataType) {
                try {
                    String value = dataType.value();
                    DataBuildService dataBuildService = (DataBuildService) dataBuildClass.newInstance();
                    DATA_BUILD_SERVICE_MAP.put(value, dataBuildService);
                    log.debug("注册数据构建处理器[{}: {}]成功", value, dataBuildClass.getSimpleName());
                } catch (Exception e) {
                    log.error("注册数据构建处理器[{}]失败", dataBuildClass.getSimpleName(), e);
                }
            }
        });

    }

    protected static DataBuildService getDataBuildService(String dataBuildName) {
        return DATA_BUILD_SERVICE_MAP.get(dataBuildName);
    }
}
