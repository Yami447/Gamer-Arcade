package com.arcade.gamerarcade.configurations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.arcade")
@ComponentScan(basePackages = "com.arcade")
//@Configuration
public class GamerArcadeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GamerArcadeApplication.class, args);
    }

}
