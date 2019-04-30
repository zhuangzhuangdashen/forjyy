package com.hzgroup.javers.modelbusingjaversdemo;

import org.javers.core.metamodel.annotation.Id;

import java.util.List;

/**
 * Created by LIZHUANGZHUANG001 on 2019/3/12.
 */
public class AvailStatusMessages {
    @Id
    private String hotelCode;
    private List<AvailStatusMessage> availStatusMessageList;

    public AvailStatusMessages(String hotelCode, List<AvailStatusMessage> availStatusMessageList) {
        this.hotelCode = hotelCode;
        this.availStatusMessageList = availStatusMessageList;
    }
}
