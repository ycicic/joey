package com.zhongshi.joey.functions;

import com.zhongshi.joey.functions.service.FunctionService;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 公共方法转换工厂
 *
 * @author ycc
 */
@Slf4j
public class FunctionBuildFactory {

    private static final Map<String, FunctionService> FUNCTIONS = new HashMap<>();

    static {
        register();
    }

    private static void register() {
        Reflections reflections = new Reflections(FunctionService.class.getPackage().getName());
        Set<Class<?>> functionClasses = reflections.getTypesAnnotatedWith(FunctionType.class);
        functionClasses.forEach(functionClass -> {
            FunctionType functionType = functionClass.getAnnotation(FunctionType.class);

            if (null != functionType) {
                try {
                    String value = functionType.value();
                    FunctionService functionService = (FunctionService) functionClass.newInstance();
                    FUNCTIONS.put(value, functionService);
                    log.debug("注册公共方法处理器[{}: {}]成功", value, functionClass.getSimpleName());
                } catch (Exception e) {
                    log.error("注册公共方法处理器[{}]失败", functionClass.getSimpleName(), e);
                }
            }
        });

    }

    protected static FunctionService getFunction(String functionName){
        return FUNCTIONS.get(functionName);
    }

}
