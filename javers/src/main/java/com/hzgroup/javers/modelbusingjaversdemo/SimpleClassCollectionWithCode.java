package com.hzgroup.javers.modelbusingjaversdemo;

import java.util.List;

/**
 * Created by LIZHUANGZHUANG001 on 2019/3/13.
 */
public class SimpleClassCollectionWithCode {
    String hotelCode;
    List<SimpleClass> classList;

    public SimpleClassCollectionWithCode(String hotelCode, List<SimpleClass> classList) {
        this.hotelCode = hotelCode;
        this.classList = classList;
    }
}
