package com.czht.smartpark.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class SmartparkZipkinApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartparkZipkinApplication.class, args);
    }

}
