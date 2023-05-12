package com.adzhiametov.gps.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class GpsServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GpsServerApplication.class, args);
    }

}
