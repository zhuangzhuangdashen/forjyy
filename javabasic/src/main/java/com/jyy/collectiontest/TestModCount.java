package com.jyy.collectiontest;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by LIZHUANGZHUANG001 on 2019/4/16.
 */
public class TestModCount {
    public static void main(String[] args) {


        List list = Lists.newArrayList();
        for (int i = 0; i < 1000000; i++) {
            list.add("1");
            list.remove("1");
            System.out.println(list.listIterator());
        }
        System.out.println();
    }
}
