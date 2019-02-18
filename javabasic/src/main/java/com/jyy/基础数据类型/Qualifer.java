package com.jyy.基础数据类型;

/**
 * 修饰符
 */
public class Qualifer {

    static int i = 10;
    static public int j =10;
    static protected int m = 10;
    static private int n = 10;

}

class QualifierSon extends Qualifer {

    public static void main(String[] args) {
        System.out.println(i);
        System.out.println(j);
        System.out.println(m);
        // 私有成员变量无法被继承
//        System.out.println(n);
    }
}