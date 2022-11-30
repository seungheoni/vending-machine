package com.example.mongo.model;

import com.example.order.dto.OrderPayLoad;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;

/**
 * 주문서
 */
@Document
@Data
@AllArgsConstructor
public class Order {

    /**
     * 주문서 고유 번호
     */
    @MongoId
    private ObjectId id;

    /**
     * 음료수 재고 관리 코드
     */
    private String drinkCode;

    /**
     * 구매 항목
     */
    private String item;

    /**
     * 제품 가격
     */
    private long price;

    /**
     * 주문서 생성 날짜
     */
    @CreatedDate
    private Instant createDate;

    public static Order of(OrderPayLoad orderPayLoad) {
        return new Order(null,orderPayLoad.getDrinkCode(),orderPayLoad.getItem(), orderPayLoad.getPrice(), null);
    }
}
