package com.jyy.LocalCacheUtils;

public class TestLocalCacheUtils {

    public static void main(String[] args) {

        RedisKeyLiveTime.getEnumStrArray();

        long beginTime = System.currentTimeMillis();
        Thread t1 = new Thread(new ExecuteInvokeTimeClass());
        t1.setPriority(6);
        t1.setName("线程1");

        Thread t2 = new Thread(new ExecuteInvokeTimeClass());
        t2.setPriority(5);
        t2.setName("线程2");

        t1.start();
        t2.start();
        long endTime = System.currentTimeMillis();
        System.out.println("共执行" + (endTime - beginTime) / 1000 + "s");
    }
}
    class ExecuteInvokeTimeClass extends Thread{
        @Override
        public void run() {
            for (int i = 0; i<510; i++) {
                LocalCacheUtils.increaseInvokeTime("8005235", "QueryRoomFreeWithActivityList_seven1");
//                LocalCacheUtils2.increaseInvokeTime("8005236", "QueryRoomFreeWithActivityList_seven");
            }
            System.out.println("end");
        }
    }
