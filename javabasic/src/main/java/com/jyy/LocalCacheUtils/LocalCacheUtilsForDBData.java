package com.jyy.LocalCacheUtils;

import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 本地缓存 用来存储数据库数据
 *
 *  结构
 *  读--不加锁
 *  写--拿锁
 *  注意：此工具目前只支持缓存全量覆盖 不支持增量，否则可能会抛出并发修改异常，请单独处理业务的比较操作
 *  且不保证read的数据就是刚好insert后的数据
 */
public class LocalCacheUtilsForDBData {

    public static volatile Map<String, List> businessData = new HashMap<>();

    /**
     * 写缓存加锁 避免多个线程同时写
     * @param businessKey
     * @param cacheData
     */
    public static synchronized void insertCache(String businessKey, List cacheData) {
/*        try {
            // 模拟真实场景替换
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        Assert.notNull(businessKey, "businessKey can not be null");

        if (CollectionUtils.isEmpty(businessData)) {
            businessData = new HashMap<>();
        }
        businessData.put(businessKey, cacheData);
    }

    /**
     * 读缓存
     * 读的时候要保证没有人在写
     *      因此使用volatile，如果在写 就把读线程的缓存行置为失效
     *      要限制写的频率和内容
     * @param businessKey
     * @return
     */
    public static List readCache(String businessKey) {
        if (CollectionUtils.isEmpty(businessData)) {
            return null;
        }
        return businessData.get(businessKey);
    }
}
