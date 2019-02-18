package com.jyy.LocalCacheUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 本地缓存工具类
 * 专门用于接口的访问频次限制
 */
public class LocalCacheUtils {

    public static final Logger LOGGER = LoggerFactory.getLogger(LocalCacheUtils.class);

    public static final Object lockObj = new Object();

    /**
     *  String:业务key
     *  Long:当前时间
     *  Integer：次数
     */
    public static final ConcurrentHashMap<String, ConcurrentHashMap<String, ConcurrentHashMap<Long, Integer>>> map = new ConcurrentHashMap<>();

    /**
     * 增加访问次数
     * Map 示例
     *  map:[
     *  {"8005236":
     *      12321121212（存储时的毫秒数） : 1}
     *  ]
     * @return true:证明没有超过频率限制
     */
    public static boolean increaseInvokeTime(String key, String functionName) {
        Assert.notNull(key, "key must not be empty");
        synchronized (lockObj) {
            if (!map.containsKey(key)) {
                RedisKeyLiveTime[] redisKeyLiveTimeInstanceArray = RedisKeyLiveTime.values();
                if (RedisKeyLiveTime.getEnumStrArray().contains(redisKeyLiveTimeInstanceArray)) {
                    ConcurrentHashMap<Long, Integer> longIntegerConcurrentHashMap = new ConcurrentHashMap<>();
                    longIntegerConcurrentHashMap.put(System.currentTimeMillis(), 1);

                    ConcurrentHashMap<String, ConcurrentHashMap<Long, Integer>> map1 = new ConcurrentHashMap<>();
                    map1.put(functionName, longIntegerConcurrentHashMap);
                    map.put(key, map1);
                    return true;
                }

                LOGGER.error("no match function name");
                System.out.println("no match function name");
                return false;
//            LOGGER.error("increateInvokeTime failed.no match key");
//            return false;
            }

            ConcurrentHashMap<String, ConcurrentHashMap<Long, Integer>> longIntegerMap = map.get(key);
            // 懒删除 查询的时候发现已过期再删除  或者可以写个定时策略

            // 获取
            if (CollectionUtils.isEmpty(longIntegerMap) || CollectionUtils.isEmpty(longIntegerMap.get(functionName))) {
                ConcurrentHashMap initMap = new ConcurrentHashMap<>();
                initMap.put(functionName, new ConcurrentHashMap<>().put(System.currentTimeMillis(), 1));
                map.put(key, initMap);
                return true;
            } else {
                ConcurrentHashMap<Long, Integer> longIntegerConcurrentHashMap = longIntegerMap.get(functionName);
                RedisKeyLiveTime redisKeyLiveTime = RedisKeyLiveTime.valueOf(functionName);
                if (redisKeyLiveTime == null) {
                    LOGGER.error("increateInvokeTime failed.no match redisKeyLiveTime");
                    return false;
                }
                Enumeration<Long> keys = longIntegerConcurrentHashMap.keys();
                if (!keys.hasMoreElements()) {
                    longIntegerConcurrentHashMap.put(System.currentTimeMillis(), 1);
                    longIntegerMap.put(functionName, longIntegerConcurrentHashMap);
                    map.put(key, longIntegerMap);
                    return true;
                } else {
                    Long aLong = keys.nextElement();
                    long mills = redisKeyLiveTime.getMills();
                    // 已过期
                    if (System.currentTimeMillis() - aLong >= mills * 1000) {
                        longIntegerConcurrentHashMap.put(System.currentTimeMillis(), 1);
                        longIntegerMap.put(functionName, longIntegerConcurrentHashMap);
                        map.put(key, longIntegerMap);
                        return true;
                    }
                    // 未过期
                    int alreadyInvokeTime = longIntegerConcurrentHashMap.get(aLong);
                    if (alreadyInvokeTime > RedisKeyLiveTime.valueOf(functionName).times) {
                        System.out.println("访问超限，禁止访问!");
                        return false;
                    } else {
                        System.out.println(Thread.currentThread().getName() + "线程 :" + key + functionName + "已访问" + alreadyInvokeTime + "次");
                        longIntegerConcurrentHashMap.put(aLong, alreadyInvokeTime + 1);
                        longIntegerMap.put(functionName, longIntegerConcurrentHashMap);
                        map.put(key, longIntegerMap);
                        return true;
                    }
                }
            }
        }
    }
}
