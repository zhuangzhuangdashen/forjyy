package com.jyy.基础数据类型;

/**
 * Created by LIZHUANGZHUANG001 on 2019/2/19.
 */
public class TestFinally {

    public static void main(String[] args) {

        try {
            System.out.println("try");
            try {
                System.out.println("线程开始休眠");
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            System.out.println("finally");
        }

    }
}
