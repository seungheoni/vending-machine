package com.example.mongo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document
@Data
@AllArgsConstructor
public class Drink {

    @MongoId
    ObjectId id;
    String name;
    long price;
    long quantity;

    List<Display> displays;

    /**
     * 음료 데이터 생성
     * @param name 이름
     * @param price 가격
     * @param quantity 수량
     */
    public static Drink of(String name, int price, int quantity) {
        return new Drink(null, name, price, quantity, null);
    }
}