package juctest.interrupttest;

/**
 * Created by LIZHUANGZHUANG001 on 2019/5/2.
 */
class MyThread extends Thread {

    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        try {
            int i=0;
            while (true) {
                while (!isInterrupted()) {
                    Thread.sleep(100);
                    i++;
                    System.out.println(Thread.currentThread().getName() + " (" + this.getState() + ") loop " + i);
//                    interrupt();
                }
            }
        } catch (Exception e) {
            System.out.println(Thread.currentThread().getName() +" ("+this.getState()+") " +
                    "catch InterruptedException.");
        }
    }
}

public class Test1 {

    public static void main(String[] args) {
        try {
            Thread t1 = new MyThread("t1");  // 新建“线程t1”
            System.out.println(t1.getName() +" ("+t1.getState()+") is new.");

            t1.start();                      // 启动“线程t1”
            System.out.println(t1.getName() +" ("+t1.getState()+") is started.");

            // 主线程休眠300ms，然后主线程给t1发“中断”指令。
            Thread.sleep(300);
            t1.interrupt();
            System.out.println(t1.getName() +" ("+t1.getState()+") is interrupted.");

            // 主线程休眠300ms，然后查看t1的状态。
            Thread.sleep(300);
            System.out.println(t1.getName() +" ("+t1.getState()+") is interrupted now.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}