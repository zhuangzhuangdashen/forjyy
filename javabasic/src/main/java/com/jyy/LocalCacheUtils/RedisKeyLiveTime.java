package com.jyy.LocalCacheUtils;

import sun.reflect.misc.ReflectUtil;

import java.util.ArrayList;
import java.util.List;

public enum RedisKeyLiveTime {
    /**
     * QueryRoomFreeWithActivityList接口
     * 查询0-7天的缓存key的有效期是3600秒
     */
    QueryRoomFreeWithActivityList_seven(60, 1000),

    QueryRoomFreeWithActivityList_sevenToFifteen(240L, 20),

    QueryRoomFreeWithActivityList_overFifteen(120L, 30);

    RedisKeyLiveTime() {
    }

    public static List getEnumStrArray() {
        RedisKeyLiveTime[] values = RedisKeyLiveTime.values();
        List instanceList = new ArrayList(values.length);
        for (RedisKeyLiveTime value : values) {
            String enumInstanceName = value.name();
            instanceList.add(enumInstanceName);
        }
        return instanceList;
    }

    RedisKeyLiveTime(long mills, int times) {
        this.mills = mills;
        this.times = times;
    }

    long mills;

    int times;

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public Long getMills() {
        return mills;
    }

    public void setMills(long mills) {
        this.mills = mills;
    }
}
