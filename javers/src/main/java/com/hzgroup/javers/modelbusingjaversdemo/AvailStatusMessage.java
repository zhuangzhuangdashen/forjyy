package com.hzgroup.javers.modelbusingjaversdemo;

/**
 * Created by LIZHUANGZHUANG001 on 2019/3/12.
 */
public class AvailStatusMessage {
    private String locatorID;
    private StatusApplicationControl statusApplicationControl;
    private RestrictionStatus restrictionStatus;

    public AvailStatusMessage(String locatorID, StatusApplicationControl statusApplicationControl, RestrictionStatus restrictionStatus) {
        this.locatorID = locatorID;
        this.statusApplicationControl = statusApplicationControl;
        this.restrictionStatus = restrictionStatus;
    }
}
