package com.example.springsource.configtest;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.annotation.Configuration;

/**
 * Created by LIZHUANGZHUANG001 on 2019/5/15.
 */
@Configuration
public class ConfigTest implements BeanNameAware
{
    private String beanName;

    public ConfigTest() {
        System.out.println("配置类初始化");
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
