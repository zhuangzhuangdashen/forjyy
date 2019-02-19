package com.jyy.基础数据类型;

/**
 * Created by LIZHUANGZHUANG001 on 2019/2/19.
 */
public abstract class GenericAbstractTest <T1, T2>{

    abstract void test(T1 t);

}

class T1 extends GenericAbstractTest<Integer, String> {
    @Override
    void test(Integer t) {

    }
}

class T2 extends GenericAbstractTest<T1, T2> {
    @Override
    void test(T1 t) {

    }
}

class T3<Integer, T2, T3> extends GenericAbstractTest<Integer, T2> {
    @Override
    void test(Integer t) {

    }
}

