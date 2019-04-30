package com.hzgroup.javers.modelbusingjaversdemo;

import org.javers.core.metamodel.annotation.Id;

/**
 * Created by LIZHUANGZHUANG001 on 2019/3/14.
 */
public class RoomCountWithInnerObject {

    @Id
    private HotelCodeAndBizDate hotelCodeAndBizDate;

    private int roomCount;

    public RoomCountWithInnerObject(HotelCodeAndBizDate hotelCodeAndBizDate, int roomCount) {
        this.hotelCodeAndBizDate = hotelCodeAndBizDate;
        this.roomCount = roomCount;
    }
}
