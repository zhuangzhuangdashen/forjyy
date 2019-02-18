package com.jyy.synchronizedtest;


/**
 * 问题：当synchronized锁的是int时，会发生i输出重复的值的问题
 *  当锁的是obj或者TestBaiduSyncInteger类时，问题解决
 *
 *
 *  问题出在i++上
 *  通过反编译可以看到i++在从虚拟机执行的时候被翻译为
 *  见 \src\main\resources\img\0218.img
 *  Integer.valueOf(i.intValue()+1) 每次都是新的对象
 *
 *  因此在使用synchronized的时候一定要锁不变的对象或者类
 *  一定要锁不变的对象
 *  一定要锁不变的对象
 *  一定要锁不变的对象
 *  一定要锁不变的对象
 *  一定要锁不变的对象
 *  一定要锁不变的对象
 */
public class TestBaiduSyncInteger implements Runnable{

    public static Integer i = new Integer(0);
    public static Object obj = new Object();
    @Override
    public void run() {
        while (true) {
            synchronized (/*i*//*TestBaiduSyncInteger.class*/obj) {
                if (i < 100) {
                    i++;
                    System.out.println("i=" + i);
                } else {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new TestBaiduSyncInteger());
        Thread t2 = new Thread(new TestBaiduSyncInteger());
        t1.start();
        t2.start();
    }
}
