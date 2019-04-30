package com.jyy.guavautilstest;

import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

/**
 * Created by LIZHUANGZHUANG001 on 2019/2/25.
 */
public class StopWatchTest {

    public static void main(String[] args) {

        String s = "123";
        String ss = new String("123");
        Preconditions.checkArgument(s.equals(ss), "ss不等于ss");
        Preconditions.checkNotNull(s, "s不能为空");


        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        System.out.println(elapsed + "ms");

    }
}
