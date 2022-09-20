package com.example.mongo;

import com.example.mongo.config.MongoConfigurer;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

@DataMongoTest
@ContextConfiguration(classes = {MongoConfigurer.class})
public class DbIntegrationTests {

    static MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"));

    @Autowired
    private MongoClient client;

    static {
        mongoDBContainer.start();
    }

    @DynamicPropertySource
    public static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", () -> mongoDBContainer.getReplicaSetUrl());
    }

    @Test
    public void mongodbConntest() {
        MongoDatabase mongoDatabase = client.getDatabase("test");
        Bson command = new BsonDocument("ping", new BsonInt64(1));
        Document commandResult = mongoDatabase.runCommand(command);
        System.out.println(commandResult);
    }
}