package com.hzgroup.javers.modelbusingjaversdemo;

/**
 * Created by LIZHUANGZHUANG001 on 2019/3/12.
 */
public class RequestorID {
    private String type;
    private String id;
    private String id_context;

    public RequestorID(String type, String id, String id_context) {
        this.type = type;
        this.id = id;
        this.id_context = id_context;
    }
}
