package juctest.LockSupport;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by LIZHUANGZHUANG001 on 2019/5/3.
 */
public class LockSupportTest2 {

    private static Thread mainThread;

    public static void main(String[] args) {

        ThreadA ta = new ThreadA("ta");
        // 获取主线程
        mainThread = Thread.currentThread();

        System.out.println(Thread.currentThread().getName()+" start ta");
//        ta.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" block");
        // 主线程阻塞
        LockSupport.park(mainThread);

        System.out.println(Thread.currentThread().getName()+" continue");
    }

    static class ThreadA extends Thread{

        public ThreadA(String name) {
            super(name);
        }

        public void run() {
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println(Thread.currentThread().getName()+" wakup others");
            // 唤醒“主线程”
            LockSupport.unpark(mainThread);
        }
    }
}