package com.example.mongo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
@Data
@AllArgsConstructor
public class Drink {

    @MongoId
    ObjectId id;
    String name;
    long price;
    long quantity;
}