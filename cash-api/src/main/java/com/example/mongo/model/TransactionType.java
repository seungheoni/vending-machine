package com.example.mongo.model;

public enum TransactionType {
    /**
     * 입금
     */
    DEPOSIT,

    /**
     * 지출
     */
    CHARGE,

    /**
     * 거스름돈
     */
    CHANGE
}
