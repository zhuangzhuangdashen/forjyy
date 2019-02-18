package com.jyy.LocalCacheUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by LIZHUANGZHUANG001 on 2019/2/18.
 */
public class LocalCacheUtilsForDBTest {

    public static final String BUSINESS_KEY = "forbidList";

    public static void main(String[] args) {
        // init data
        initCacheData();
        // then read
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0 ; i <100 ; i++) {
                    List list = LocalCacheUtilsForDBData.readCache(BUSINESS_KEY);
                    System.out.println("read content : " + list);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0 ; i <100 ; i++) {
                    LocalCacheUtilsForDBData.insertCache(BUSINESS_KEY, getForbidList());
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
    }

}
