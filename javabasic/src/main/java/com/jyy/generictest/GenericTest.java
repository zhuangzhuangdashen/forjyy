package com.jyy.generictest;

import java.util.Random;

/**
 * Created by LIZHUANGZHUANG001 on 2019/2/26.
 * 泛型类
 */
public class GenericTest<T> {

    private T var;

    public GenericTest(T var) {
        this.var = var;
    }
}

/**
 * 泛型接口
 * @param <T>
 */
interface T1 <T>{

    T test();
}

/**
 * 默认f泛型<Object>
 */
class InterfaceTypeSubClass implements T1{

    @Override
    public Object test() {
        return null;
    }
}

class InterfaceTypeSubClass2<T> implements T1 <T> {

    @Override
    public T test() {
        return null;
    }
}

class InterfaceTypeSubClass3 implements T1 <String> {

    private String [] fruits = new String[]{"apple", "orange"};

    @Override
    public String test() {
        Random random = new Random(2);
        return fruits[random.nextInt()];
    }
}

