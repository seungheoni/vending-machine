package com.example.mongo.model;

import com.example.cash.dto.CashDepositView;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;

@Document
@Data
@AllArgsConstructor
public class Cash {

    /**
     * cash id
     */
    @MongoId
    private ObjectId id;

    /**
     * 금액 잔고
     */
    private Long balance;

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

    public static Cash of(long balance) {
        return new Cash(ObjectId.get(), balance, Instant.now(), Instant.now());
    }

    private Cash copy() {
        return new Cash(id, balance, createDate, updateDate);
    }

    /**
     * 입금하기
     *
     * @param amount 금액
     */
    public Cash deposit(long amount) {
        Cash cash = copy();
        cash.balance += amount;
        return cash;
    }

    /**
     * 입금 응답값 변환
     */
    public CashDepositView toCashDepositView() {
        return new CashDepositView(balance);
    }
}
