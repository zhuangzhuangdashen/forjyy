package juctest.waitandnotify;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by LIZHUANGZHUANG001 on 2019/4/13.
 */
public class CountDownLatchTest {

    public static void main(String[] args) {
        //初始化计数
        AtomicInteger count = new AtomicInteger(0);
        //创建10个线程
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        //设置CountDownLatch为10
        java.util.concurrent.CountDownLatch countDownLatch = new java.util.concurrent.CountDownLatch(10);
        for(int i=0;i<10;i++){
            executorService.execute(() -> {
                //每个线程累加10000次
                for(int j=0;j<10000;j++){
                    count.addAndGet(1);
                }
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                //线程最后执行countDown
                countDownLatch.countDown();
            });
        }
        try{
            //调用await方法等待
            countDownLatch.await();
        }catch(Exception e){
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("C");
            }
        }).start();
        System.out.println(count.toString());
    }
}
