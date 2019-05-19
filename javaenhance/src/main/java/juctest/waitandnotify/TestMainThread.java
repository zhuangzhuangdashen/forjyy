package juctest.waitandnotify;

/**
 * Created by LIZHUANGZHUANG001 on 2019/5/2.
 */
public class TestMainThread {
    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    ;
                }
            }
        }).start();
        System.out.println("Main函数执行完成");
    }
}
