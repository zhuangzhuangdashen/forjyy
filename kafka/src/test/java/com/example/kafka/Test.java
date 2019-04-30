package com.example.kafka;

/**
 * Created by LIZHUANGZHUANG001 on 2019/4/24.
 */
public class Test {
    public static void main(String[] args) {

        test1();
    }

    public static void test1() {
        Integer a = null;
        String b = null;
        test2(String.valueOf(a), new Person());
    }

    public static void test2(String a, Person b) {
        System.out.println("a : " + a + ",b: " + b);
    }
}

class Person{

}
