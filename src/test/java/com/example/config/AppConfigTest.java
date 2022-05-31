package com.example.config;

import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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

        MongoDatabase mongoDatabase = mongoClient.getDatabase("vending-machine");
        Bson command = new BsonDocument("ping", new BsonInt64(1));
        Document commandResult = mongoDatabase.runCommand(command);

        assertNotNull(commandResult);
    }
}