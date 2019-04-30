package com.hzgroup.javers.modelbusingjaversdemo;

import lombok.ToString;
import org.javers.core.metamodel.annotation.DiffIgnore;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Created by LIZHUANGZHUANG001 on 2019/3/12.
 */
@ToString
public class OTA_HotelAvailNotifRQ implements Serializable {
    @DiffIgnore
    private Date timeStamp;
    private String Target;
    private String version;
    private String primaryLangID;
    private String xmlns;
    private Pos pos;
    private AvailStatusMessages availStatusMessages;

    public OTA_HotelAvailNotifRQ(Date timeStamp, String target, String version, String primaryLangID, String xmlns, Pos pos, AvailStatusMessages availStatusMessages) {
        this.timeStamp = timeStamp;
        Target = target;
        this.version = version;
        this.primaryLangID = primaryLangID;
        this.xmlns = xmlns;
        this.pos = pos;
        this.availStatusMessages = availStatusMessages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OTA_HotelAvailNotifRQ that = (OTA_HotelAvailNotifRQ) o;
        return Objects.equals(timeStamp, that.timeStamp) &&
                Objects.equals(Target, that.Target) &&
                Objects.equals(version, that.version) &&
                Objects.equals(primaryLangID, that.primaryLangID) &&
                Objects.equals(xmlns, that.xmlns) &&
                Objects.equals(pos, that.pos) &&
                Objects.equals(availStatusMessages, that.availStatusMessages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeStamp, Target, version, primaryLangID, xmlns, pos, availStatusMessages);
    }
}
