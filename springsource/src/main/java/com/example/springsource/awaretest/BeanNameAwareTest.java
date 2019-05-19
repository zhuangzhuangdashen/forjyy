package com.example.springsource.awaretest;

import com.example.springsource.configtest.ConfigTest;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by LIZHUANGZHUANG001 on 2019/5/15.
 */
@Component
public class BeanNameAwareTest implements BeanNameAware, InitializingBean {

//    @Override
//    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println(beanName + " : postProcessBeforeInitialization");
//        return bean;
//    }
//
//    @Override
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("postProcessAfterInitialization");
//        return bean;
//    }

//    @Autowired
    private ConfigTest configTest;

    private String name;

    @Bean("Alen")
    public String getBean() {
        return "Alen";
    }


    @Autowired
    public void setConfigTest(ConfigTest configTest) {
        this.configTest = configTest;
        System.out.println("configTest : " + configTest);
    }

    private String beanName;

    public BeanNameAwareTest() {
        System.out.println("构造器执行");
        System.out.println(beanName);
    }

    public BeanNameAwareTest(String beanName) {
        this.beanName = beanName;
        System.out.println(beanName);
    }

    @Override
    public void setBeanName(String beanName) {
        System.out.println(configTest);
        this.beanName = beanName;
        System.out.println("set方法执行");
        System.out.println(beanName);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet execute");
    }
}
