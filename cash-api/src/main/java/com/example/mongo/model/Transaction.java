package com.example.mongo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.PositiveOrZero;
import java.time.Instant;

@Document
@Data
@AllArgsConstructor
public class Transaction {

    /**
     * transaction id
     */
    @MongoId
    private ObjectId id;

    /**
     * 구분
     */
    private TransactionType type;

    /**
     * 금액
     */
    @PositiveOrZero
    private Long amount;

    /**
     * 데이터 생성 날짜
     */
    @CreatedDate
    private Instant createDate;

    /**
     * 데이터 업데이트 날짜
     */
    @LastModifiedDate
    private Instant updateDate;

    /**
     * 거래 내역 생성
     *
     * @param type   거래 구분
     * @param amount 거래 내역금액
     */
    private static Transaction of(TransactionType type, Long amount) {
        return new Transaction(null, type, amount, null, null);
    }

    /**
     * 거래 입금 내역 생성
     *
     * @param amount 거래 입금 금액
     */
    public static Transaction ofDeposit(Long amount) {
        return of(TransactionType.DEPOSIT, amount);
    }
}
