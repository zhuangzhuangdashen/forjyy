package com.hzgroup.javers.modelbusingjaversdemo;

import org.javers.core.metamodel.annotation.Id;

import java.util.Date;

/**
 * Created by LIZHUANGZHUANG001 on 2019/3/14.
 */
public class HotelCodeAndBizDate {
    @Id
    private String roomType;
//    @Id
    private String hotelCode;
//    @Id
    private Date bizDate;
//    @Id
    private int roomCount;

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }

    public Date getBizDate() {
        return bizDate;
    }

    public void setBizDate(Date bizDate) {
        this.bizDate = bizDate;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }

    public HotelCodeAndBizDate(String hotelCode, Date bizDate, int roomCount, String roomType) {
        this.hotelCode = hotelCode;
        this.bizDate = bizDate;
        this.roomType = roomType;
        this.roomCount = roomCount;
    }
}
