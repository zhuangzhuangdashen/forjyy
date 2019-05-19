package juctest.yieldtest;

/**
 * Created by LIZHUANGZHUANG001 on 2019/5/2.
 */
public class YieldLockTest{

    private static Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        ThreadA t1 = new ThreadA("t1");
        t1.setPriority(8);
        ThreadA t2 = new ThreadA("t2");
        t2.setPriority(4);
        t2.start();
        t2.yield();
        Thread.sleep(5);
        t1.start();
    }

    static class ThreadA extends Thread{
        public ThreadA(String name){
            super(name);
        }
        public void run(){
            // 获取obj对象的同步锁
            synchronized (obj) {
                for(int i=0; i <10000; i++){
                    System.out.printf("%s [%d]:%d\n", this.getName(), this.getPriority(), i);
                    // i整除4时，调用yield
                    if (i%4 == 0)
                        Thread.yield();
                }
            }
        }
    }
}