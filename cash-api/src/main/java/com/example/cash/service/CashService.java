package com.example.cash.service;

import com.example.cash.dto.CashChangeView;
import com.example.cash.dto.CashDepositView;

public interface CashService {

    /**
     * 금액 입금
     * @param amount
     * @return
     */
    CashDepositView deposit(Long amount);

    /**
     * 거스름돈 반환
     * @return
     */
    CashChangeView change();
}
