package com.arcade.gamerarcade.configurations;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
//
//@Configuration
//public class MongoConfig {
//
//    // MongoClient bean for MongoDB 4.x
//    @Bean
//    public MongoClient mongoClient() {
//        return MongoClients.create("mongodb://localhost:27017"); // Adjust the URI as needed
//    }
//
//    // MongoTemplate bean
//    @Bean
//    public MongoTemplate mongoTemplate(MongoClient mongoClient, MongoMappingContext mongoMappingContext) {
//        return new MongoTemplate(mongoClient, "personalProjects"); // Use your database name here
//    }
//}


