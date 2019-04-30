package com.forjyy;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by LIZHUANGZHUANG001 on 2019/3/10.
 */
public class SqlSessionFactoryBuilderTest {

    private static SqlSessionFactory sqlSessionFactory;

    private SqlSessionFactoryBuilderTest() {
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        if (sqlSessionFactory == null) {
            synchronized (SqlSessionFactoryBuilderTest.class) {
                if (sqlSessionFactory == null) {
                    String resource = "mybatis-config.xml";
                    InputStream stream = null;
                    try {
                        stream = Resources.getResourceAsStream(resource);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    sqlSessionFactory = new SqlSessionFactoryBuilder().build(stream);
                }
            }
        }
        return sqlSessionFactory;
    }
}
