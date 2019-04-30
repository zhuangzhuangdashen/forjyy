package com.example.kafkaconsumer;

import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * Created by LIZHUANGZHUANG001 on 2019/4/28.
 */
@Component
public class QuartzTest extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext){
        System.out.println("quartz执行一次定时任务 ");
    }
}