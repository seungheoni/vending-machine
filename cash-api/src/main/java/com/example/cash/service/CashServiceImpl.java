package com.example.cash.service;

import com.example.cash.dto.CashChangeView;
import com.example.cash.dto.CashDepositView;
import com.example.cash.repo.CashRepository;
import com.example.error.exception.CashEmptyException;
import com.example.error.exception.CashNotEnoughException;
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
        return cashRepository.findFirstBy()
                .map(cash -> {
                    transactionService.deposit(amount);
                    return cash.deposit(amount);
                }).map(cashRepository::save)
                .map(Cash::toCashDepositView)
                .orElseThrow();
    }

    @Override
    public CashChangeView change() {
        return cashRepository.findFirstBy()
                .filter(Cash::enableBalance)
                .map(cash -> {
                    cashRepository.save(cash.change());
                    transactionService.change(cash.getBalance());
                    return cash.toCashChangeView();
                }).orElseThrow(() -> {
                    throw new CashEmptyException();
                });
    }

    @Override
    public void charge(Long amount) {
        cashRepository.findFirstBy()
                .filter(Cash::enableBalance)
                .ifPresentOrElse(cash -> {
                    cashRepository.save(cash.charge(amount));
                    transactionService.charge(amount);
                }, () -> {
                    throw new CashNotEnoughException();
                });
    }
}
