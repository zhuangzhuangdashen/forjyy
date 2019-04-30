package com.forjyy.springproject;

import com.forjyy.springproject.entity.JyyEntity;
import com.forjyy.springproject.service.JyyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableCaching
@ComponentScan(basePackages = "com.jyy")
public class SpringprojectApplicationTests {

    @Autowired
    private JyyService jyyService;

    @Test
    public void contextLoads() {
        jyyService.insert(new JyyEntity(1));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Object o = jyyService.get(1);
        System.out.println("o:" + o);

        System.out.println("spring test");
    }

}
