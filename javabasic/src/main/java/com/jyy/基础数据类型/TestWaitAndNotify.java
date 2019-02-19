package com.jyy.基础数据类型;

/**
 * Created by LIZHUANGZHUANG001 on 2019/2/19.
 */
public class TestWaitAndNotify {

    public static void main(String[] args) {

        final Object obj = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj) {
                    System.out.println("开始等待");
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("被唤醒，开始10s休眠");
                try {
                    // 线程休眠1os  测试主线程是否是守护线程
                    Thread.sleep(30000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                    System.out.println("休眠结束");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("执行唤醒");
                synchronized (obj) {
                    obj.notify();
                }
            }
        }).start();

        System.out.println("主线程继续等待");
        System.out.println("主线程是否是守护线程 :" + Thread.currentThread().isDaemon());
    }
}
