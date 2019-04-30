/**
 * Created by LIZHUANGZHUANG001 on 2019/4/21.
 */
package juctest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by LIZHUANGZHUANG001 on 2019/4/20.
 */
public class TestABC1 {
    static Lock lock = new ReentrantLock();
    static Condition conditionA = lock.newCondition();
    static Condition conditionB = lock.newCondition();


    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);

        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                for (int j = 0; j < 20; j++) {
                    System.out.println("A" + j);
                    try {
                        conditionB.signal();
                        conditionA.await();
                countDownLatch.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                lock.unlock();
            }
        });
        a.start();

        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.lock();
                for (int i = 0; i < 10; i++) {
                    System.out.println("B" + i);
                    conditionA.signal();
                    try {
                        conditionB.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.unlock();
                countDownLatch.countDown();

            }
        });
        b.start();
        try {
            countDownLatch.await();
            Thread c = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("C开始执行 ！");
                    System.out.println("C开始执行 ！");
                    System.out.println("C开始执行 ！");
                    System.out.println("C开始执行 ！");
                }
            });
            c.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
