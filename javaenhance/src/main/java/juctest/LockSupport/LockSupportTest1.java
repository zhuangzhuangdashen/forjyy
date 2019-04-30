package juctest.LockSupport;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by LIZHUANGZHUANG001 on 2019/4/14.
 */
public class LockSupportTest1 {

    private static Thread mainThread;

    public static void main(String[] args) {

        ThreadA ta = new ThreadA("ta");
        // 获取主线程
        mainThread = Thread.currentThread();

        System.out.println(Thread.currentThread().getName()+" start ta");
        LockSupport.park(mainThread);
        System.out.println(Thread.currentThread().getName()+" block");
        ta.start();
        // 主线程阻塞

        System.out.println(Thread.currentThread().getName()+" continue");
    }

    static class ThreadA extends Thread{

        public ThreadA(String name) {
            super(name);
        }

        public void run() {
            System.out.println(Thread.currentThread().getName()+" wakup others");
            // 唤醒“主线程”
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
            }
            LockSupport.unpark(mainThread);
        }
    }
}
