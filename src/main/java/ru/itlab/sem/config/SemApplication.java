package ru.itlab.sem.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class SemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SemApplication.class, args);
    }

}
