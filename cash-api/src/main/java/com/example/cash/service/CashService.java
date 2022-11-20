package com.example.cash.service;

import com.example.cash.dto.CashChangeView;
import com.example.cash.dto.CashDepositView;

public interface CashService {

    /**
     * 금액 입금
     * @param amount 금액
     */
    CashDepositView deposit(Long amount);

    /**
     * 거스름돈 반환
     */
    CashChangeView change();

    /**
     * 입금 금액 사용
     * @param amount 사용 금액
     */
    void charge(Long amount);
}
