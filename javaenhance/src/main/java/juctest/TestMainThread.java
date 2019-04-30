package juctest;

/**
 * Created by LIZHUANGZHUANG001 on 2019/4/17.
 */
public class TestMainThread {

    public static void main(String[] args) {

        test();
        System.out.println("Hello");

    }

    private static void test() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("我是一个孤儿线程");
            }
        }).start();
//        System.out.println("Hello");
    }
}
