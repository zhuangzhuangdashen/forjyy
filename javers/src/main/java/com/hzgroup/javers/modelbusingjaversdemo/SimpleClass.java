package com.hzgroup.javers.modelbusingjaversdemo;

import org.javers.core.metamodel.annotation.Id;

/**
 * Created by LIZHUANGZHUANG001 on 2019/3/12.
 */
public class SimpleClass {

    @Id
    private HotelCodeAndBizDate hotelCodeAndBizDate;

    private int roomCount;

    private String roomType;

    public SimpleClass(HotelCodeAndBizDate hotelCodeAndBizDate, int roomCount, String roomType) {
        this.hotelCodeAndBizDate = hotelCodeAndBizDate;
        this.roomCount = roomCount;
        this.roomType = roomType;
    }
}
