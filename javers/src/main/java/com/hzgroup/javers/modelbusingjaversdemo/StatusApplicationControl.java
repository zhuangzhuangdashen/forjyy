package com.hzgroup.javers.modelbusingjaversdemo;

import java.util.Date;

/**
 * Created by LIZHUANGZHUANG001 on 2019/3/12.
 */
public class StatusApplicationControl {
    private Date start;
    private Date end;
    private String ratePlanCode;
    private String invTypeCode;
    private String isRoom;

    public StatusApplicationControl(Date start, Date end, String ratePlanCode, String invTypeCode, String isRoom) {
        this.start = start;
        this.end = end;
        this.ratePlanCode = ratePlanCode;
        this.invTypeCode = invTypeCode;
        this.isRoom = isRoom;
    }
}
