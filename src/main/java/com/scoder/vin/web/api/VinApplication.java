package com.scoder.vin.web.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.scoder.vin.web.api.vin.mapper")
public class VinApplication {

    public static void main(String[] args) {
        SpringApplication.run(VinApplication.class, args);
    }

}
