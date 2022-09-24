package com.example.cash.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class CashDepositPayLoad {

    /**
     * 투입 금액 양
     */
    private long amount;
}
