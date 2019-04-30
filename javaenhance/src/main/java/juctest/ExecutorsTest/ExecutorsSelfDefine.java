package juctest.ExecutorsTest;

import java.util.concurrent.*;

/**
 * Created by LIZHUANGZHUANG001 on 2019/4/28.
 */
public class ExecutorsSelfDefine {

    public static void main(String[] args) {

        // 利用Executors工具类创建缓存线程池
        ExecutorService service = Executors.newCachedThreadPool();

        /**
         *
         * keepAliveTime when the number of threads is greater than
         *        the core, this is the maximum time that excess idle threads
         *        will wait for new tasks before terminating.
         *
         * SynchronousQueue :
         *          同步队列
         * ArrayBlockingQueue:
         *          阻塞队列
         * 区别？
         *
         *
         */
        ExecutorService service1 = new ThreadPoolExecutor(
                10,
                20,
                0L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10, true)
        );

        System.out.println(new Object());

    }

}
