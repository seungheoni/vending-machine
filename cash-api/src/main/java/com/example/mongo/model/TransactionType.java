package com.example.mongo.model;

public enum TransactionType {
    /**
     * 입금
     */
    DEPOSIT,

    /**
     * 지출
     */
    WITHDRAW,

    /**
     * 거스름돈
     */
    CHANGE
}
