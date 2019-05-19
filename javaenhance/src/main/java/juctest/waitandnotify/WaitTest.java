package juctest.waitandnotify;

/**
 * Created by LIZHUANGZHUANG001 on 2019/5/2.
 */
class ThreadA extends Thread{

    public ThreadA(String name) {
        super(name);
    }

    public void run() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName()+" call notify()");
            // 唤醒当前的wait线程
            notify();
        }
    }
}

public class WaitTest {

    public static void main(String[] args) {

        ThreadA t1 = new ThreadA("t1");

        synchronized(t1) {
            try {
                // 启动“线程t1”
                System.out.println(Thread.currentThread().getName()+" start t1");
                t1.start();

                Thread.sleep(10000);

                // 主线程等待t1通过notify()唤醒。
                /**
                 * 这里有个很有意思的点
                 * 无论Thread.sleep(10000);休眠多久 wait都会先于notify执行
                 * 这是因为notify之前需要获得锁 synchronized (this)
                 * 而sleep方法不会释放锁，wait方法会释放锁
                 * 所以一直等到wait方法释放锁之后线程t1才能获得锁，然后唤醒同一个monitor上得休眠线程，也就是main
                 */
                System.out.println(Thread.currentThread().getName()+" wait()");
                t1.wait();

                System.out.println(Thread.currentThread().getName()+" continue");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}