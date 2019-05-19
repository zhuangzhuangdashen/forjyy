package redissontest;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Created by LIZHUANGZHUANG001 on 2019/5/5.
 */
@Slf4j
public class RedissonTest {

    static CountDownLatch countDownLatch = new CountDownLatch(5);
    public static void main(String[] args) throws InterruptedException {
        Config config = new Config();
//        config.setTransportMode(TransportMode.EPOLL);
        config.useSingleServer()
                .setAddress("redis://localhost:6379");
        RedissonClient redisson = Redisson.create(config);

        IntStream.rangeClosed(1,5)
                .parallel()
                .forEach(i -> {
                    System.out.println("InStream");
                    executeLock(redisson);
                });
    }

    public static void executeLock(RedissonClient redisson){
        RLock lock = redisson.getLock("myLock");
        boolean locked = false;
        try{
            log.info("try lock");
            locked = lock.tryLock();
//            locked = lock.tryLock(1,2,TimeUnit.MINUTES);
            log.info("get lock result:{}",locked);
            if(locked){
                TimeUnit.SECONDS.sleep(5);
                countDownLatch.countDown();
                log.info("get lock and finish");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            log.info("enter unlock");
            if(locked){
                lock.unlock();
                log.info("already unlock");
            } else {
                log.info("no lock , just unlock");
            }
        }
    }

}
