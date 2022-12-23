package com.example.cash.service;

import com.example.cash.dto.CashChangeView;
import com.example.cash.dto.CashDepositView;
import com.example.cash.repo.CashRepository;
import com.example.error.exception.CashEmptyException;
import com.example.error.exception.CashNotEnoughException;
import com.example.mongo.model.Cash;
import com.example.mongo.model.Transaction;
import com.example.mongo.model.entitymapper.CashMapper;
import com.example.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CashServiceImpl implements CashService {

    private final CashRepository cashRepository;

    private final TransactionService transactionService;

    private final CashMapper cashMapper;

    @Override
    public CashDepositView deposit(final Long amount) {
        return cashRepository.findFirstBy()
                .map(cash -> cashRepository.save(cashMapper.deposit(cash,amount))
                ).map(cash -> {
                    transactionService.deposit(amount);
                    return cashMapper.cashToCashDepositView(cash);
                }).orElseThrow();
    }

    @Override
    public CashChangeView change() {
        return cashRepository.findFirstBy()
                .filter(Cash::enableBalance)
                .map(cash -> {
                    cashRepository.save(cashMapper.change(cash));
                    transactionService.change(cash.getBalance());
                    return cashMapper.cashToCashChangeView(cash);
                }).orElseThrow(() -> {
                    throw new CashEmptyException();
                });
    }

    @Override
    public Transaction charge(Long amount) {
         return cashRepository.findFirstBy()
                .map(cash -> cashMapper.charge(cash,amount))
                .filter(cash -> cash.getBalance() >= 0)
                .map(cash -> {
                    cashRepository.save(cash);
                    return transactionService.charge(amount);
                }).orElseThrow(()-> {
                    throw new CashNotEnoughException();
                 });
    }
}