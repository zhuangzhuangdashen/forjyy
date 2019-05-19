package juctest.interrupttest;

/**
 * Created by LIZHUANGZHUANG001 on 2019/5/2.
 */
public class Test2 {


    public static void main(String[] args) throws InterruptedException {


        ThreadD d = new ThreadD();
        d.start();
        System.out.println(d.isInterrupted());
//        while (!d.isInterrupted()) {
            d.interrupt();
        System.out.println(d.isInterrupted());
//        }
        d.start();

        ThreadD d1 = new ThreadD();
        d1.start();

//        Thread.sleep(1000);
        System.out.println(d1.getState());
//        d.isInterrupted();
        System.out.println(d.getState());
    }
}

class ThreadD extends Thread{

    @Override
    public void run() {
        super.run();

    }
}
