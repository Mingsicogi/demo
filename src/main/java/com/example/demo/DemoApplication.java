package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = {
        "classpath:properties/common.properties"
}, encoding = "UTF-8")
public class DemoApplication {
    @Value("${kafka.bootstrap.address}")
    private static String bootstrapAddress;

    public static void main(String[] args) {
        System.out.println("bootstrapAddress = " + bootstrapAddress);

        SpringApplication.run(DemoApplication.class, args);


    }
}

