package com.example.StudentRanker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StudentRankerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentRankerApplication.class, args);
    }

}
