package com.example.config;

import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.junit.jupiter.api.Assertions.*;

class AppConfigTest {

    ApplicationContext ac;

    MongoTemplate mongoTemplate;

    MongoClient mongoClient;

    @BeforeEach
    void init(){
        ac = new AnnotationConfigApplicationContext(AppConfig.class);

        mongoClient = ac.getBean("mongoClient",MongoClient.class);
    }

    @Test
    public void mongodbConntest() {

        MongoDatabase mongoDatabase = mongoClient.getDatabase("vending_machine");

        ClientSession session = mongoClient.startSession();

        System.out.println(session);
    }
}