package com.hzgroup.javers.modelbusingjaversdemo;

import org.javers.core.metamodel.annotation.Id;

import java.util.Date;
import java.util.List;

/**
 * Created by LIZHUANGZHUANG001 on 2019/3/12.
 */
public class SimpleClassCollection {

    @Id
    private String hotelCode;

    @Id
    private Date bizDate;

    List<RoomCountAndRoomType> classList;

    public SimpleClassCollection(String hotelCode, Date bizDate, List<RoomCountAndRoomType> classList) {
        this.hotelCode = hotelCode;
        this.bizDate = bizDate;
        this.classList = classList;
    }
}
