package juctest.yieldtest;

/**
 * Created by LIZHUANGZHUANG001 on 2019/5/2.
 */
public class YieldLockTest2{

    private static Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        ThreadC t1 = new ThreadC("t1");
        t1.start();

        Thread.sleep(1000);
        synchronized (t1) {
            for(int i=0; i <10; i++) {
                System.out.println("main" + i);
                // i整除4时，调用yield
                if (i % 4 == 0)
                    Thread.yield();
            }
        }
    }
}

class ThreadC extends Thread{
        public ThreadC(String name){
            super(name);
        }
        public void run(){
            // 获取obj对象的同步锁
            synchronized (this) {
                for(int i=0; i <10; i++){
                    System.out.printf("%s [%d]:%d\n", this.getName(), this.getPriority(), i);
                    // i整除4时，调用yield
                    if (i%4 == 0)
                        Thread.yield();
                }
            }
        }
    }