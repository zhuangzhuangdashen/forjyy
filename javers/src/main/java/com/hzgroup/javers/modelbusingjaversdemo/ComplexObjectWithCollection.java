package com.hzgroup.javers.modelbusingjaversdemo;

import org.javers.core.metamodel.annotation.Id;

import java.util.List;

/**
 * Created by LIZHUANGZHUANG001 on 2019/3/15.
 */
public class ComplexObjectWithCollection {

    @Id
    private String idValue;

    private List<RoomCount> countList;

    public ComplexObjectWithCollection(String idValue, List<RoomCount> countList) {
        this.idValue = idValue;
        this.countList = countList;
    }
}
