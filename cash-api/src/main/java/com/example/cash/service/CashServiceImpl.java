package com.example.cash.service;

import com.example.cash.dto.CashDepositView;
import com.example.cash.repo.CashRepository;
import com.example.mongo.model.Cash;
import com.example.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CashServiceImpl implements CashService {

    private final CashRepository cashRepository;

    private final TransactionService transactionService;

    @Override
    public CashDepositView deposit(final Long amount) {

        transactionService.deposit(amount);
        Cash cash = cashRepository.findFirstBy();
        cash.setBalance(cash.getBalance() + amount);
        cashRepository.save(cash);
        return CashDepositView.of(cash.getBalance());
    }
}
