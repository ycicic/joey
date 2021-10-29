package com.zhongshi.joey.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhongshi.joey.assertion.AssertType;
import com.zhongshi.joey.config.MybatisConfig;
import com.zhongshi.joey.entity.Case;
import com.zhongshi.joey.entity.ExecutionOrder;
import com.zhongshi.joey.exception.JoeyException;
import com.zhongshi.joey.mapper.CaseMapper;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 测试基类
 *
 * @author ycc
 */
@Slf4j
public class BaseTest {
    protected static final Queue<Case> CASE_QUEUE = new LinkedList<>();
    private static final Pattern PRE_PATTERN = Pattern.compile("^(\\$\\.)?([\\w.]*)(=|>|<|!=)\"(.*)\"$");

    @BeforeSuite
    protected void caseLoading() {
        log.info("测试套件执行前准备-装载测试用例");
        String workflowId = System.getProperty("workflowId");

        if (StringUtils.isEmpty(workflowId)) {
            throw new JoeyException("用例工作流ID确实，请检查参数");
        }

        SqlSessionFactory sqlSessionFactory = MybatisConfig.getSqlSessionFactory();

        @Cleanup
        SqlSession sqlSession = sqlSessionFactory.openSession();
        CaseMapper mapper = sqlSession.getMapper(CaseMapper.class);

        String orderStr = mapper.selectOrder(workflowId);
        List<ExecutionOrder> executionOrders = JSONArray.parseArray(orderStr, ExecutionOrder.class);

        List<Case> cases = mapper.queryCaseByModule(workflowId);
        Map<Long, Case> casesMap = cases.stream().collect(Collectors.toMap(Case::getCaseId, aCase -> aCase));

        Map<Long, Long> executionOrdersMap = executionOrders.stream().collect(Collectors.toMap(ExecutionOrder::getOrder, ExecutionOrder::getDetailId));

        executionOrdersMap.forEach((order, detailId) -> {
            Case aCase = casesMap.get(detailId);
            CASE_QUEUE.add(aCase);
            log.info("添加到执行队列[{}]：{}", order, aCase);
        });

    }

    @SneakyThrows
    protected void assertCase(String preResults, String exec) {
        if (StringUtils.isEmpty(preResults)) {
            return;
        }
        JSONObject object = JSONObject.parseObject(exec);
        String[] preResult = preResults.split(";");
        for (String s : preResult) {
            Matcher matcher = PRE_PATTERN.matcher(s);
            if (matcher.find()) {
                Object obj;
                if (StringUtils.isNotEmpty(matcher.group(1))) {
                    String[] keys = matcher.group(2).split("\\.");
                    obj = object;
                    for (String key : keys) {
                        if (obj instanceof JSONObject) {
                            obj = ((JSONObject) obj).get(key);
                        } else {
                            throw new Exception("类型不匹配");
                        }
                    }
                } else {
                    obj = matcher.group(2);
                }

                AssertType assertType = AssertType.getAssertType(matcher.group(3));
                String value = matcher.group(4);

                boolean flag = false;

                switch (assertType) {
                    case EQUAL:
                        flag = obj.toString().equals(value);
                        break;
                    case UNEQUAL:
                        flag = !obj.toString().equals(value);
                        break;
                    case LESS_THAN:
                        flag = 0 > new BigDecimal(obj.toString()).compareTo(new BigDecimal(value));
                        break;
                    case MORE_THAN:
                        flag = 0 < new BigDecimal(obj.toString()).compareTo(new BigDecimal(value));
                        break;
                    default:
                        break;
                }

                String condition = matcher.group();
                Assert.assertTrue(flag, "条件[" + condition + "]断言失败,参数实际值为：" + obj);
            }
        }
    }

}
