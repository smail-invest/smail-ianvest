package com.smile.invest.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.smile.invest")
public class SmileinvestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmileinvestApplication.class, args);
    }

}
