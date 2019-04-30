package com.example.kafka.controller;

/**
 * Created by LIZHUANGZHUANG001 on 2019/4/24.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.UUID;

/**
 * 生产者
 * 使用@EnableScheduling注解开启定时任务
 */
@Component
@EnableScheduling
public class KafkaProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * 定时任务
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void send(){
         String message = UUID.randomUUID().toString();
        ListenableFuture future = kafkaTemplate.send("app_log", message);
        future.addCallback(o -> System.out.println("send-消息发送成功：" + message), throwable -> System.out.println("消息发送失败：" + message));
    }

    @KafkaListener(topics = {"app_log"}, groupId = "0")
    public void receive(String content){
        System.err.println("Receive:" + content);
    }

}