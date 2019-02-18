package com.jyy.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalUse {


    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(1);
        for (int i = 0; i< 10; i++) {
            service.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                    Student student = new Student();
                    System.out.println(student.getNumber());
                    System.out.println("~~~~~~~~~~~~~~~~~~~");
                }
            });
        }

        service.shutdown();
    }
}

class Student{

    public static ThreadLocal t = new ThreadLocal();

    public Student() {
        if (t == null) {
            t = new ThreadLocal();
        }
        t.set(System.nanoTime() + "");
    }

    public String getNumber() {
        return (String) t.get();
    }
}