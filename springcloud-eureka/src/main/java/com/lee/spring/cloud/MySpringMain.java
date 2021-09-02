package com.lee.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MySpringMain {
    public static void main(String[] args) {
        SpringApplication.run(MySpringMain.class,args);
    }
}
