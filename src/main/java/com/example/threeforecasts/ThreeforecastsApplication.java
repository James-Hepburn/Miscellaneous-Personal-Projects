package com.example.threeforecasts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ThreeforecastsApplication {
	public static void main(String[] args) {
		SpringApplication.run(ThreeforecastsApplication.class, args);
    }
}