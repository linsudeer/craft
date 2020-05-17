package com.czht.smartpark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class SmartparkGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartparkGatewayApplication.class, args);
    }

}
