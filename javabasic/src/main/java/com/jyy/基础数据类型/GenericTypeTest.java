package com.jyy.基础数据类型;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LIZHUANGZHUANG001 on 2019/2/19.
 *  泛型类
 * T type
 * <K V> key value
 * E element
 *
 * 注意：
 * 泛型不能用在静态属性上
 * 指定的类型不能是基本类型
 */
public class GenericTypeTest<T, S> {
    private T t;
    List<T> list = new ArrayList<>();

    public GenericTypeTest() {
        System.out.println("构造方法执行完毕");
    }

    public void add(T t) {
        list.add(t);
        System.out.println(t);
    }

    public static void main(String[] args) {
        GenericTypeTest<Integer, S> test = new GenericTypeTest<>();
        test.add(1);
    }
}
