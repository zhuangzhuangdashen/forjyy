package juctest.waitandnotify;

/**
 * Created by LIZHUANGZHUANG001 on 2019/5/3.
 */
public class WaitAndNotifyMoniManyCondition {
    public static void main(String[] args) {

        new ThradE().start();
        new ThradE().start();

    }

}

class ThradE extends Thread{
    @Override
    public void run() {
        synchronized (ThradE.class) {
            super.run();
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + "  :" + i);

                try {
                    notifyAll();
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
        }
    }
}
