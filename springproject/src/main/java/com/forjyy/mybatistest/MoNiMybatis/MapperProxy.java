package com.forjyy.mybatistest.MoNiMybatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by LIZHUANGZHUANG001 on 2019/3/10.
 */
public class MapperProxy implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //1、根据传入的接口信息，这里method的方法找到需要执行的sql语句（接口类全名 + 方法名 = 配置文件中的namespace + 执行sql的id）
        //2、获取对应sql执行的类型，以及获取sql返回参数的类型
        //3、调用sqlSession对应的增删改查方法 ， 执行sql语句返回结果
        System.out.println("接口执行的对应方法是： " + method.getName());
        ResultDemo resultDemo = new ResultDemo();
        resultDemo.setId(101);
        resultDemo.setValue("测试返回结果");
        return resultDemo;
    }

}