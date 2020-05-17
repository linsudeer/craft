package com.czht.smartpark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = "com.czht.smartpark.auth.mapper")
@SpringBootApplication
public class SmartparkAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartparkAuthApplication.class, args);
    }

}
