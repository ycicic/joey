package com.zhongshi.joey.utils;

import com.alibaba.fastjson.JSONObject;
import com.zhongshi.joey.data.DataBuildFacade;
import com.zhongshi.joey.functions.FunctionBuildFacade;
import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 构建数据工具类
 *
 * @author ycc
 */
@Slf4j
public class BuildDataUtil {

    private static final Pattern FUN_PATTERN = Pattern.compile("__(\\w+)\\(((\\w+,?)*)\\)");
    private static final Pattern DATA_PATTERN = Pattern.compile("\\$\\{([\\w,.]*)[^}]*}");

    public static String buildRequestData(String requestData) {

        requestData = buildFunction(requestData);
        requestData = buildData(requestData);

        return requestData;
    }

    private static String buildFunction(String requestData) {
        Matcher matcher = FUN_PATTERN.matcher(requestData);

        while (matcher.find()) {
            String function = matcher.group();
            String functionName = matcher.group(1);
            String functionParameters = matcher.group(2);
            Allure.step("执行公共方法：" + function + ";函数：" + functionName + "; 参数：" + functionParameters);
            log.debug("检测到公共方法：{};函数：{}; 参数：{}", function, functionName, functionParameters);
            String res = FunctionBuildFacade.execute(functionName, new String[]{functionParameters});
            requestData = requestData.replace(function, res);
            Allure.step("执行参数转换：" + function + " --> " + res);
        }
        return requestData;
    }

    private static String buildData(String requestData) {
        Matcher matcher = DATA_PATTERN.matcher(requestData);
        while (matcher.find()) {
            String arg = matcher.group();
            log.debug("检测到待转换参数：{}", arg);
            String value = matcher.group(1);
            String data = DataBuildFacade.execute(value);
            requestData = requestData.replace(arg, data);
            log.debug("参数转换完成：{} -> {}", arg, data);
            Allure.step("执行参数转换：" + arg + " --> " + data);
        }
        return requestData;
    }

}
