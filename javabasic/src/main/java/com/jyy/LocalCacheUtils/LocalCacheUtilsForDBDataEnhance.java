package com.jyy.LocalCacheUtils;

import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LIZHUANGZHUANG001 on 2019/2/18.
 * LocalCacheUtilsForDBData 增强版 可以增量对缓存进行替换
 *
 */
public class LocalCacheUtilsForDBDataEnhance {
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
     * 写缓存加锁 避免多个线程同时写
     * @param businessKey
     * @param cacheData
     */
    public static synchronized void addCache(String businessKey, List cacheData) {
        Assert.notNull(businessKey, "businessKey can not be null");

        if (CollectionUtils.isEmpty(businessData)) {
            businessData = new HashMap<>();
            businessData.put(businessKey, cacheData);
            return;
        }

        if (CollectionUtils.isEmpty(businessData.get(businessKey))) {
            businessData.put(businessKey, cacheData);
            return;
        }
        // 模拟真实场景 todo 上线后需注释
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 增量写核心逻辑
        List newCacheData = businessData.get(businessKey);
        newCacheData.addAll(cacheData);
        businessData.put(businessKey, newCacheData);
        return;
    }

    /**
     * 读缓存
     * 读的时候要保证没有人在写
     *      因此使用volatile，如果在写 就把读线程的缓存行置为失效
     *      要限制写的频率和内容
     * @param businessKey
     * @return
     */
    public static List readCache(String businessKey) throws InterruptedException {
        if (CollectionUtils.isEmpty(businessData)) {
            return null;
        }
        Thread.sleep(10);
        return businessData.get(businessKey);
    }

}
