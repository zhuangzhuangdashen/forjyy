package com.example.config;

import com.example.kafkaconsumer.QuartzTest;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by LIZHUANGZHUANG001 on 2019/4/28.
 */
@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail quartzDetail(){
        return JobBuilder.newJob(QuartzTest.class).withIdentity("QuartzTest").storeDurably().build();
    }
    @Bean
    public SimpleTrigger quartzTrigger(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10)
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(quartzDetail())
                .withIdentity("QuartzTest")
                .withSchedule(scheduleBuilder)
                .build();
    }
}