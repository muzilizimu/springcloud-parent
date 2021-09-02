package com.lee.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MySpringMain {
    public static void main(String[] args) {
        SpringApplication.run(MySpringMain.class,args);
    }
}
