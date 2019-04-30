package juctest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by LIZHUANGZHUANG001 on 2019/4/20.
 */
public class TestABC {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        CountDownLatch countDownLatch = new CountDownLatch(2);
        for (int i = 0;i < 2;i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("A");

                    countDownLatch.countDown();
                }
            });
        }

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
