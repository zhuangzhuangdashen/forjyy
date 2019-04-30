package com.forjyy.springproject.service.impl;

import com.forjyy.springproject.entity.JyyEntity;
import com.forjyy.springproject.service.JyyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by LIZHUANGZHUANG001 on 2019/2/19.
 */
@Component
public class JyyServiceImpl implements JyyService {

    @Autowired
    private DataSource dataSource;

    @Override
    @CachePut(key = "#entity.age", value = "entity")
    public JyyEntity insert(JyyEntity entity) {
        entity = new JyyEntity(10);
        System.out.println("执行完毕");
        return entity;
//            Connection connection = dataSource.getConnection();
//
//            PreparedStatement preparedStatement = connection.prepareStatement("insert into jyy values(" + entity.getAge() + ")");
//            preparedStatement.execute();
//            connection.commit();
    }

    @Override
    @Cacheable(key = "#age", value = "entity")
    public JyyEntity get(int age) {

        System.out.println("从数据库捞信息");
//        Connection connection = null;
//        try {
//            connection = dataSource.getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement("select * from jyy");
//            return  preparedStatement.executeQuery();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return null;
    }
}
