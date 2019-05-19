package juctest.consumerandproducer;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by LIZHUANGZHUANG001 on 2019/5/2.
 */
public class ProducerAndConsumerByHand {
    private static final Queue queue = new ArrayBlockingQueue<Integer>(20);

    static Lock lock = new ReentrantLock();
    static Condition notEmpty = lock.newCondition();
    static Condition notFull = lock.newCondition();

    public static void main(String[] args) {
        new Producer1().start();
        new Consumer1().start();
    }

     static class Producer1 extends Thread{
        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    int i = new Random().nextInt();
                    System.out.println("publish : " + Integer.valueOf(i));
                    queue.add(i);
                    Thread.sleep(1000);
                } catch (Exception e) {
                    try {
                        notFull.await();
                        notEmpty.signal();
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }finally {
                    lock.unlock();
                }
            }
        }
    }

     static class Consumer1 extends Thread{
        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    Object poll = queue.poll();
                    System.out.println("consume : " + poll);
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                    try {
                        notEmpty.await();
                        notFull.signal();
                    } catch (Exception e1) {
                        e1.getStackTrace();
                    }
                } finally {
                    lock.unlock();
                }
                }
        }
     }
}


