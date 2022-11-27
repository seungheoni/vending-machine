package com.example.mongo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.index.Indexed;
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
     * 주문서 uniq 코드
     * ex) 0020101,0020102
     */
    @Indexed(unique = true)
    private String code;

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
}
