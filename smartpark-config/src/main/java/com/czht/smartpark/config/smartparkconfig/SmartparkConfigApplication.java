package com.czht.smartpark.config.smartparkconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class SmartparkConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartparkConfigApplication.class, args);
    }

}
