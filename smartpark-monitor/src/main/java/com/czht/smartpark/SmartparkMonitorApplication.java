package com.czht.smartpark.monitor.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class SmartparkMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartparkMonitorApplication.class, args);
    }

}
