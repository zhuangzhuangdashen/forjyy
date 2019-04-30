package com.example.kafka.controller;

import org.springframework.stereotype.Controller;

/**
 * Created by LIZHUANGZHUANG001 on 2019/4/24.
 */
@Controller
public class HelloController {

    public HelloController() {
        System.out.println("构造完成");
    }

    public String sout() {
        System.out.println("初始化成功");
        return "";
    }
}
