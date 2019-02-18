package com.jyy.synchronizedtest;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 如果使用普通版的Integer，那么打印的结果会有脏数据
 *
 * 改进版 使用原子的Integer 和 volatile 直接覆盖synchronized锁的作用(饱和锁)
 */
public class TestSynchronizeInteger {

    static volatile AtomicInteger init = new AtomicInteger(0);

    static Object object = new Object();

    public static void main(String[] args) {

        Thread t1 = new Thread(new SyncIntegerClass(), "线程1");
//        t1.setPriority(5);
        Thread t2 = new Thread(new SyncIntegerClass(), "线程2");
//        t2.setPriority(6);

        t1.start();
        t2.start();
        }

    static class  SyncIntegerClass extends Thread{
            @Override
            public void run() {
                synchronized (init/*object*/) {
                    while (true) {
                        if (init.get() < 100) {
                            init.getAndIncrement();
                            System.out.println(Thread.currentThread().getName() + "执行结果 :" +init);
                            Thread.yield();
                        } else {
                            System.out.println("end!");
                            break;
                        }
                    }
                }
            }
        }
}
