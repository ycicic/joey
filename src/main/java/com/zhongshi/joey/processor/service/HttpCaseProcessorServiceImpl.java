package com.zhongshi.joey.processor.service;

import com.alibaba.fastjson.JSONObject;
import com.zhongshi.joey.cache.CaseResponseCache;
import com.zhongshi.joey.entity.Case;
import com.zhongshi.joey.entity.HttpProtocol;
import com.zhongshi.joey.entity.enums.ProtocolType;
import com.zhongshi.joey.entity.enums.RequestDataType;
import com.zhongshi.joey.entity.enums.RequestMethod;
import com.zhongshi.joey.processor.Protocol;
import com.zhongshi.joey.utils.BuildDataUtil;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * HTTP用例处理
 *
 * @author ycc
 */
@Slf4j
@Protocol(ProtocolType.HTTP)
public class HttpCaseProcessorServiceImpl implements CaseProcessorService {

    @SneakyThrows
    @Override
    public String exec(Case testCase) {
        Boolean isCache = testCase.getIsCache();
        HttpProtocol httpProtocol = testCase.getHttpProtocol();
        JSONObject requestData = JSONObject.parseObject(testCase.getRequestData());
        RequestMethod method = httpProtocol.getMethod();
        RequestDataType dataType = httpProtocol.getDataType();
        String url = httpProtocol.getUrl();
        url = BuildDataUtil.buildRequestData(url);

        JSONObject headerObj = requestData.getJSONObject("header");
        JSONObject dataObj = requestData.getJSONObject("data");


        RequestSpecification request = RestAssured.given().contentType(dataType.toString()).request();

        if (null != headerObj) {
            request = request.headers(headerObj.getInnerMap());
        }

        log.debug("处理HTTP请求\n[{}][{}]\nheaders: {}\nparams: {}", method, url, headerObj, dataObj);

        Response response = null;
        switch (method) {
            case GET:
                if (null != dataObj) {
                    request = request.params(dataObj.getInnerMap());
                }
                response = request.get(url);
                break;
            case POST:
                if (null != dataObj) {
                    request = request.body(dataObj.toJSONString());
                }
                response = request.post(url);
                break;
            default:
                log.debug("暂不支持的类型: {}", method);
                break;
        }
        String res = "";

        if (response != null) {
            res = response.asString();
            log.debug("HTTP请求结果[{}]", res);
        } else {
            log.debug("HTTP请求无response返回");
        }

        if (isCache) {
            log.debug("缓存用例结果: {},{}", testCase.getCaseId(), res);
            CaseResponseCache.CACHE.put(testCase.getCaseId(), res);
        }

        return res;
    }
}
