package com.example.mongo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
@Data
@AllArgsConstructor
public class Drink {
    /**
     * 음료수 번호
     */
    @MongoId
    ObjectId id;

    /**
     * 음료수 물류 관리 코드
     */
    @Indexed(unique = true)
    String code;

    /**
     * 음료수 이름
     */
    String name;

    /**
     * 판매가격
     */
    long price;

    /**
     * 수량
     */
    long quantity;

    /**
     * 음료 데이터 생성
     * @param code 음료 물류 관리 번호
     * @param name 음료수 이름
     * @param price 음료수 가격
     * @param quantity 음료수 수량
     */
    public static Drink of(String code, String name, int price, int quantity) {
        return new Drink(null, code, name, price, quantity);
    }
}