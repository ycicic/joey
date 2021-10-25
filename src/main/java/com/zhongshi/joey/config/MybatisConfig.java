package com.zhongshi.joey.config;

import lombok.SneakyThrows;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * mybatis配置类
 *
 * @author ycc
 */
public class MybatisConfig {
    private static SqlSessionFactory SESSION;

    @SneakyThrows
    public static SqlSessionFactory getSqlSessionFactory() {
        if (SESSION == null) {
            SESSION = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        }
        return SESSION;
    }

}
