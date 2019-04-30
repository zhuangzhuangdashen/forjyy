package TimerTest;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by LIZHUANGZHUANG001 on 2019/4/28.
 */
public class TimerTest {
    public static void main(String[] args) {
//        schedule1();
//        schedule2();
//        schedule3();
//        System.out.println(new Object());
        long l = System.currentTimeMillis();
        for (int i = 0; i< 500000000; i++) {

        }
        long l1 = System.currentTimeMillis();
        System.out.println(l1 + "," + l + " :" + (l1 - l) + "ms");
    }


    /**
     * Timer实现
     */
    private static void schedule1() {
        int sleepTime=2*1000;
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Timer方式执行一次定时任务");
            }
        };
        new Timer().schedule(timerTask,1,sleepTime);
    }

    /**
     * Thread.sleep()实现
     */
    private static void schedule2() {
        //定时任务间隔时间
        int sleepTime=2*1000;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        System.out.println("Thread方式执行一次定时任务");
                        //线程休眠规定时间
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private static void schedule3() {
        int sleepTime=2*1000;
        ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutor.scheduleAtFixedRate(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("ScheduledExecutorService方式执行一次定时任务");
                    }
                }
                ,1,sleepTime, TimeUnit.MILLISECONDS);
    }

    private static void schedule4() {

    }

}
