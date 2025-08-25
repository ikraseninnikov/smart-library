package com.library;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.library.mapper")
public class SmartLibrary {
    public static void main(String[] args) {
        SpringApplication.run(SmartLibrary.class, args);
    }
}