package com.forjyy.mybatistest.MoNiMybatis;

import java.lang.reflect.Proxy;

/**
 * Created by LIZHUANGZHUANG001 on 2019/3/10.
 */
public class SqlSessionTest {

    @SuppressWarnings("unchecked")
    public <T> T getMapper(Class<? extends Object> type){
        //1、通过代理工厂，利用传入的类型获取对应的代理类（这边模拟时直接new MapperProxy()）
        //2、通过代理类和传入的类型创建代理实例返回出去
        return (T) Proxy.newProxyInstance(
                type.getClassLoader(),
                new Class<?>[]{type},
                new MapperProxy());
    }

}