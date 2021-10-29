package com.zhongshi.joey.entity;

import com.zhongshi.joey.config.MybatisConfig;
import com.zhongshi.joey.mapper.CaseMapper;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 提供数据实现类
 *
 * @author ycc
 */
@Slf4j
public class DataProviders {

    @SneakyThrows
    @DataProvider(name = "participate", parallel = true)
    public static Object[][] participate(Method method) {
        return getDataForCaseType();
    }

    private static Object[][] getDataForCaseType() {
        String workflowId = System.getProperty("workflowId");

        SqlSessionFactory sqlSessionFactory = MybatisConfig.getSqlSessionFactory();

        @Cleanup
        SqlSession sqlSession = sqlSessionFactory.openSession();
        CaseMapper mapper = sqlSession.getMapper(CaseMapper.class);
        List<Case> cases = mapper.queryCaseByModule(workflowId);

        Object[][] obj = new Object[cases.size()][1];

        for (int i = 0; i < cases.size(); i++) {
            obj[i][0] = cases.get(i);
        }
        return obj;
    }

}
