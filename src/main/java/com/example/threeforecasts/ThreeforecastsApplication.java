package com.example.threeforecasts;

import com.example.threeforecasts.service.NflverseService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.*;

@SpringBootApplication
public class ThreeforecastsApplication {
	public static void main(String[] args) {
		SpringApplication.run(ThreeforecastsApplication.class, args);
    }

    @Bean
    CommandLineRunner runner (NflverseService playerService) {
        return args -> {
            playerService.loadPlayers ();
        };
    }
}
