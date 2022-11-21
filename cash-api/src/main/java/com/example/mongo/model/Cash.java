package com.example.mongo.model;

import com.example.cash.dto.CashChangeView;
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
        return new Cash(null, balance, null, null);
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
     * 남은 거스름돈 반환
     *
     */
    public Cash change() {
        Cash cash = copy();
        cash.balance = 0L;
        return cash;
    }

    /**
     * 입금 금액 사용
     * @param amount 금액
     */
    public Cash charge(Long amount) {
        Cash cash = copy();
        cash.balance -= amount;
        return cash;
    }
    /**
     * 잔고 사용여부
     */
    public boolean enableBalance() {
        return balance > 0;
    }

    /**
     * 입금 응답값 변환
     */
    public CashDepositView toCashDepositView() {
        return new CashDepositView(balance);
    }

    /**
     * 거스름돈 응답값 반환
     */
    public CashChangeView toCashChangeView() {
        return new CashChangeView(balance);
    }
}
