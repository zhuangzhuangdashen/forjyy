package com.example.admincf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.admincf")
@EnableAspectJAutoProxy
@EnableScheduling
public class AdmincfApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdmincfApplication.class, args);
    }

}
