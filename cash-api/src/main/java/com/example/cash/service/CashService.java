package com.example.cash.service;

import com.example.cash.dto.CashDepositPayLoad;
import com.example.cash.dto.CashDepositView;

public interface CashService {

    CashDepositView deposit(Long amount);
}
