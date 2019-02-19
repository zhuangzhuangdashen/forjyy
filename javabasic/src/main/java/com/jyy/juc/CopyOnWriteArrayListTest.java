package com.jyy.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by LIZHUANGZHUANG001 on 2019/2/19.
 */
public class CopyOnWriteArrayListTest {

    public static void main(String[] args) {
        final List<Integer> list = new CopyOnWriteArrayList();
//        final List<Integer> list = new ArrayList();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    list.add(1);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (true) {
                    for (Integer i : list) {
                        System.out.println(i);
                    }
                }
            }
        }).start();
    }
}
