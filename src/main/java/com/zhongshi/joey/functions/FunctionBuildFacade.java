package com.zhongshi.joey.functions;

/**
 * 公共方法转换门面
 *
 * @author ycc
 */
public class FunctionBuildFacade {

    public static String execute(String functionName, String[] args) {
        return FunctionBuildFactory.getFunction(functionName).execute(args);
    }

}
