package com.jyy.基础数据类型;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LIZHUANGZHUANG001 on 2019/2/19.
 *
 * 泛型类 泛型接口 泛型方法
 *
 */
public class GenericTypeOnFunctionTest {

    public static <T extends List> void testGeneric(T t) {
        System.out.println(t);
        return;
    }

    public static void main(String[] args) {
        testGeneric(new ArrayList());
    }
}
