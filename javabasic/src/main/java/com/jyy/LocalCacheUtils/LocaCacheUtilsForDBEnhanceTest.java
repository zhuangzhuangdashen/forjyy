package com.jyy.LocalCacheUtils;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by LIZHUANGZHUANG001 on 2019/2/18.
 */
public class LocaCacheUtilsForDBEnhanceTest {
    public static final String BUSINESS_KEY = "forbidList";

    public static void main(String[] args) {
        // init data
//        initCacheData();
        // then read
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0 ; i <1000 ; i++) {
                    List list = null;
                    try {
                        list = LocalCacheUtilsForDBDataEnhance.readCache(BUSINESS_KEY);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("read content : " + (CollectionUtils.isEmpty(list) ? null : list.size()));
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0 ; i <100 ; i++) {
                    LocalCacheUtilsForDBDataEnhance.addCache(BUSINESS_KEY, getForbidList());
                    System.out.println("insert finish!");
                }
            }
        }).start();
    }

    private static List getForbidList() {
        final List forbidList = new ArrayList<>();
        forbidList.add(new ForbidInfo("1", "2", new Date(), new Date()));
        forbidList.add(new ForbidInfo("1111", "22222", new Date(), new Date()));

        return forbidList;
    }

    private static void initCacheData() {
        List forbidList = new ArrayList<>();
        forbidList.add(new ForbidInfo("1", "2", new Date(), new Date()));
        forbidList.add(new ForbidInfo("1111", "22222", new Date(), new Date()));

        LocalCacheUtilsForDBData.insertCache(BUSINESS_KEY, forbidList);
    }
    static class ForbidInfo{
        String hotelId;
        String agentCode;
        Date beginDate;
        Date endDate;

        public ForbidInfo(String hotelId, String agentCode, Date beginDate, Date endDate) {
            this.hotelId = hotelId;
            this.agentCode = agentCode;
            this.beginDate = beginDate;
            this.endDate = endDate;
        }

        @Override
        public String toString() {
            return "ForbidInfo{" +
                    "hotelId='" + hotelId + '\'' +
                    ", agentCode='" + agentCode + '\'' +
                    ", beginDate=" + beginDate +
                    ", endDate=" + endDate +
                    '}';
        }
    }
}
