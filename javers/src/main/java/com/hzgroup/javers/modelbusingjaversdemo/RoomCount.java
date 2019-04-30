package com.hzgroup.javers.modelbusingjaversdemo;

import org.javers.core.metamodel.annotation.Id;

import java.util.Date;
import java.util.Objects;

/**
 * Created by LIZHUANGZHUANG001 on 2019/3/14.
 */
public class RoomCount {
    private String hotelCode;
    private Date bizDate;
    private int roomCount;
    @Id
    private String roomType;
    public RoomCount(String hotelCode, Date bizDate, int roomCount, String roomType) {
        this.hotelCode = hotelCode;
        this.bizDate = bizDate;
        this.roomCount = roomCount;
        this.roomType = roomType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomCount roomCount1 = (RoomCount) o;
        return roomCount == roomCount1.roomCount &&
                Objects.equals(hotelCode, roomCount1.hotelCode) &&
                Objects.equals(bizDate, roomCount1.bizDate) &&
                Objects.equals(roomType, roomCount1.roomType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hotelCode, bizDate, roomCount, roomType);
    }
}
