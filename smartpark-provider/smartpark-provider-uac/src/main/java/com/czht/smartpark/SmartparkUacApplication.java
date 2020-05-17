package com.czht.smartpark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = "com.czht.smartpark.provider.uac.mapper")
@SpringBootApplication
@EnableFeignClients
@EnableHystrix
public class SmartparkUacApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartparkUacApplication.class, args);
    }

}
